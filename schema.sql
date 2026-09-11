CREATE DATABASE IF NOT EXISTS sakshee_portfolio;
USE sakshee_portfolio;

CREATE TABLE IF NOT EXISTS contact_messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    message VARCHAR(240) NOT NULL,
    created_at DATETIME NOT NULL
);
