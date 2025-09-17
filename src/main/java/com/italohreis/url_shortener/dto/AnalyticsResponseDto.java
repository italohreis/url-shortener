package com.italohreis.url_shortener.dto;

import java.util.List;
import java.util.Map;

public record AnalyticsResponseDto(
        String shortUrl,
        String longUrl,
        long totalClicks,
        Map<String, Long> clicksByDay,
        List<ReferrerStatsDto> topReferrers,
        Map<String, Long> topUserAgents
) {}