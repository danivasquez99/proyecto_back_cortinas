/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao;

import cr.ac.ucr.ie.lenguajes_2025.domain.RolePermission;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public interface RolePermissionDAO extends CRUD<RolePermission> {
    // Métodos extra para relaciones
    LinkedList<RolePermission> findByRoleId(Integer roleId);
    LinkedList<RolePermission> findByPermissionId(Integer permissionId);
    void deleteByRoleId(int roleId);
    void deleteByPermissionId(int permissionId);
    void delete(Integer roleId, Integer permissionId);
}
