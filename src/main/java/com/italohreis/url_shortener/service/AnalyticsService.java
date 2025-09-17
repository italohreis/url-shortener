package com.italohreis.url_shortener.service;

import com.italohreis.url_shortener.dto.AnalyticsResponseDto;
import com.italohreis.url_shortener.dto.ReferrerStatsDto;
import com.italohreis.url_shortener.exception.UrlNotFoundException;
import com.italohreis.url_shortener.model.ClickAnalytics;
import com.italohreis.url_shortener.model.UrlShortener;
import com.italohreis.url_shortener.repository.ClickAnalyticsRepository;
import com.italohreis.url_shortener.repository.UrlShortenerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ClickAnalyticsRepository analyticsRepository;
    private final UrlShortenerRepository urlShortenerRepository;

    @Async
    public void trackClick(UrlShortener url, String ipAddress, String userAgent, String referrer) {
        ClickAnalytics click = new ClickAnalytics();
        click.setUrlShortener(url);
        click.setClickTimestamp(LocalDateTime.now());
        click.setIpAddress(ipAddress);
        click.setUserAgent(userAgent);
        click.setReferrer(referrer);

        analyticsRepository.save(click);
    }

    public AnalyticsResponseDto getAnalytics(String shortUrl) {
        UrlShortener url = urlShortenerRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortUrl));

        List<ClickAnalytics> clicks = analyticsRepository.findAllByUrlShortener_ShortUrl(shortUrl);

        long totalClicks = clicks.size();

        Map<String, Long> clicksByDay = clicks.stream()
                .collect(Collectors.groupingBy(
                        click -> click.getClickTimestamp().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        Collectors.counting()
                ));

        List<ReferrerStatsDto> topReferrers = clicks.stream()
                .filter(click -> click.getReferrer() != null && !click.getReferrer().isBlank())
                .collect(Collectors.groupingBy(ClickAnalytics::getReferrer, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(entry -> new ReferrerStatsDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Map<String, Long> topUserAgents = clicks.stream()
                .filter(click -> click.getUserAgent() != null && !click.getUserAgent().isBlank())
                .collect(Collectors.groupingBy(ClickAnalytics::getUserAgent, Collectors.counting()));

        return new AnalyticsResponseDto(
                shortUrl,
                url.getLongUrl(),
                totalClicks,
                clicksByDay,
                topReferrers,
                topUserAgents
        );
    }
}
