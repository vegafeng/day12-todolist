-- V20250911170000__Create_table_company.sql

CREATE TABLE todos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       text VARCHAR(255) NOT NULL,
                       done BOOLEAN NOT NULL DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
