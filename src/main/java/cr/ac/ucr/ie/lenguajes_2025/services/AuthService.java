/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.UserDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;

/**
 *
 * @author Josías Morales
 */

public class AuthService {
    private static UserDAOImplement userDAO = new UserDAOImplement();

    //Simulación de login: retorna el usuario si email y password coinciden
    public User authenticate(String email, String password) {
        for (User user : userDAO.getAll()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        
        return null;
    }

    // Verifica si el usuario es admin
    public boolean isAdmin(User user) {
        return user != null && "admin".equalsIgnoreCase(user.getRole());
    }

    // Verifica si el usuario es cliente
    public boolean isCliente(User user) {
        return user != null && "cliente".equalsIgnoreCase(user.getRole());
    }
}
