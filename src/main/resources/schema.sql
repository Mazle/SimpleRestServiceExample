
CREATE TABLE clients (
  id   BIGINT NOT NULL CONSTRAINT clients_pk primary key,
  name VARCHAR(255) NOT NULL,
  description  VARCHAR(255) NOT NULL
);