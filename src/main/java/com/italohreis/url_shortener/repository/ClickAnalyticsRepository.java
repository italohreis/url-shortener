package com.italohreis.url_shortener.repository;

import com.italohreis.url_shortener.model.ClickAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClickAnalyticsRepository extends JpaRepository<ClickAnalytics, Long> {
    List<ClickAnalytics> findAllByUrlShortener_ShortUrl(String shortUrl);
}
