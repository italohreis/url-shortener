package com.italohreis.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;

public record ShortenUrlRequest (
    @NotBlank
    String longUrl
) {}
