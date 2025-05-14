CREATE DATABASE IF NOT EXISTS plan_schema;

USE plan_schema;

CREATE TABLE plan_schema (
                             user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(100) NOT NULL,
                             contents TEXT,
                             user VARCHAR(50) NOT NULL,
                             password VARCHAR(255) NOT NULL,
                             created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                             updated_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);