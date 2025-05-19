/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public interface UserRoleDAO extends CRUD<UserRole> {
    // Métodos extra para relaciones
    LinkedList<UserRole> findByUserId(Integer userId);
    LinkedList<UserRole> findByRoleId(Integer roleId);
    void deleteByUserId(int userId);
    void deleteByRoleId(int roleId);
    void delete(Integer userId, Integer roleId);
}
