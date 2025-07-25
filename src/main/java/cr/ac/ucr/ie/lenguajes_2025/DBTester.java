package cr.ac.ucr.ie.lenguajes_2025;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DBTester {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void testDBConnection() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM product")) {

            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("📦 PRODUCTOS ENCONTRADOS EN LA DB: " + count);
            }

        } catch (Exception e) {
            System.err.println("❌ Error conectando a la base desde Spring: " + e.getMessage());
        }
    }
}
