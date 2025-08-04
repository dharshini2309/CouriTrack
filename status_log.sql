CREATE DATABASE IF NOT EXISTS CourierTrackDB;
USE CourierTrackDB;

-- Create couriers table
CREATE TABLE IF NOT EXISTS couriers (
    tracking_id VARCHAR(50) PRIMARY KEY,
    sender_name VARCHAR(100),
    receiver_name VARCHAR(100),
    status VARCHAR(100) DEFAULT 'Pending'
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    role VARCHAR(20)
);

-- Insert default users
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'admin'),
('staff1', 'staff123', 'staff')
ON DUPLICATE KEY UPDATE 
    password = VALUES(password),
    role = VALUES(role);

-- Create status_log table
CREATE TABLE IF NOT EXISTS status_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tracking_id VARCHAR(50),
    status VARCHAR(100),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
SELECT * FROM couriers;

-- SET SQL_SAFE_UPDATES = 0;

-- USE CourierTrackDB;
-- DELETE FROM couriers;
-- DELETE FROM status_log;

