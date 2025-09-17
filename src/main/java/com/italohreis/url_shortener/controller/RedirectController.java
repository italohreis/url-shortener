package com.italohreis.url_shortener.controller;

import com.italohreis.url_shortener.model.UrlShortener;
import com.italohreis.url_shortener.service.AnalyticsService;
import com.italohreis.url_shortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final UrlShortenerService urlShortenerService;
    private final AnalyticsService analyticsService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl, HttpServletRequest request) {
        UrlShortener urlEntity = urlShortenerService.getUrlEntityByShortUrl(shortUrl);
        String longUrl = urlShortenerService.getLongUrlByShortUrl(shortUrl);

        trackAnalytics(urlEntity, request);

        URI redirectUri = UriComponentsBuilder.fromUriString(longUrl)
                .build()
                .toUri();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(redirectUri)
                .build();
    }

    private void trackAnalytics(UrlShortener url, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String referrer = request.getHeader("Referer");
        analyticsService.trackClick(url, ipAddress, userAgent, referrer);
    }
}
