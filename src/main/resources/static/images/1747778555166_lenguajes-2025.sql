-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2025 a las 22:56:29
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `lenguajes-2025`
--
CREATE DATABASE IF NOT EXISTS `lenguajes-2025` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `lenguajes-2025`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `sp_delete_order_by_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_order_by_id` (IN `p_idOrder` INT)   BEGIN
    DELETE FROM `order` WHERE idOrder = p_idOrder;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_permission` (IN `p_id` INT)   BEGIN
    DELETE FROM permission WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_product_by_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_product_by_id` (IN `p_idProduct` INT)   BEGIN
    DELETE FROM product WHERE idProduct = p_idProduct;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_role_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_role_permission` (IN `p_role_id` INT, IN `p_permission_id` INT)   BEGIN
    DELETE FROM role_permission WHERE role_id = p_role_id AND permission_id = p_permission_id;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_role_permissions_by_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_role_permissions_by_permission` (IN `p_permission_id` INT)   BEGIN
    DELETE FROM role_permission WHERE permission_id = p_permission_id;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_role_permissions_by_role`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_role_permissions_by_role` (IN `p_role_id` INT)   BEGIN
    DELETE FROM role_permission WHERE role_id = p_role_id;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_user_roles_by_role`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_user_roles_by_role` (IN `p_role_id` INT)   BEGIN
    DELETE FROM user_role WHERE role_id = p_role_id;
END$$

DROP PROCEDURE IF EXISTS `sp_delete_user_roles_by_user`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_user_roles_by_user` (IN `p_user_id` INT)   BEGIN
    DELETE FROM user_role WHERE user_id = p_user_id;
END$$

DROP PROCEDURE IF EXISTS `sp_exists_role_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_exists_role_permission` (IN `p_role_id` INT, IN `p_permission_id` INT)   BEGIN
    SELECT COUNT(*) FROM role_permission WHERE role_id = p_role_id AND permission_id = p_permission_id;
END$$

DROP PROCEDURE IF EXISTS `sp_find_order_by_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_order_by_id` (IN `p_idOrder` INT)   BEGIN
    SELECT * FROM `order` WHERE idOrder = p_idOrder;
END$$

DROP PROCEDURE IF EXISTS `sp_find_permission_by_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_permission_by_id` (IN `p_id` INT)   BEGIN
    SELECT id, name FROM permission WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `sp_find_permission_by_name`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_permission_by_name` (IN `p_name` VARCHAR(100))   BEGIN
    SELECT id, name FROM permission WHERE name = p_name;
END$$

DROP PROCEDURE IF EXISTS `sp_find_product_by_id`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_product_by_id` (IN `p_idProduct` INT)   BEGIN
    SELECT * FROM product WHERE idProduct = p_idProduct;
END$$

DROP PROCEDURE IF EXISTS `sp_find_role_permissions_by_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_role_permissions_by_permission` (IN `p_permission_id` INT)   BEGIN
    SELECT role_id, permission_id FROM role_permission WHERE permission_id = p_permission_id;
END$$

DROP PROCEDURE IF EXISTS `sp_find_role_permissions_by_role`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_role_permissions_by_role` (IN `p_role_id` INT)   BEGIN
    SELECT role_id, permission_id FROM role_permission WHERE role_id = p_role_id;
END$$

DROP PROCEDURE IF EXISTS `sp_find_user_roles_by_role`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_user_roles_by_role` (IN `p_role_id` INT)   BEGIN
    SELECT user_id, role_id FROM user_role WHERE role_id = p_role_id;
END$$

DROP PROCEDURE IF EXISTS `sp_find_user_roles_by_user`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_find_user_roles_by_user` (IN `p_user_id` INT)   BEGIN
    SELECT user_id, role_id FROM user_role WHERE user_id = p_user_id;
END$$

DROP PROCEDURE IF EXISTS `sp_get_all_order`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_order` ()   BEGIN
    SELECT * FROM `order`;
END$$

DROP PROCEDURE IF EXISTS `sp_get_all_permissions`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_permissions` ()   BEGIN
    SELECT id, name FROM permission;
END$$

DROP PROCEDURE IF EXISTS `sp_get_all_product`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_product` ()   BEGIN
    SELECT * FROM product;
END$$

DROP PROCEDURE IF EXISTS `sp_get_all_role_permissions`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_get_all_role_permissions` ()   BEGIN
    SELECT role_id, permission_id FROM role_permission;
END$$

DROP PROCEDURE IF EXISTS `sp_insert_order`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_order` (IN `p_status` VARCHAR(25), IN `p_orderDate` DATE, IN `p_estimatedDeliveryDate` DATE, IN `p_total` FLOAT)   BEGIN
    INSERT INTO `order`(status, orderDate, estimatedDeliveryDate, total)
    VALUES (p_status, p_orderDate, p_estimatedDeliveryDate, p_total);
END$$

DROP PROCEDURE IF EXISTS `sp_insert_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_permission` (IN `p_name` VARCHAR(100))   BEGIN
    INSERT INTO permission (name) VALUES (p_name);
END$$

DROP PROCEDURE IF EXISTS `sp_insert_product`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_product` (IN `p_name` VARCHAR(25), IN `p_details` VARCHAR(200), IN `p_price` FLOAT, IN `p_stock` INT, IN `p_imageUrl` VARCHAR(255), IN `p_entryDate` DATE)   BEGIN
    INSERT INTO product(name, details, price, stock, imageUrl, entryDate)
    VALUES (p_name, p_details, p_price, p_stock, p_imageUrl, p_entryDate);
END$$

DROP PROCEDURE IF EXISTS `sp_insert_role_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_role_permission` (IN `p_role_id` INT, IN `p_permission_id` INT)   BEGIN
    INSERT INTO role_permission (role_id, permission_id) VALUES (p_role_id, p_permission_id);
END$$

DROP PROCEDURE IF EXISTS `sp_update_order`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_order` (IN `p_idOrder` INT, IN `p_status` VARCHAR(25), IN `p_orderDate` DATE, IN `p_estimatedDeliveryDate` DATE, IN `p_total` FLOAT)   BEGIN
    UPDATE `order`
    SET status = p_status,
        orderDate = p_orderDate,
        estimatedDeliveryDate = p_estimatedDeliveryDate,
        total = p_total
    WHERE idOrder = p_idOrder;
END$$

DROP PROCEDURE IF EXISTS `sp_update_permission`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_permission` (IN `p_id` INT, IN `p_name` VARCHAR(100))   BEGIN
    UPDATE permission SET name = p_name WHERE id = p_id;
END$$

DROP PROCEDURE IF EXISTS `sp_update_product`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_product` (IN `p_idProduct` INT, IN `p_name` VARCHAR(25), IN `p_details` VARCHAR(200), IN `p_price` FLOAT, IN `p_stock` INT, IN `p_imageUrl` VARCHAR(255), IN `p_entryDate` DATE)   BEGIN
    UPDATE product
    SET name = p_name,
        details = p_details,
        price = p_price,
        stock = p_stock,
        imageUrl = p_imageUrl,
        entryDate = p_entryDate
    WHERE idProduct = p_idProduct;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `idOrder` int(11) NOT NULL,
  `status` varchar(25) DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `estimatedDeliveryDate` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `order`
--

INSERT INTO `order` (`idOrder`, `status`, `orderDate`, `estimatedDeliveryDate`, `total`, `created_at`) VALUES
(1, 'En proceso', '2025-05-01', '2025-05-05', 35.99, '2025-05-19 20:11:22'),
(3, 'CANCELADO', '2025-05-05', '2025-05-19', 5000, '2025-05-20 09:10:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permission`
--

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permission`
--

INSERT INTO `permission` (`id`, `name`) VALUES
(5, 'assign_roles'),
(1, 'create_user'),
(3, 'delete_user'),
(2, 'edit_user'),
(6, 'manage_permissions'),
(10, 'PRODUCT_CREATE'),
(12, 'PRODUCT_DELETE'),
(11, 'PRODUCT_UPDATE'),
(7, 'PRODUCT_VIEW'),
(4, 'view_user');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `idProduct` int(11) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `entryDate` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`idProduct`, `name`, `details`, `price`, `stock`, `imageUrl`, `entryDate`, `created_at`) VALUES
(2, 'Taza', 'Taza de cerámica', 20000, 50, NULL, '2025-01-09', '2025-05-19 20:11:10'),
(3, 'Camisetas', 'Algodon', 8978, 134, NULL, '2025-05-18', '2025-05-19 22:48:50'),
(5, 'SkoiceBot', 'Algodon', 231, 31, NULL, '2025-05-19', '2025-05-20 10:13:25'),
(6, 'Camiseta', 'Camiseta blanca de algodón', 19.99, 100, NULL, '2025-05-19', '2025-05-20 19:23:41'),
(7, 'Camiseta', 'Camiseta blanca de algodón', 19.99, 100, NULL, '2025-05-18', '2025-05-20 19:41:24'),
(8, 'Camiseta imagen', 'Camiseta blanca de algodón', 19.99, 100, 'http://localhost:8080/images/1747770320186_imagen1.jpg', NULL, '2025-05-20 19:45:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_image`
--

DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `idImage` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `url_path` varchar(500) NOT NULL,
  `uploaded_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `product_image`
--

INSERT INTO `product_image` (`idImage`, `product_id`, `file_name`, `url_path`, `uploaded_at`) VALUES
(2, 7, '1747770084567_Captura_de_pantalla_2025-04-06_120108.png', 'http://localhost:8080/images/1747770084567_Captura_de_pantalla_2025-04-06_120108.png', '2025-05-20 19:41:24'),
(3, 8, '1747770320186_imagen1.jpg', 'http://localhost:8080/images/1747770320186_imagen1.jpg', '2025-05-20 19:45:20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE `promotion` (
  `idPromotion` int(11) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `promotion`
--

INSERT INTO `promotion` (`idPromotion`, `title`, `discount`, `startDate`, `endDate`, `imageUrl`, `created_at`) VALUES
(1, 'Descuento de Verano', 10, '2025-05-01', '2025-05-31', 'https://picsum.photos/202', '2025-05-19 20:11:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `quotation`
--

DROP TABLE IF EXISTS `quotation`;
CREATE TABLE `quotation` (
  `idQuotation` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `estimatedCost` float DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `requestDate` date DEFAULT NULL,
  `responseDate` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `quotation`
--

INSERT INTO `quotation` (`idQuotation`, `description`, `estimatedCost`, `status`, `requestDate`, `responseDate`, `created_at`) VALUES
(1, 'Cotización de productos personalizados', 120, 'Pendiente', '2025-04-20', NULL, '2025-05-19 20:11:14'),
(2, 'Cotización urgente', 250, 'Aprobado', '2025-04-25', '2025-04-26', '2025-05-19 20:11:14');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `raffle`
--

DROP TABLE IF EXISTS `raffle`;
CREATE TABLE `raffle` (
  `idRaffle` int(11) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `conditions` varchar(255) DEFAULT NULL,
  `raffleDate` date DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `raffle`
--

INSERT INTO `raffle` (`idRaffle`, `title`, `description`, `conditions`, `raffleDate`, `status`, `imageUrl`, `created_at`) VALUES
(1, 'Sorteo Día del Cliente', 'Participa y gana un premio', 'Comprar mínimo $50', '2025-06-15', 'Activo', 'https://picsum.photos/203', '2025-05-19 20:11:17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'Admin'),
(2, 'Editor'),
(4, 'Guest'),
(3, 'Viewer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `role_permission`
--

INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES
(1, 7),
(1, 10),
(1, 11),
(1, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `idService` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `category` varchar(25) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `estimatedCost` float DEFAULT NULL,
  `estimatedDuration` time DEFAULT NULL,
  `status` char(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `imageUrl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `service`
--

INSERT INTO `service` (`idService`, `name`, `category`, `description`, `estimatedCost`, `estimatedDuration`, `status`, `created_at`, `imageUrl`) VALUES
(3, 'Cotizar instalación de cortinas y persianas', 'Cotización', 'Agenda una cita para medir ventanas y generar una cotización aproximada de cortinas o persianas.', 0, '01:00:00', '1', '2025-05-20 19:57:45', 'https://i.ibb.co/7JTPsFwj/Servicio-Cotizacion.jpg'),
(4, 'Mantenimiento de cortinas', 'Mantenimiento', 'Solicita una visita para el mantenimiento preventivo de tus cortinas o persianas.', 10000, '01:30:00', '1', '2025-05-20 19:57:45', 'https://i.ibb.co/XkpDp8Sn/Servicio-Mantenimiento.jpg'),
(5, 'Alfombrados', 'Instalación', 'Instalación o mantenimiento profesional de alfombras completas para tu hogar u oficina.', 35000, '02:00:00', '1', '2025-05-20 19:57:45', 'https://i.ibb.co/XZkGbpXf/Alfombras-Interiores.jpg'),
(6, 'Diseño de interiores', 'Diseño', 'Asesoría profesional para optimizar espacios: closets, muebles, puertas, hidrófugos y más.', 40000, '02:30:00', '1', '2025-05-20 19:57:45', 'https://i.ibb.co/wFgp84Tc/Servicio-Interiores.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `urlProfilePicture` varchar(255) DEFAULT NULL,
  `role` varchar(20) DEFAULT 'cliente',
  `isActive` char(1) DEFAULT '1',
  `creationDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`idUser`, `name`, `birthdate`, `email`, `password`, `urlProfilePicture`, `role`, `isActive`, `creationDate`) VALUES
(1, 'Antony', '2000-10-10', 'tony@gmail.com', 'tony123', 'https://picsum.photos/100', 'admin', '1', '2025-05-19 20:11:07'),
(2, 'Ana López', '1995-03-22', 'ana@example.com', 'pass123', 'https://picsum.photos/101', 'cliente', '1', '2025-05-19 20:11:07'),
(3, 'admin_user', NULL, 'admin@example.com', 'admin123', NULL, 'cliente', '1', '2025-05-19 20:29:45'),
(4, 'john_doe', NULL, 'john@example.com', 'john123', NULL, 'cliente', '1', '2025-05-19 20:29:45'),
(5, 'jane_smith', NULL, 'jane@example.com', 'jane123', NULL, 'cliente', '1', '2025-05-19 20:29:45'),
(6, 'guest_user', NULL, 'guest@example.com', 'guest123', NULL, 'cliente', '1', '2025-05-19 20:29:45');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`idOrder`);

--
-- Indices de la tabla `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idProduct`);

--
-- Indices de la tabla `product_image`
--
ALTER TABLE `product_image`
  ADD PRIMARY KEY (`idImage`),
  ADD KEY `idx_prodimg_product_id` (`product_id`);

--
-- Indices de la tabla `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`idPromotion`);

--
-- Indices de la tabla `quotation`
--
ALTER TABLE `quotation`
  ADD PRIMARY KEY (`idQuotation`);

--
-- Indices de la tabla `raffle`
--
ALTER TABLE `raffle`
  ADD PRIMARY KEY (`idRaffle`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `permission_id` (`permission_id`);

--
-- Indices de la tabla `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`idService`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `order`
--
ALTER TABLE `order`
  MODIFY `idOrder` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `permission`
--
ALTER TABLE `permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `idProduct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `product_image`
--
ALTER TABLE `product_image`
  MODIFY `idImage` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `promotion`
--
ALTER TABLE `promotion`
  MODIFY `idPromotion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `quotation`
--
ALTER TABLE `quotation`
  MODIFY `idQuotation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `raffle`
--
ALTER TABLE `raffle`
  MODIFY `idRaffle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `service`
--
ALTER TABLE `service`
  MODIFY `idService` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `product_image`
--
ALTER TABLE `product_image`
  ADD CONSTRAINT `fk_prodimg_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`idProduct`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE;

--
-- Filtros para la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`idUser`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
