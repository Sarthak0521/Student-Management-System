CREATE DATABASE studentdb;
USE studentdb;
CREATE TABLE students(
id INT PRIMARY KEY,
name VARCHAR(100),
age INT,
email VARCHAR(100) UNIQUE,
course VARCHAR(100)
);
