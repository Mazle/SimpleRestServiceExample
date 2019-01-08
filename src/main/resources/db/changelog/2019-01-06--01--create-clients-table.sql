CREATE TABLE clients (
  id   BIGINT CONSTRAINT clients_pk primary key DEFAULT nextval('rest_service_seq'),
  name VARCHAR(255) NOT NULL,
  description  VARCHAR(255) NOT NULL
);