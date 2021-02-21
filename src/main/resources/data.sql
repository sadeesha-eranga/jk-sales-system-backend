INSERT INTO branch (id, name, type, email, created_at, updated_at, status) VALUES(1, 'Head Office', 'HEAD_OFFICE', 'msadeeshaeranga@gmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');
INSERT INTO branch_user(id, name, username, password, created_at, updated_at, branch_id) VALUES(1, 'Admin', 'admin', '$2y$12$vZUL2x2eTS9woA4Ozcs5veLoUXFQ4Xb1/iSY66aoDSStoyd7i6iYa', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

