ALTER TABLE cliente ADD COLUMN senha VARCHAR(255) NULL;
UPDATE cliente SET senha = '123456' WHERE cliente.id = 3;
UPDATE cliente SET senha = '123' WHERE cliente.id = 1;