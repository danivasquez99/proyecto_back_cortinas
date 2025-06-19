package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.UserDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.User;
import cr.ac.ucr.ie.lenguajes_2025.utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
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
        sql.append("CALL sp_get_all_users();");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();

            User user;

            while (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setBirthdate(rs.getDate(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setUrlProfilePicture(rs.getString(6));
                user.setRole(rs.getString(7));
                user.setIsActive("1".equals(rs.getString(8)));
                user.setCreatedAt(Timestamp.valueOf(rs.getDate(9).toString()));
                
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
        sql.append("CALL sp_insert_user(?,?,?,?,?);");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, t.getName());
            ps.setDate(2, t.getBirthdate());
            ps.setString(3, t.getEmail());
            ps.setString(4, Utils.encryptSHA256(t.getPassword()));
            ps.setString(5, t.getUrlProfilePicture());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
        }
    }

    @Override
    public void update(User t) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_update_user(?,?,?,?,?,?);");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t.getIdUser());
            ps.setString(2, t.getName());
            ps.setDate(3, t.getBirthdate());
            ps.setString(4, t.getEmail());
            ps.setString(5, Utils.encryptSHA256(t.getPassword()));
            ps.setString(6, t.getUrlProfilePicture());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer t) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_delete_user(?);");

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
        sql.append("CALL sp_find_user_by_id(?);");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdUser(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setBirthdate(rs.getDate(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setUrlProfilePicture(rs.getString(6));
                user.setRole(rs.getString(7));
                user.setIsActive("1".equals(rs.getString(8)));
                user.setCreatedAt(Timestamp.from(Instant.MIN));
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar usuario: " + e.getMessage());
        }

        return user;
    }

    public User getLastInsertedUser() {
        User user = null;
        String sql = "SELECT * FROM user ORDER BY idUser DESC LIMIT 1";

        try (Connection cn = ConnectionDB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                user = new User();
                user.setIdUser(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setBirthdate(rs.getDate(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setUrlProfilePicture(rs.getString(6));
                user.setRole(rs.getString(7));
                user.setIsActive("1".equals(rs.getString(8)));
                user.setCreatedAt(Timestamp.valueOf(rs.getDate(9).toString()));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el último usuario: " + e.getMessage());
        }

        return user;
    }
    
    @Override
    public boolean validateExistingEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User login(String email, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_login_user(?,?);");

        User userLogin = new User();
        String encryptPassword = Utils.encryptSHA256(password);

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, email);
            ps.setString(2, encryptPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userLogin.setIdUser(rs.getInt(1));
                userLogin.setName(rs.getString(2));
                userLogin.setBirthdate(rs.getDate(3));
                userLogin.setEmail(rs.getString(4));
                userLogin.setPassword(rs.getString(5));
                userLogin.setUrlProfilePicture(rs.getString(6));
                userLogin.setRole(rs.getString(7));
                userLogin.setIsActive(rs.getString(8).equals("1"));
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar usuario: " + e.getMessage());
        }

        return userLogin;
    }

}
