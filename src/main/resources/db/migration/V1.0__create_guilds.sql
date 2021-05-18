CREATE TABLE guilds (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  guild_id      BIGINT UNIQUE NOT NULL,
  name          VARCHAR(255) NOT NULL,
  prefix        VARCHAR(255) NOT NULL,
  created_at    DATETIME NOT NULL,
  updated_at    DATETIME NOT NULL
);
