CREATE TABLE IF NOT EXISTS musics(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    curtida BOOLEAN NOT NULL DEFAULT FALSE,
    filename VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);