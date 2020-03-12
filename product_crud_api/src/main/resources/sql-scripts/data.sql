INSERT INTO roles (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO roles (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO users (id, first_name, last_name, password, username) VALUES (1, 'Priya', 'Jain', '$2a$09$RQwiT9yYqebV3TEX6o9o/.5qMjyRZ2PlpFC.RSJlx0O8KR0gmtoJK', 'priya.jain');
INSERT INTO users (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '$2a$09$RQwiT9yYqebV3TEX6o9o/.5qMjyRZ2PlpFC.RSJlx0O8KR0gmtoJK', 'admin.admin');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

-- Populate random product items in table

INSERT INTO product(id, name,current_price,last_update) VALUES (1, 'Milk','25.50','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (2, 'Bread','12.50','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (3, 'Coffee','5.50','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (4, 'Tea','10.00','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (5, 'Cheese','50.50','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (6, 'Yogurt','35.00','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (7, 'Ice-Cream','25.00','2020-03-11 18:54:45.174000');
INSERT INTO product(id, name,current_price,last_update) VALUES (8, 'Whey','40.00','2020-03-11 18:54:45.174000');