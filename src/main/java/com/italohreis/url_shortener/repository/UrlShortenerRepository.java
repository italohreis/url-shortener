package com.italohreis.url_shortener.repository;

import com.italohreis.url_shortener.model.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, UUID> {

    Optional<UrlShortener> findByShortCode(String shortCode);

    Optional<UrlShortener> findByLongUrl(String longUrl);
}
