package com.italohreis.url_shortener.service;

import com.italohreis.url_shortener.dto.ShortenUrlResponse;
import com.italohreis.url_shortener.exception.UrlNotFoundException;
import com.italohreis.url_shortener.model.UrlShortener;
import com.italohreis.url_shortener.repository.UrlShortenerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlShortenerService {
    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final UrlShortenerRepository repository;
    private final String baseUrl;

    public UrlShortenerService(UrlShortenerRepository repository, @Value("${app.base-url}") String baseUrl) {
        this.repository = repository;
        this.baseUrl = baseUrl;
    }

    @Transactional
    public ShortenUrlResponse shortenUrl(String longUrl) {
        Optional<UrlShortener> existingUrl = repository.findByLongUrl(longUrl);
        if (existingUrl.isPresent()) {
            return new ShortenUrlResponse(this.baseUrl + "/" + existingUrl.get().getShortUrl());
        }

        UrlShortener newUrl = new UrlShortener();
        newUrl.setLongUrl(longUrl);
        newUrl.setCreatedAt(LocalDateTime.now());

        UrlShortener savedUrl = repository.save(newUrl);
        String shortCode = encodeBase62(savedUrl.getId());

        savedUrl.setShortUrl(shortCode);
        repository.save(savedUrl);

        String shortUrl = this.baseUrl + "/" + shortCode;

        return new ShortenUrlResponse(shortUrl);
    }

    public String getLongUrlByShortUrl(String shortUrl) {
        UrlShortener urlShortener = repository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Short URL not found: " + shortUrl));
        return urlShortener.getLongUrl();
    }

    public String encodeBase62(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(BASE62_CHARS.charAt((int) (num % 62)));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}
