/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserRoleDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.RolePermissionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.PermissionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import cr.ac.ucr.ie.lenguajes_2025.domain.RolePermission;
import cr.ac.ucr.ie.lenguajes_2025.domain.Permission;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class AuthService {
    private static UserDAOImplement userDAO = new UserDAOImplement();
    private static UserRoleDAOImplement userRoleDAO = new UserRoleDAOImplement();
    private static RolePermissionDAOImplement rolePermissionDAO = new RolePermissionDAOImplement();
    private static PermissionDAOImplement permissionDAO = new PermissionDAOImplement();

    //Simulación de login: retorna el usuario si email y password coinciden
    public User authenticate(String email, String password) {
        for (User user : userDAO.getAll()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        
        return null;
    }
    
    //Verifica si el usuario tiene un permiso específico (por nombre)
    public boolean hasPermission(User user, String permissionName) {
        if (user == null) {
            return false;
        }

        // 1. Obtener roles del usuario
        LinkedList<UserRole> userRoles = userRoleDAO.findByUserId(user.getIdUser());
       
        for (UserRole userRole : userRoles) {
            int roleId = userRole.getRoleId();

            // 2. Obtener permisos de cada rol
            LinkedList<RolePermission> rolePerms = rolePermissionDAO.findByRoleId(roleId);
            
            for (RolePermission rolePerm : rolePerms) {
                int permId = rolePerm.getPermissionId();

                // 3. Obtener el permiso y comparar el nombre
                Permission perm = permissionDAO.findById(permId);
                
                if (perm != null && perm.getName().equalsIgnoreCase(permissionName)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
