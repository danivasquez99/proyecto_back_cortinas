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
