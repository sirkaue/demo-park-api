insert into USUARIOS (id, username, password, role) values (100, 'ana@email.com', '$2a$10$rSlfuU0wd0xxjpw8D8QOVuOOdPYFFeeRBp5D0gRLVdnjXZGjvexC6', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role) values (101, 'bob@email.com', '$2a$10$rSlfuU0wd0xxjpw8D8QOVuOOdPYFFeeRBp5D0gRLVdnjXZGjvexC6', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role) values (102, 'bia@email.com', '$2a$10$rSlfuU0wd0xxjpw8D8QOVuOOdPYFFeeRBp5D0gRLVdnjXZGjvexC6', 'ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (103, 'jon@email.com', '$2a$10$rSlfuU0wd0xxjpw8D8QOVuOOdPYFFeeRBp5D0gRLVdnjXZGjvexC6', 'ROLE_CLIENTE');

insert into CLIENTES (id, nome, cpf, id_usuario) values (10, 'Roberto Gomes', '61731194048', 101);
insert into CLIENTES (id, nome, cpf, id_usuario) values (20, 'Bianca Silva', '21685552064', 102);
