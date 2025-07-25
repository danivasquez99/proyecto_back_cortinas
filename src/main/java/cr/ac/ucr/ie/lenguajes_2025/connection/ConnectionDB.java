package cr.ac.ucr.ie.lenguajes_2025.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class ConnectionDB {
    
    private final static String DATABASE = "railway";
    private final static String USER = "root";
    private final static String PASSWORD = "lnsagDnAWqcbdHVNrKFZerJCpjcOyJSH";
    private final static String HOST = "switchback.proxy.rlwy.net";
    private final static int PORT = 22535;

    private final static String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE +
            "?useSSL=true&serverTimezone=UTC"; // agregado por seguridad y compatibilidad

    private static Connection connect;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al importar la clase: " + e.getMessage());
        }

        try {
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión establecida con Railway DB");
        } catch (SQLException e) {
            System.out.println("❌ Error al establecer la conexión: " + e.getMessage());
        }

        return connect;
    }
}
