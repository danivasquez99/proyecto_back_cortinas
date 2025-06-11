/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.RolePermissionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.PermissionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import cr.ac.ucr.ie.lenguajes_2025.domain.RolePermission;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class RolePermissionService {
    private final RolePermissionDAOImplement rolePermissionDAO = new RolePermissionDAOImplement();
    private final PermissionDAOImplement permissionDAO = new PermissionDAOImplement();

    public LinkedList<RolePermission> getAllRolePermissions() {
        return rolePermissionDAO.getAll();
    }

    public LinkedList<RolePermission> getPermissionsByRoleId(int roleId) {
        return rolePermissionDAO.findByRoleId(roleId);
    }

    public LinkedList<RolePermission> getRolesByPermissionId(int permissionId) {
        return rolePermissionDAO.findByPermissionId(permissionId);
    }

    public void assignPermissionToRole(RolePermission rolePermission) {
        rolePermissionDAO.insert(rolePermission);
    }

    public void removePermissionFromRole(int roleId, int permissionId) {
        rolePermissionDAO.delete(roleId, permissionId);
    }
    
    public LinkedList<Permission> getPermissionsDetailsByRoleId(int roleId) {
        LinkedList<RolePermission> rolePerms = rolePermissionDAO.findByRoleId(roleId);
        LinkedList<Permission> permissions = new LinkedList<>();
        for (RolePermission rp : rolePerms) {
            Permission p = permissionDAO.findById(rp.getPermissionId());
            if (p != null) permissions.add(p);
        }
        return permissions;
    }
}
