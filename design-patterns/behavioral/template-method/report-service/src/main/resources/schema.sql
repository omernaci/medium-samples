CREATE TABLE IF NOT EXISTS task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    status VARCHAR(20) NOT NULL
);
