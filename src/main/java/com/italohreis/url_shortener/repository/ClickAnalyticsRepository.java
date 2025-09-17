package com.italohreis.url_shortener.repository;

import com.italohreis.url_shortener.model.ClickAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickAnalyticsRepository extends JpaRepository<ClickAnalytics, Long> {
}
