CREATE TABLE Product (
             id BIGINT PRIMARY KEY AUTO_INCREMENT,
             name VARCHAR(255),
             price DECIMAL(10, 2)
);

CREATE TABLE Discount (
              id BIGINT PRIMARY KEY AUTO_INCREMENT,
              discountPercentage DECIMAL(10, 2),
              startDate DATETIME,
              endDate DATETIME,
              product_id BIGINT,
              FOREIGN KEY (product_id) REFERENCES Product(id)
);