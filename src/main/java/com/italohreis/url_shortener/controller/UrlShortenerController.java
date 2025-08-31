package com.italohreis.url_shortener.controller;

import com.italohreis.url_shortener.dto.ShortenUrlRequest;
import com.italohreis.url_shortener.dto.ShortenUrlResponse;
import com.italohreis.url_shortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody @Valid ShortenUrlRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(urlShortenerService.shortenUrl(request.longUrl()));
    }
}
