INSERT INTO Product (name, price) VALUES ('Smartphone', 699.99);
INSERT INTO Product (name, price) VALUES ('Laptop', 1299.99);
INSERT INTO Product (name, price) VALUES ('Headphones', 149.99);

INSERT INTO Discount (discountPercentage, startDate, endDate, product_id) VALUES (15.00, '2022-01-01 00:00:00', '2022-01-15 23:59:59', 1);
INSERT INTO Discount (discountPercentage, startDate, endDate, product_id) VALUES (10.00, '2022-01-16 00:00:00', '2022-01-31 23:59:59', 1);
INSERT INTO Discount (discountPercentage, startDate, endDate, product_id) VALUES (20.00, '2022-02-01 00:00:00', '2022-02-28 23:59:59', 2);
INSERT INTO Discount (discountPercentage, startDate, endDate, product_id) VALUES (5.00, '2022-01-15 00:00:00', '2022-02-15 23:59:59', 3);