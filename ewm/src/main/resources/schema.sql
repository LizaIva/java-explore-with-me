CREATE TABLE IF NOT EXISTS users
(
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255)                            NOT NULL,
    email   VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS compilations
(
    compilation_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pinned         BOOLEAN                                 NOT NULL,
    title          VARCHAR(525)                            NOT NULL,
    CONSTRAINT pk_compilation PRIMARY KEY (compilation_id)
);

CREATE TABLE IF NOT EXISTS categories
(
    category_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (category_id),
    CONSTRAINT UQ_CATEGORY_NAME UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS events
(
    event_id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    annotation         VARCHAR(1024)                           NOT NULL,
    description        VARCHAR(1024)                           NOT NULL,
    created            TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    category_id        BIGINT                                  NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (category_id) ON DELETE RESTRICT,
    confirmed_requests BIGINT                                  NOT NULL,
    event_date         TIMESTAMP                               NOT NULL,
    user_id            BIGINT                                  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    paid               BOOLEAN                                 NOT NULL,
    title              VARCHAR(255)                            NOT NULL,
    views              BIGINT                                  NOT NULL,
    location_lat       DECIMAL                                 NOT NULL,
    location_lon       DECIMAL                                 NOT NULL,
    participant_limit  BIGINT                                  NOT NULL,
    published_on       TIMESTAMP                               NOT NULL,
    request_moderation BOOLEAN                                 NOT NULL,
    state              VARCHAR(50)                             NOT NULL,
    CONSTRAINT pk_events PRIMARY KEY (event_id)
);

CREATE TABLE IF NOT EXISTS compilations_events
(
    compilation_id BIGINT NOT NULL,
    FOREIGN KEY (compilation_id) REFERENCES compilations (compilation_id) ON DELETE CASCADE,
    event_id       BIGINT NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS requests
(
    request_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    event_id     BIGINT                                  NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE,
    requester_id BIGINT                                  NOT NULL,
    FOREIGN KEY (requester_id) REFERENCES users (user_id) ON DELETE CASCADE,
    status       VARCHAR(50)                             NOT NULL,
    CONSTRAINT pk_request PRIMARY KEY (request_id)
);


CREATE TABLE IF NOT EXISTS comments
(
    comment_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id    BIGINT                                  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE,
    event_id   BIGINT                                  NOT NULL,
    FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE,
    state      VARCHAR(50)                             NOT NULL,
    text       VARCHAR(500)                            NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (comment_id)
);
