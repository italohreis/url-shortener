package com.italohreis.url_shortener.dto;

public record ReferrerStatsDto(
        String referrer,
        Long clicks
) {}
