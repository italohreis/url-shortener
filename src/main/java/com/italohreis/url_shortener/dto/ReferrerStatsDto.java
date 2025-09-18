package com.italohreis.url_shortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO representing statistics for a specific referrer")
public record ReferrerStatsDto(
        @Schema(description = "The referrer URL or 'Direct' if not available", example = "https://www.github.com")
        String referrer,

        @Schema(description = "The number of clicks from this referrer", example = "25")
        Long clicks
) {}
