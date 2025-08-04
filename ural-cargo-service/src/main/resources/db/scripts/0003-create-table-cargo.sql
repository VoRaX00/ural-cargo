CREATE TABLE IF NOT EXISTS cargo
(
    id BIGSERIAL PRIMARY KEY,
    type_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    length FLOAT NOT NULL,
    height FLOAT NOT NULL,
    width FLOAT NOT NULL,
    weight FLOAT NOT NULL,
    volume FLOAT NOT NULL,
    phone_number TEXT NOT NULL,
    loading_place TEXT NOT NULL,
    unloading_place TEXT NOT NULL,
    price DECIMAL NOT NULL,
    comment TEXT,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ,
    FOREIGN KEY (type_id) REFERENCES cargo_types (id)
)