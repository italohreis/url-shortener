package com.italohreis.url_shortener.exception;

import org.springframework.http.HttpStatus;


public record RestErrorMessage(
        HttpStatus status,
        String message,
        String path
) {}
