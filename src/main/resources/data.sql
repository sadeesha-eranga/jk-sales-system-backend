INSERT INTO branch (id, name, type, email, created_at, updated_at, status)
    VALUES(1, 'Head Office', 'HEAD_OFFICE', 'msadeeshaeranga@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
INSERT INTO branch (id, name, type, email, created_at, updated_at, status)
    VALUES(2, 'Normal Branch 1', 'NORMAL', 'sadeeshae@ceyentra.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');

INSERT INTO branch_user(id, name, username, password, created_at, updated_at, branch_id)
    VALUES(1, 'Super Admin', 'super_admin', '$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
INSERT INTO branch_user(id, name, username, password, created_at, updated_at, branch_id)
    VALUES(2, 'Branch Admin', 'branch_admin', '$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO customer(id, name, nic, email)
    VALUES (1, 'Mary Jane', '123456789V', 'msadeeshaeranga@gmail.com');

INSERT INTO driver(id, name, nic, mobile)
    VALUES (1, 'John Doe', '123456789V', '0778536360');

INSERT INTO vehicle(id, vehicle_no, driver_id)
VALUES (1, 'ABC-1245', 1);

INSERT INTO product(id, name, unit)
    VALUES (1, 'Rice', 'Kg');
INSERT INTO product(id, name, unit)
    VALUES (2, 'Flour', 'g');

INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (1, 100, 100, 75.00, 1, 1);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (2, 100, 100, 100.00, 1, 2);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (3, 100, 100, 75.00, 2, 1);
INSERT INTO stock(id, total_qty, remaining_qty, unit_price, branch_id, product_id)
    VALUES (4, 100, 100, 100.00, 2, 2);