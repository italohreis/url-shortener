package com.italohreis.url_shortener.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Redirect", description = "Endpoint for redirecting shortened URLs")
public interface RedirectControllerDocs {

    @Operation(summary = "Redirects a short URL to its original long URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirected successfully"),
            @ApiResponse(responseCode = "404", description = "Shortened URL not found")
    })
    ResponseEntity<Void> redirect(
            @Parameter(description = "The code of the shortened URL", required = true, example = "cb")
            @PathVariable String shortUrl,
            @Parameter(hidden = true)
            HttpServletRequest request);
}
