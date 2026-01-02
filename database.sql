
CREATE DATABASE banking_db;
USE banking_db;

CREATE TABLE users (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100),
 username VARCHAR(50),
 password VARCHAR(50),
 balance DOUBLE
);

INSERT INTO users(name, username, password, balance)
VALUES ('Demo User','demo','demo123',10000);
