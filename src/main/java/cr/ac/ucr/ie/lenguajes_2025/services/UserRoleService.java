/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserRoleDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.UserRole;
import java.util.LinkedList;

/**
 *
 * @author Josías Morales
 */

public class UserRoleService {
    private final UserRoleDAOImplement userRoleDAO = new UserRoleDAOImplement();

    public LinkedList<UserRole> getAllUserRoles() {
        return userRoleDAO.getAll();
    }

    public LinkedList<UserRole> getRolesByUserId(int userId) {
        return userRoleDAO.findByUserId(userId);
    }

    public LinkedList<UserRole> getUsersByRoleId(int roleId) {
        return userRoleDAO.findByRoleId(roleId);
    }

    public void assignRoleToUser(UserRole userRole) {
        userRoleDAO.insert(userRole);
    }

    public void removeRoleFromUser(int userId, int roleId) {
        userRoleDAO.delete(userId, roleId);
    }
}
