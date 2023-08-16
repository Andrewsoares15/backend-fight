CREATE TABLE IF NOT EXISTS PEOPLE
(
    id          UUID PRIMARY KEY,
    nick_name   VARCHAR(255) NOT NULL UNIQUE,
    people_name VARCHAR(255) NOT NULL,
    dat_birth   DATE         NOT NULL,
    stacks      VARCHAR(255)
);

CREATE INDEX idx_people_nick_name ON PEOPLE (nick_name);
CREATE INDEX idx_people_people_name ON PEOPLE (people_name);
CREATE INDEX idx_stack_stack_name ON PEOPLE (stacks);
