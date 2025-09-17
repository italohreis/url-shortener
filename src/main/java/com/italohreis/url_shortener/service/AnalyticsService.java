package com.italohreis.url_shortener.service;

import com.italohreis.url_shortener.model.ClickAnalytics;
import com.italohreis.url_shortener.model.UrlShortener;
import com.italohreis.url_shortener.repository.ClickAnalyticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final ClickAnalyticsRepository analyticsRepository;

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
}
