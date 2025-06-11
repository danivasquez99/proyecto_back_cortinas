package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.ProductDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;

import java.sql.*;
import java.util.LinkedList;

public class ProductDAOImplement implements ProductDAO {

    @Override
    public LinkedList<Product> getAll() {
        LinkedList<Product> productList = new LinkedList<>();
        String sql = "{CALL sp_get_all_product()}";

        try (Connection cn = ConnectionDB.getConnection(); CallableStatement cs = cn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setName(rs.getString("name"));
                product.setDetails(rs.getString("details"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setEntryDate(rs.getDate("entryDate"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                productList.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }

        return productList;
    }

    @Override
    public void insert(Product product) {
        String sql = "{CALL sp_insert_product(?, ?, ?, ?, ?, ?)}";

        try (Connection cn = ConnectionDB.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setString(1, product.getName());
            cs.setString(2, product.getDetails());
            cs.setFloat(3, product.getPrice());
            cs.setInt(4, product.getStock());
            cs.setString(5, product.getImageUrl());
            cs.setDate(6, product.getEntryDate());

            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        String sql = "{CALL sp_update_product(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection cn = ConnectionDB.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, product.getIdProduct());
            cs.setString(2, product.getName());
            cs.setString(3, product.getDetails());
            cs.setFloat(4, product.getPrice());
            cs.setInt(5, product.getStock());
            cs.setString(6, product.getImageUrl());
            cs.setDate(7, product.getEntryDate());

            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idProduct) {
        String sql = "{CALL sp_delete_product_by_id(?)}";

        try (Connection cn = ConnectionDB.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, idProduct);
            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Integer idProduct) {
        Product product = null;
        String sql = "{CALL sp_find_product_by_id(?)}";

        try (Connection cn = ConnectionDB.getConnection(); CallableStatement cs = cn.prepareCall(sql)) {

            cs.setInt(1, idProduct);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setName(rs.getString("name"));
                product.setDetails(rs.getString("details"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setEntryDate(rs.getDate("entryDate"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }

        return product;
    }

    public Product getLastInsertedProduct() {
        Product product = null;
        String sql = "SELECT * FROM product ORDER BY idProduct DESC LIMIT 1";

        try (Connection cn = ConnectionDB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt("idProduct"));
                product.setName(rs.getString("name"));
                product.setDetails(rs.getString("details"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                product.setImageUrl(rs.getString("imageUrl"));
                product.setEntryDate(rs.getDate("entryDate"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el último producto: " + e.getMessage());
        }

        return product;
    }

}
