-- Crear base de datos
CREATE DATABASE IF NOT EXISTS `lenguajes-2025`;
USE `lenguajes-2025`;

-- Crear tablas
DROP TABLE IF EXISTS `order`, `product`, `promotion`, `quotation`, `raffle`, `service`, `user`;

CREATE TABLE `order` (
  idOrder INT NOT NULL AUTO_INCREMENT,
  status VARCHAR(25),
  orderDate DATE,
  estimatedDeliveryDate DATE,
  total FLOAT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idOrder)
);

CREATE TABLE `product` (
  idProduct INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(25),
  details VARCHAR(200),
  price FLOAT,
  stock INT,
  imageUrl VARCHAR(255),
  entryDate DATE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idProduct)
);

CREATE TABLE `promotion` (
  idPromotion INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45),
  discount FLOAT,
  startDate DATE,
  endDate DATE,
  imageUrl VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idPromotion)
);

CREATE TABLE `quotation` (
  idQuotation INT NOT NULL AUTO_INCREMENT,
  description VARCHAR(255),
  estimatedCost FLOAT,
  status VARCHAR(20),
  requestDate DATE,
  responseDate DATE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idQuotation)
);

CREATE TABLE `raffle` (
  idRaffle INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(50),
  description VARCHAR(255),
  conditions VARCHAR(255),
  raffleDate DATE,
  status VARCHAR(25),
  imageUrl VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idRaffle)
);

CREATE TABLE `service` (
  idService INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45),
  category VARCHAR(25),
  description VARCHAR(200),
  estimatedCost FLOAT,
  estimatedDuration TIME,
  status CHAR(1) DEFAULT '1',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idService)
);

CREATE TABLE `user` (
  idUser INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100),
  birthdate DATE,
  email VARCHAR(45),
  password VARCHAR(45),
  urlProfilePicture VARCHAR(255),
  role VARCHAR(20) DEFAULT 'cliente',
  isActive CHAR(1) DEFAULT '1',
  creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idUser)
);

-- Insertar datos de prueba

INSERT INTO `user` (name, birthdate, email, password, urlProfilePicture, role) VALUES
('Tony ML', '2000-10-10', 'tony@example.com', '123456', 'https://picsum.photos/100', 'admin'),
('Ana López', '1995-03-22', 'ana@example.com', 'pass123', 'https://picsum.photos/101', 'cliente');

INSERT INTO `product` (name, details, price, stock, imageUrl, entryDate) VALUES
('Camiseta', 'Camiseta de algodón', 15.99, 100, 'https://picsum.photos/200', '2025-01-01'),
('Taza', 'Taza de cerámica', 8.50, 50, 'https://picsum.photos/201', '2025-01-10');

INSERT INTO `promotion` (title, discount, startDate, endDate, imageUrl) VALUES
('Descuento de Verano', 10.00, '2025-05-01', '2025-05-31', 'https://picsum.photos/202');

INSERT INTO `quotation` (description, estimatedCost, status, requestDate, responseDate) VALUES
('Cotización de productos personalizados', 120.00, 'Pendiente', '2025-04-20', NULL),
('Cotización urgente', 250.00, 'Aprobado', '2025-04-25', '2025-04-26');

INSERT INTO `raffle` (title, description, conditions, raffleDate, status, imageUrl) VALUES
('Sorteo Día del Cliente', 'Participa y gana un premio', 'Comprar mínimo $50', '2025-06-15', 'Activo', 'https://picsum.photos/203');

INSERT INTO `service` (name, category, description, estimatedCost, estimatedDuration) VALUES
('Envío Express', 'Logística', 'Envío en menos de 24 horas', 20.00, '01:00:00'),
('Diseño Personalizado', 'Diseño', 'Servicio de diseño gráfico', 50.00, '02:30:00');

INSERT INTO `order` (status, orderDate, estimatedDeliveryDate, total) VALUES
('En proceso', '2025-05-01', '2025-05-05', 35.99),
('Entregado', '2025-04-28', '2025-05-02', 99.99);

-- Consultas de prueba

SELECT * FROM `user`;

SELECT * FROM `product`;
SELECT * FROM `promotion`;
SELECT * FROM `quotation`;
SELECT * FROM `raffle`;
SELECT * FROM `service`;
SELECT * FROM `order`;

-- Procedimientos almacenados para la tabla product

DELIMITER //

CREATE PROCEDURE sp_get_all_product()
BEGIN
    SELECT * FROM product;
END;
//

CREATE PROCEDURE sp_insert_product(
    IN p_name VARCHAR(25),
    IN p_details VARCHAR(200),
    IN p_price FLOAT,
    IN p_stock INT,
    IN p_imageUrl VARCHAR(255),
    IN p_entryDate DATE
)
BEGIN
    INSERT INTO product(name, details, price, stock, imageUrl, entryDate)
    VALUES (p_name, p_details, p_price, p_stock, p_imageUrl, p_entryDate);
END;
//

CREATE PROCEDURE sp_update_product(
    IN p_idProduct INT,
    IN p_name VARCHAR(25),
    IN p_details VARCHAR(200),
    IN p_price FLOAT,
    IN p_stock INT,
    IN p_imageUrl VARCHAR(255),
    IN p_entryDate DATE
)
BEGIN
    UPDATE product
    SET name = p_name,
        details = p_details,
        price = p_price,
        stock = p_stock,
        imageUrl = p_imageUrl,
        entryDate = p_entryDate
    WHERE idProduct = p_idProduct;
END;
//

CREATE PROCEDURE sp_delete_product_by_id(IN p_idProduct INT)
BEGIN
    DELETE FROM product WHERE idProduct = p_idProduct;
END;
//

CREATE PROCEDURE sp_find_product_by_id(IN p_idProduct INT)
BEGIN
    SELECT * FROM product WHERE idProduct = p_idProduct;
END;
//

-- Procedimientos almacenados para la tabla order

CREATE PROCEDURE sp_get_all_order()
BEGIN
    SELECT * FROM `order`;
END;
//

CREATE PROCEDURE sp_insert_order(
    IN p_status VARCHAR(25),
    IN p_orderDate DATE,
    IN p_estimatedDeliveryDate DATE,
    IN p_total FLOAT
)
BEGIN
    INSERT INTO `order`(status, orderDate, estimatedDeliveryDate, total)
    VALUES (p_status, p_orderDate, p_estimatedDeliveryDate, p_total);
END;
//

CREATE PROCEDURE sp_update_order(
    IN p_idOrder INT,
    IN p_status VARCHAR(25),
    IN p_orderDate DATE,
    IN p_estimatedDeliveryDate DATE,
    IN p_total FLOAT
)
BEGIN
    UPDATE `order`
    SET status = p_status,
        orderDate = p_orderDate,
        estimatedDeliveryDate = p_estimatedDeliveryDate,
        total = p_total
    WHERE idOrder = p_idOrder;
END;
//

CREATE PROCEDURE sp_delete_order_by_id(IN p_idOrder INT)
BEGIN
    DELETE FROM `order` WHERE idOrder = p_idOrder;
END;
//

CREATE PROCEDURE sp_find_order_by_id(IN p_idOrder INT)
BEGIN
    SELECT * FROM `order` WHERE idOrder = p_idOrder;
END;
//

DELIMITER ;


-- Pato

-- Tablas para roles y permisos
CREATE TABLE role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE permission (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE role_permission (
  role_id INT NOT NULL,
  permission_id INT NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
  FOREIGN KEY (permission_id) REFERENCES permission(id) ON DELETE CASCADE
);

CREATE TABLE user_role (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES user(idUser) ON DELETE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);


-- PROCEDIMIENTOS PARA ROLES
DELIMITER //
CREATE PROCEDURE sp_get_all_roles()
BEGIN
    SELECT id, name FROM role;
END //

CREATE PROCEDURE sp_insert_role(IN p_name VARCHAR(100))
BEGIN
    INSERT INTO role (name) VALUES (p_name);
END //

CREATE PROCEDURE sp_update_role(IN p_id INT, IN p_name VARCHAR(100))
BEGIN
    UPDATE role SET name = p_name WHERE id = p_id;
END //

CREATE PROCEDURE sp_delete_role(IN p_id INT)
BEGIN
    DELETE FROM role WHERE id = p_id;
END //

CREATE PROCEDURE sp_find_role_by_id(IN p_id INT)
BEGIN
    SELECT id, name FROM role WHERE id = p_id;
END //

CREATE PROCEDURE sp_find_role_by_name(IN p_name VARCHAR(100))
BEGIN
    SELECT id, name FROM role WHERE name = p_name;
END //
DELIMITER ;

-- PROCEDIMIENTOS PARA PERMISOS
DELIMITER //
CREATE PROCEDURE sp_get_all_permissions()
BEGIN
    SELECT id, name FROM permission;
END //

CREATE PROCEDURE sp_insert_permission(IN p_name VARCHAR(100))
BEGIN
    INSERT INTO permission (name) VALUES (p_name);
END //

CREATE PROCEDURE sp_update_permission(IN p_id INT, IN p_name VARCHAR(100))
BEGIN
    UPDATE permission SET name = p_name WHERE id = p_id;
END //

CREATE PROCEDURE sp_delete_permission(IN p_id INT)
BEGIN
    DELETE FROM permission WHERE id = p_id;
END //

CREATE PROCEDURE sp_find_permission_by_id(IN p_id INT)
BEGIN
    SELECT id, name FROM permission WHERE id = p_id;
END //

CREATE PROCEDURE sp_find_permission_by_name(IN p_name VARCHAR(100))
BEGIN
    SELECT id, name FROM permission WHERE name = p_name;
END //
DELIMITER ;

-- PROCEDIMIENTOS PARA ROLE_PERMISSION
DELIMITER //
CREATE PROCEDURE sp_get_all_role_permissions()
BEGIN
    SELECT role_id, permission_id FROM role_permission;
END //

CREATE PROCEDURE sp_insert_role_permission(IN p_role_id INT, IN p_permission_id INT)
BEGIN
    INSERT INTO role_permission (role_id, permission_id) VALUES (p_role_id, p_permission_id);
END //

CREATE PROCEDURE sp_delete_role_permission(IN p_role_id INT, IN p_permission_id INT)
BEGIN
    DELETE FROM role_permission WHERE role_id = p_role_id AND permission_id = p_permission_id;
END //

CREATE PROCEDURE sp_find_role_permissions_by_role(IN p_role_id INT)
BEGIN
    SELECT role_id, permission_id FROM role_permission WHERE role_id = p_role_id;
END //

CREATE PROCEDURE sp_find_role_permissions_by_permission(IN p_permission_id INT)
BEGIN
    SELECT role_id, permission_id FROM role_permission WHERE permission_id = p_permission_id;
END //

CREATE PROCEDURE sp_delete_role_permissions_by_role(IN p_role_id INT)
BEGIN
    DELETE FROM role_permission WHERE role_id = p_role_id;
END //

CREATE PROCEDURE sp_delete_role_permissions_by_permission(IN p_permission_id INT)
BEGIN
    DELETE FROM role_permission WHERE permission_id = p_permission_id;
END //

CREATE PROCEDURE sp_exists_role_permission(IN p_role_id INT, IN p_permission_id INT)
BEGIN
    SELECT COUNT(*) FROM role_permission WHERE role_id = p_role_id AND permission_id = p_permission_id;
END //
DELIMITER ;

-- PROCEDIMIENTOS PARA USER_ROLE
DELIMITER //
CREATE PROCEDURE sp_get_all_user_roles()
BEGIN
    SELECT user_id, role_id FROM user_role;
END //

CREATE PROCEDURE sp_insert_user_role(IN p_user_id INT, IN p_role_id INT)
BEGIN
    INSERT INTO user_role (user_id, role_id) VALUES (p_user_id, p_role_id);
END //

CREATE PROCEDURE sp_delete_user_role(IN p_user_id INT, IN p_role_id INT)
BEGIN
    DELETE FROM user_role WHERE user_id = p_user_id AND role_id = p_role_id;
END //

CREATE PROCEDURE sp_find_user_roles_by_user(IN p_user_id INT)
BEGIN
    SELECT user_id, role_id FROM user_role WHERE user_id = p_user_id;
END //

CREATE PROCEDURE sp_find_user_roles_by_role(IN p_role_id INT)
BEGIN
    SELECT user_id, role_id FROM user_role WHERE role_id = p_role_id;
END //

CREATE PROCEDURE sp_delete_user_roles_by_user(IN p_user_id INT)
BEGIN
    DELETE FROM user_role WHERE user_id = p_user_id;
END //

CREATE PROCEDURE sp_delete_user_roles_by_role(IN p_role_id INT)
BEGIN
    DELETE FROM user_role WHERE role_id = p_role_id;
END //

CREATE PROCEDURE sp_exists_user_role(IN p_user_id INT, IN p_role_id INT)
BEGIN
    SELECT COUNT(*) FROM user_role WHERE user_id = p_user_id AND role_id = p_role_id;
END //
DELIMITER ;

INSERT INTO user ( name, email, password) VALUES
( 'admin_user', 'admin@example.com', 'admin123'),
( 'john_doe', 'john@example.com', 'john123'),
( 'jane_smith', 'jane@example.com', 'jane123'),
( 'guest_user', 'guest@example.com', 'guest123');


INSERT INTO role (name) VALUES
('Admin'),
('Editor'),
('Viewer'),
('Guest');

INSERT INTO permission (name) VALUES
('create_user'),
('edit_user'),
('delete_user'),
('view_user'),
('assign_roles'),
('manage_permissions');

-- Admin tiene todos los permisos
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'Admin';

-- Editor puede editar y ver usuarios
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p 
WHERE r.name = 'Editor' AND p.name IN ('edit_user', 'view_user');

-- Viewer solo puede ver
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p 
WHERE r.name = 'Viewer' AND p.name = 'view_user';

-- admin_user -> Admin
INSERT INTO user_role (user_id, role_id)
SELECT 1, id FROM role WHERE name = 'Admin';

-- john_doe -> Editor
INSERT INTO user_role (user_id, role_id)
SELECT 2, id FROM role WHERE name = 'Editor';

-- jane_smith -> Viewer
INSERT INTO user_role (user_id, role_id)
SELECT 3, id FROM role WHERE name = 'Viewer';

-- guest_user -> Guest
INSERT INTO user_role (user_id, role_id)
SELECT 4, id FROM role WHERE name = 'Guest';


RENAME TABLE `order` TO `order_table`;

SHOW CREATE TABLE order_table;
