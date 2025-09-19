CREATE TABLE click_analytics (
    id SERIAL PRIMARY KEY,
    url_shortener_id INTEGER NOT NULL,
    click_timestamp TIMESTAMP NOT NULL,
    ip_address VARCHAR(255),
    user_agent TEXT,
    referrer VARCHAR(255),
    CONSTRAINT fk_url_shortener
    FOREIGN KEY(url_shortener_id)
    REFERENCES url_shortener(id)
);
