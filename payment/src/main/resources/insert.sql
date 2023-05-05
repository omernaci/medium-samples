
INSERT INTO account (id, account_number, account_holder_name, balance)
VALUES
    (1, '1234567890', 'John Doe', 1000),
    (2, '0987654321', 'Jane Doe', 5000),
    (3, '2468013579', 'Bob Smith', 2500),
    (4, '1357924680', 'Alice Johnson', 1500),
    (5, '9753108642', 'Tom Wilson', 2000);

INSERT INTO payment (id, reference_number, amount, created_date, processed_date, payment_status, source_account_id, destination_account_id, remarks)
VALUES
    (1, 'PAY0001', 100, '2022-01-01 00:00:00', '2022-01-01 01:00:00', 'COMPLETED', 1, 2, 'Payment for goods'),
    (2, 'PAY0002', 250, '2022-01-02 00:00:00', NULL, 'PENDING', 2, 3, 'Rent payment'),
    (3, 'PAY0003', 50, '2022-01-03 00:00:00', '2022-01-03 00:30:00', 'COMPLETED', 3, 1, 'Transfer to friend'),
    (4, 'PAY0004', 300, '2022-01-04 00:00:00', NULL, 'PENDING', 4, 2, 'Payment for services'),
    (5, 'PAY0005', 75, '2022-01-05 00:00:00', '2022-01-05 02:00:00', 'COMPLETED', 5, 1, 'Repayment of loan');
