CREATE TABLE IF NOT EXISTS People (
    id UUID PRIMARY KEY,
    nick_name VARCHAR(255) NOT NULL,
    people_name VARCHAR(255) NOT NULL,
    dat_birth DATE NOT NULL,
    stack TEXT[]
);
