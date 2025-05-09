/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.dao_implement;


import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.ProductDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Product;

import java.sql.*;
import java.util.LinkedList;
/**
 *
 * @author Tony
 */


public class ProductDAOImplement implements ProductDAO {

    @Override
    public LinkedList<Product> getAll() {
        LinkedList<Product> productList = new LinkedList<>();

        String sql = "SELECT idProduct, name, details, price, stock, imageUrl, entryDate, created_at FROM product";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
        String sql = "INSERT INTO product (name, details, price, stock, imageUrl, entryDate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDetails());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImageUrl());
            ps.setDate(6, product.getEntryDate());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name=?, details=?, price=?, stock=?, imageUrl=?, entryDate=? WHERE idProduct=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDetails());
            ps.setFloat(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getImageUrl());
            ps.setDate(6, product.getEntryDate());
            ps.setInt(7, product.getIdProduct());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idProduct) {
        String sql = "DELETE FROM product WHERE idProduct=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProduct);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Integer idProduct) {
        Product product = null;
        String sql = "SELECT idProduct, name, details, price, stock, imageUrl, entryDate, created_at FROM product WHERE idProduct=?";

        try (Connection cn = ConnectionDB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProduct);
            ResultSet rs = ps.executeQuery();

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
}
