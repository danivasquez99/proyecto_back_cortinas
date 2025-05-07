package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.UserDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public class UserDAOImplement implements UserDAO {

    @Override
    public LinkedList<User> getAll() {
        LinkedList<User> usersList = new LinkedList<User>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idUser, name, birthdate, email, password, ");
        sql.append("urlProfilePicture, role, isActive, creationDate ");
        sql.append("FROM user WHERE isActive = 1");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            User user;

            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setBirthdate(LocalDate.parse(rs.getString(3)));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setUrlProfilePicture(rs.getString(6));
                user.setRole(rs.getString(7));
                user.setIsActive(rs.getString(8) == "1" ? true : false);
                user.setCreationDate(LocalDate.parse(rs.getString(9)));

                usersList.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar todos los usuarios: " + e.getMessage());
        }

        return usersList;
    }

    @Override
    public void insert(User t) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO user");
        sql.append("(idUser, name, birthdate, email, password, ");
        sql.append("urlProfilePicture, role, creationDate) ");
        sql.append("VALUES (?,?,?,?,?,?,?,?)");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t.getIdUser());
            ps.setString(2, t.getName());
            ps.setDate(3, Date.valueOf(t.getBirthdate()));
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getPassword());
            ps.setString(6, t.getUrlProfilePicture());
            ps.setString(7, t.getRole());
            ps.setString(8, t.isIsActive() ? "1" : "0");
            ps.setDate(9, Date.valueOf(t.getCreationDate()));

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    @Override
    public void update(User t) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE user SET ");
        sql.append("idUser=?, name=?, birthdate=?, email=?, password=?, ");
        sql.append("urlProfilePicture=?, role=?, isActive=? ");
        sql.append("WHERE idUser=?");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t.getIdUser());
            ps.setString(2, t.getName());
            ps.setDate(3, Date.valueOf(t.getBirthdate()));
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getPassword());
            ps.setString(6, t.getUrlProfilePicture());
            ps.setString(7, t.getRole());
            ps.setString(8, t.isIsActive() ? "1" : "0");

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer t) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE user SET isActive=0 ");
        sql.append("WHERE idUser=?");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t);

            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @Override
    public User findById(Integer t) {
        User user = new User();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT user idUser, name, birthdate, email, password, ");
        sql.append("urlProfileImage, role, isActive, creationDate");
        sql.append("WHERE idUser=?");

        try {

            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdUser(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setBirthdate(LocalDate.parse(rs.getDate(3).toString()));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setUrlProfilePicture(rs.getString(6));
                user.setRole(rs.getString(7));
                user.setIsActive(rs.getString(8).equals("1") ? true : false);
                user.setCreationDate(LocalDate.parse(rs.getDate(9).toString()));
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar usuario: " + e.getMessage());
        }

        return user;
    }

    @Override
    public boolean validateExistingEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
