package com.italohreis.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record ShortenUrlRequest (
    @NotBlank(message = "URL cannot be blank")
    @URL(message = "Invalid URL format")
    String longUrl
) {}
