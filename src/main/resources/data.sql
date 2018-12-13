DELETE FROM clients;

ALTER TABLE clients ALTER COLUMN id RESTART WITH 1;

INSERT INTO clients (name, description) VALUES
('Alexey', 'Java developer'),
('Max', 'JavaScript developer'),
('Nikodim', 'Assembler developer ');