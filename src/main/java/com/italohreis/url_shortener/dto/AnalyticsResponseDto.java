package com.italohreis.url_shortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

@Schema(description = "DTO containing the analytics data for a shortened URL")
public record AnalyticsResponseDto(
        @Schema(description = "The shortened URL code", example = "cb")
        String shortUrl,

        @Schema(description = "The original long URL", example = "https://www.google.com")
        String longUrl,

        @Schema(description = "Total number of clicks for the shortened URL", example = "150")
        long totalClicks,

        @Schema(description = "Map of clicks per day, with the date as key (YYYY-MM-DD) and click count as value")
        Map<String, Long> clicksByDay,

        @Schema(description = "List of top referrers")
        List<ReferrerStatsDto> topReferrers,

        @Schema(description = "Map of top user agents (browsers/devices)")
        Map<String, Long> topUserAgents
) {}
