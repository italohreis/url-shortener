CREATE TABLE url_shortener (
    id SERIAL PRIMARY KEY,
    long_url TEXT NOT NULL,
    short_code VARCHAR(255) UNIQUE,
    created_at TIMESTAMP NOT NULL
);