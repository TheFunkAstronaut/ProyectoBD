create database jdbc;
use jdbc;
-- Creación de la tabla Estudiantes
CREATE TABLE Estudiantes (
    id_estudiante INT PRIMARY KEY,
    nombre VARCHAR(250),
    ApellidoPaterno VARCHAR(250),
    ApellidoMaterno VARCHAR(250),
    carrera VARCHAR(250),
    fechaNacimiento DATE
);

-- Creación de la tabla Materias
CREATE TABLE Materias (
    id_materia INT PRIMARY KEY,
    nombre VARCHAR(250),
    numCreditos INT
);

-- Creación de la tabla Estudiantes_en_materias
CREATE TABLE Estudiantes_en_materias (
    id_estudiante INT,
    id_materia INT,
    tipoNota VARCHAR(50),
    valorNota FLOAT,
    FOREIGN KEY (id_estudiante) REFERENCES Estudiantes(id_estudiante),
    FOREIGN KEY (id_materia) REFERENCES Materias(id_materia),
    PRIMARY KEY (id_estudiante, id_materia, tipoNota)
);

-- Insertar nuevos datos en las tablas
INSERT INTO Estudiantes (id_estudiante, nombre, ApellidoPaterno, ApellidoMaterno, carrera, fechaNacimiento) 
VALUES 
    (1, 'Juan', 'Pérez', 'González', 'Ingeniería de Sistemas', '1998-05-15'),
    (2, 'María', 'García', 'Rodríguez', 'Arquitectura', '2000-09-20'),
    (3, 'Carlos', 'López', 'Martínez', 'Medicina', '1997-03-10');

INSERT INTO Materias (id_materia, nombre, numCreditos) 
VALUES 
    (1, 'Matemáticas', 4),
    (2, 'Física', 4),
    (3, 'Programación I', 4),
    (4, 'Programación II', 4),
    (5, 'Redes de Computadoras', 4),
    (6, 'Base de Datos', 4),
    (7, 'Sistemas Operativos', 4),
    (8, 'Electrónica Digital', 4),
    (9, 'Ingeniería de Software', 4),
    (10, 'Proyecto de Tesis', 6);

INSERT INTO Estudiantes_en_materias (id_estudiante, id_materia, tipoNota, valorNota)
VALUES 
    (1, 3, 'primer parcial', 85),
    (1, 4, 'primer parcial', 88),
    (1, 5, 'primer parcial', 90),
    (1, 6, 'primer parcial', 82),
    (2, 2, 'primer parcial', 78),
    (3, 3, 'primer parcial', 90);
    
select * from estudiantes;
select * from materias;
select * from estudiantes_en_materias;

