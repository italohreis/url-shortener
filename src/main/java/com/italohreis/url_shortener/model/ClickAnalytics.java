package com.italohreis.url_shortener.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "click_analytics")
@Entity
public class ClickAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_shortener_id", nullable = false)
    private UrlShortener urlShortener;

    @Column(nullable = false)
    private LocalDateTime clickTimestamp;

    private String ipAddress;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    private String referrer;
}
