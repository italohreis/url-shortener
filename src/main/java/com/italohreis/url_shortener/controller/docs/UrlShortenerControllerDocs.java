package com.italohreis.url_shortener.controller.docs;

import com.italohreis.url_shortener.dto.AnalyticsResponseDto;
import com.italohreis.url_shortener.dto.ShortenUrlRequest;
import com.italohreis.url_shortener.dto.ShortenUrlResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "URL Shortener", description = "Endpoints for shortening URLs and viewing statistics")
public interface UrlShortenerControllerDocs {

    @Operation(summary = "Creates a new shortened URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "URL successfully shortened",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ShortenUrlResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody @Valid ShortenUrlRequest request);

    @Operation(summary = "Gets the statistics for a shortened URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnalyticsResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Shortened URL not found")
    })
    ResponseEntity<AnalyticsResponseDto> getAnalytics(
            @Parameter(description = "The code of the shortened URL", required = true, example = "cb")
            @PathVariable String shortUrl);
}
