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