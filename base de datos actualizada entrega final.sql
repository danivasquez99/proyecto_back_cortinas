
CREATE DATABASE IF NOT EXISTS lenguajes_2025;
USE lenguajes_2025;

-- Crear tablas
DROP TABLE IF EXISTS `order`, product, promotion, quotation, raffle, service, user;

CREATE TABLE `order` (
  idOrder INT NOT NULL AUTO_INCREMENT,
  status VARCHAR(25),
  orderDate DATE,
  estimatedDeliveryDate DATE,
  total FLOAT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idOrder)
);

CREATE TABLE product (
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

CREATE TABLE promotion (
  idPromotion INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45),
  discount FLOAT,
  startDate DATE,
  endDate DATE,
  imageUrl VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idPromotion)
);

CREATE TABLE quotation (
  idQuotation INT NOT NULL AUTO_INCREMENT,
  userId INT NOT NULL,
  description VARCHAR(255),
  estimatedTotal FLOAT,
  status VARCHAR(20),
  requestDate DATE,
  responseDate DATE,
  creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (idQuotation),
  FOREIGN KEY (userId) REFERENCES user(idUser) ON DELETE CASCADE
);

CREATE TABLE raffle (
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

CREATE TABLE service (
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

CREATE TABLE user (
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

INSERT INTO user (name, birthdate, email, password, urlProfilePicture, role) VALUES
('Tony ML', '2000-10-10', 'tony@example.com', '123456', 'https://picsum.photos/100', 'admin'),
('angel', '2002-12-29', 'angel@gmail.com', '00000000', 'https://picsum.photos/100', 'admin'),
('luis', '2000-10-10', 'luis@gmail.com', '00000000', 'https://picsum.photos/100', 'admin'),
('Ana López', '1995-03-22', 'ana@example.com', 'pass123', 'https://picsum.photos/101', 'cliente');

INSERT INTO product (name, details, price, stock, imageUrl, entryDate) VALUES
('Decoración de pared', 'Cuadro decorativo moderno 60×90 cm', 25000.00, 20, 'https://picsum.photos/300/200?random=1', CURDATE()),
('Luces LED decorativas', 'Tira LED RGB 5 m con control remoto', 12000.00, 30, 'https://picsum.photos/300/200?random=2', CURDATE()),
('Alfombra grande', 'Alfombra 200×300 cm, poliéster', 86000.00, 10, 'https://picsum.photos/300/200?random=3', CURDATE()),
('Alfombra runner', 'Runner 80×200 cm, gris', 25000.00, 15, 'https://picsum.photos/300/200?random=4', CURDATE()),
('Puerta plegable madera', 'Puerta interior plegable madera 70×200 cm', 100000.00, 5, 'https://picsum.photos/300/200?random=5', CURDATE()),
('Puerta plástica', 'Puerta PVC blanca 70×200 cm', 45000.00, 8, 'https://picsum.photos/300/200?random=6', CURDATE()),
('Closet varilla', 'Closet rack metálico y cortina 120 cm', 55000.00, 12, 'https://picsum.photos/300/200?random=7', CURDATE()),
('Closet madera', 'Armario madera maciza 180 cm', 180000.00, 4, 'https://picsum.photos/300/200?random=8', CURDATE()),
('Mueble sala', 'Mueble TV moderno 150 cm', 95000.00, 6, 'https://picsum.photos/300/200?random=9', CURDATE()),
('Cortina tela blackout', 'Cortina blackout 54×90 cm negra', 9795.01, 25, 'https://picsum.photos/300/200?random=10', CURDATE()),
('Cortina tergal', 'Cortina de tela tergal 140×220 cm', 15000.00, 20, 'https://picsum.photos/300/200?random=11', CURDATE()),
('Cenefa decorativa', 'Cenefa bordada 10×300 cm', 12000.00, 18, 'https://picsum.photos/300/200?random=12', CURDATE()),
('Persiana automática', 'Persiana enrollable motorizada 120×180 cm', 180000.00, 3, 'https://picsum.photos/300/200?random=13', CURDATE()),
('Persiana madera manual', 'Persiana veneciana madera 100×120 cm', 49900.00, 10, 'https://picsum.photos/300/200?random=14', CURDATE());

INSERT INTO promotion (title, discount, startDate, endDate, imageUrl) VALUES
('Descuento de Verano', 10.00, '2025-05-01', '2025-05-31', 'https://picsum.photos/202');

INSERT INTO promotion (title, discount, startDate, endDate, imageUrl) VALUES
('Promo Día del Trabajador', 15.00, '2025-05-01', '2025-05-05', 'https://picsum.photos/seed/trabajador/200'),
('Semana del Medio Ambiente', 12.50, '2025-06-03', '2025-06-08', 'https://picsum.photos/seed/ambiente/200'), -- 5 junio
('Especial Anexión de Guanacaste', 20.00, '2025-07-20', '2025-07-25', 'https://picsum.photos/seed/guanacaste/200'), -- 25 julio
('Descuento Día de la Independencia', 18.00, '2025-09-13', '2025-09-15', 'https://picsum.photos/seed/independencia/200'), -- 15 sept.
('Sorteo del Día de las Culturas', 10.00, '2025-10-10', '2025-10-13', 'https://picsum.photos/seed/culturas/200'); -- 12 octubre

INSERT INTO raffle (title, description, conditions, raffleDate, status, imageUrl) VALUES
('Sorteo Mes de Mamá', 'Ganá un makeover completo para la sala', 'Hacer una compra superior a ₡75,000', '2025-08-15', 'Activo', 'https://picsum.photos/seed/mama/200'),
('Sorteo Patrio', 'Premio especial por celebrar la independencia', 'Comprar cualquier cortina tricolor', '2025-09-15', 'Activo', 'https://picsum.photos/seed/patrio/200'),
('Rifa Navidad Deco', 'Entrá en la rifa de fin de año', 'Cualquier compra entre el 15 y 24 de diciembre', '2025-12-24', 'Activo', 'https://picsum.photos/seed/navidad/200');

INSERT INTO service (name, category, description, estimatedCost, estimatedDuration) VALUES
('Cotización e instalación de cortinas', 'Instalación', 'Visita a domicilio para cotizar e instalar cortinas personalizadas según el espacio', 25000.00, '01:30:00'),
('Mantenimiento de cortinas', 'Servicio técnico', 'Limpieza, revisión y ajuste de cortinas instaladas en el hogar u oficina', 15000.00, '01:00:00'),
('Alfombrado personalizado', 'Decoración', 'Medición e instalación de alfombras a medida según el espacio y estilo del cliente', 30000.00, '02:00:00'),
('Diseño de interiores', 'Asesoría', 'Asesoría profesional para combinar colores, materiales y distribución de cortinas y muebles', 40000.00, '02:30:00');

INSERT INTO `order` (status, orderDate, estimatedDeliveryDate, total) VALUES
('Pendiente', '2025-06-01', '2025-06-06', 25450.00),
('En proceso', '2025-06-03', '2025-06-10', 39990.00),
('Cancelado', '2025-05-20', '2025-05-25', 17500.00),
('Entregado', '2025-04-15', '2025-04-20', 89900.00),
('Entregado', '2025-03-30', '2025-04-02', 124995.00);

INSERT INTO quotation (userId, description, estimatedTotal, status, requestDate, responseDate, creationDate) VALUES
(1, 'Cotización para cortinas blackout en sala y habitación principal', 68900.00, 'Pendiente', '2025-06-08', NULL, NOW()),
(2, 'Cotización de alfombrado completo en oficina y pasillo', 102000.00, 'Aprobado', '2025-06-01', '2025-06-03', NOW()),
(3, 'Instalación de cortinas manuales y una persiana automática', 85900.00, 'Rechazado', '2025-05-28', '2025-05-30', NOW()),
(1, 'Cotización para asesoría en diseño de interiores', 40000.00, 'Aprobado', '2025-06-05', '2025-06-06', NOW()),
(4, 'Mantenimiento de cortinas en tres habitaciones', 45000.00, 'Pendiente', '2025-06-09', NULL, NOW());

-- Consultas de prueba

SELECT * FROM user;
SELECT * FROM product;
SELECT * FROM promotion;
SELECT * FROM quotation;
SELECT * FROM raffle;
SELECT * FROM service;
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
DELIMITER //
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
( 'angel', 'angel@gmail.com', '00000000'),
( 'tony', 'tony@gmail.com', '00000000'),
( 'luis', 'luis@gmail.com', '00000000');


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




-- 1. Limpiar roles existentes de los usuarios (por si ya tienen otros)
DELETE FROM user_role
WHERE user_id IN (
    SELECT idUser FROM user WHERE email IN ('angel@gmail.com', 'tony@gmail.com', 'luis@gmail.com')
);

-- 2. Asegurar que el rol Admin tenga todos los permisos solo una vez
DELETE FROM role_permission
WHERE role_id = (SELECT id FROM role WHERE name = 'Admin');

-- 3. Reinsertar permisos del Admin (solo si querés restaurarlos)
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'Admin';

-- 4. Asignar rol Admin a los 3 usuarios (los únicos que importan)
INSERT INTO user_role (user_id, role_id)
SELECT u.idUser, r.id
FROM user u, role r
WHERE r.name = 'Admin'
AND u.email IN ('angel@gmail.com', 'tony@gmail.com', 'luis@gmail.com');


RENAME TABLE `order` TO order_table;

SHOW CREATE TABLE order_table;

-- Procedimientos almacenados de Angel (servicios y cotizaciones)
-- service:
DELIMITER //

CREATE PROCEDURE sp_get_all_service()
BEGIN
    SELECT * FROM service;
END //

CREATE PROCEDURE sp_insert_service(
    IN p_name VARCHAR(45),
    IN p_category VARCHAR(25),
    IN p_description VARCHAR(200),
    IN p_estimatedCost FLOAT,
    IN p_estimatedDuration TIME
)
BEGIN
    INSERT INTO service(name, category, description, estimatedCost, estimatedDuration)
    VALUES (p_name, p_category, p_description, p_estimatedCost, p_estimatedDuration);
END //

CREATE PROCEDURE sp_update_service(
    IN p_idService INT,
    IN p_name VARCHAR(45),
    IN p_category VARCHAR(25),
    IN p_description VARCHAR(200),
    IN p_estimatedCost FLOAT,
    IN p_estimatedDuration TIME
)
BEGIN
    UPDATE service
    SET name = p_name,
        category = p_category,
        description = p_description,
        estimatedCost = p_estimatedCost,
        estimatedDuration = p_estimatedDuration
    WHERE idService = p_idService;
END //

CREATE PROCEDURE sp_delete_service_by_id(IN p_idService INT)
BEGIN
    DELETE FROM service WHERE idService = p_idService;
END //

CREATE PROCEDURE sp_find_service_by_id(IN p_idService INT)
BEGIN
    SELECT * FROM service WHERE idService = p_idService;
END //

DELIMITER ;

-- quotation:
DELIMITER //

CREATE PROCEDURE sp_get_all_quotation()
BEGIN
    SELECT * FROM quotation;
END //

CREATE PROCEDURE sp_insert_quotation(
    IN p_userId INT,
    IN p_description VARCHAR(255),
    IN p_estimatedTotal FLOAT,
    IN p_status VARCHAR(20),
    IN p_requestDate DATE,
    IN p_responseDate DATE
)
BEGIN
    INSERT INTO quotation(userId, description, estimatedTotal, status, requestDate, responseDate)
    VALUES (p_userId, p_description, p_estimatedTotal, p_status, p_requestDate, p_responseDate);
END //

CREATE PROCEDURE sp_update_quotation(
    IN p_idQuotation INT,
    IN p_userId INT,
    IN p_description VARCHAR(255),
    IN p_estimatedTotal FLOAT,
    IN p_status VARCHAR(20),
    IN p_requestDate DATE,
    IN p_responseDate DATE
)
BEGIN
    UPDATE quotation
    SET userId = p_userId,
        description = p_description,
        estimatedTotal = p_estimatedTotal,
        status = p_status,
        requestDate = p_requestDate,
        responseDate = p_responseDate
    WHERE idQuotation = p_idQuotation;
END //

CREATE PROCEDURE sp_delete_quotation_by_id(IN p_idQuotation INT)
BEGIN
    DELETE FROM quotation WHERE idQuotation = p_idQuotation;
END //

CREATE PROCEDURE sp_find_quotation_by_id(IN p_idQuotation INT)
BEGIN
    SELECT * FROM quotation WHERE idQuotation = p_idQuotation;
END //

DELIMITER ;
