CREATE TABLE IF NOT EXISTS statistics
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app     VARCHAR(100)                            NOT NULL,
    uri     VARCHAR(100)                            NOT NULL,
    ip      VARCHAR(100)                            NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);