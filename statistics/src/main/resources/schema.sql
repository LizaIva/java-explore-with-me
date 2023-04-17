CREATE TABLE IF NOT EXISTS statistics
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    app VARCHAR NOT NULL,
    uri VARCHAR NOT NULL,
    ip VARCHAR NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );