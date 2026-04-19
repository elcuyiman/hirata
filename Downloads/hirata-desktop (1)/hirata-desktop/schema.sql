CREATE DATABASE IF NOT EXISTS hirata_db;
USE hirata_db;

CREATE TABLE IF NOT EXISTS usuarios (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS camiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    anio INT NOT NULL
);

CREATE TABLE IF NOT EXISTS mantenimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    fecha VARCHAR(20) NOT NULL,
    estado VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (username, password, rol)
VALUES ('admin', 'admin123', 'ADMIN')
ON DUPLICATE KEY UPDATE username = username;

INSERT INTO camiones (placa, modelo, anio)
VALUES ('ABC123', 'Volvo FH', 2020),
       ('DEF456', 'Scania R450', 2021);

INSERT INTO mantenimientos (descripcion, fecha, estado)
VALUES ('Cambio de aceite', '2026-03-20', 'Completado'),
       ('Revisión de frenos', '2026-03-25', 'Pendiente');
