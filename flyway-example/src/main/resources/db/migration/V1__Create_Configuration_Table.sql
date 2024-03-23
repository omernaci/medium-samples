CREATE TABLE configuration (
           id BIGSERIAL PRIMARY KEY,
           config_key VARCHAR(255) NOT NULL,
           config_value VARCHAR(255) NOT NULL,
           description VARCHAR(255)
);
