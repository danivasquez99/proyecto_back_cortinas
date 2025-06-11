package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.connection.ConnectionDB;
import cr.ac.ucr.ie.lenguajes_2025.dao.PromotionDAO;
import cr.ac.ucr.ie.lenguajes_2025.domain.Promotion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Daniel
 */
public class PromotionDAOImplement implements PromotionDAO {

    @Override
    public LinkedList<Promotion> getAll() {
        LinkedList<Promotion> promotions = new LinkedList<>();

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement cs = cn.prepareCall("CALL sp_get_all_promotions()");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Promotion promotion = new Promotion();
                promotion.setIdPromotion(rs.getInt("idPromotion"));
                promotion.setTitle(rs.getString("title"));
                promotion.setDiscount(rs.getFloat("discount"));
                promotion.setStartDate(rs.getDate("startDate").toLocalDate());
                promotion.setEndDate(rs.getDate("endDate").toLocalDate());
                promotion.setImageUrl(rs.getString("imageUrl"));
                promotion.setCreatedAt(rs.getDate("created_at").toLocalDate());
                promotions.add(promotion);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las promociones: " + e.getMessage());
        }

        return promotions;
    }

    @Override
    public void insert(Promotion t) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_insert_promotion(?, ?, ?, ?, ?)");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareCall(sql.toString());

            ps.setString(1, t.getTitle());
            ps.setFloat(2, t.getDiscount());
            ps.setDate(3, Date.valueOf(t.getStartDate()));
            ps.setDate(4, Date.valueOf(t.getEndDate()));
            ps.setString(5, t.getImageUrl());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar la promoción: " + e.getMessage());
        }
    }

    @Override
    public void update(Promotion t) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_update_promotion(?,?,?,?,?,?)");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareCall(sql.toString());

            ps.setInt(1, t.getIdPromotion());
            ps.setString(2, t.getTitle());
            ps.setFloat(3, t.getDiscount());
            ps.setDate(4, Date.valueOf(t.getStartDate().toString()));
            ps.setDate(5, Date.valueOf(t.getEndDate().toString()));
            ps.setString(6, t.getImageUrl());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar la promoción: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer idPromotion) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_delete_promotion(?)");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareCall(sql.toString());

            ps.setInt(1, idPromotion);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar la promoción: " + e.getMessage());
        }
    }

    @Override
    public Promotion findById(Integer idPromotion) {
        Promotion promotion = new Promotion();
        StringBuilder sql = new StringBuilder();
        sql.append("CALL sp_find_promotion_by_id(?)");

        try {
            Connection cn = ConnectionDB.getConnection();
            PreparedStatement ps = cn.prepareCall(sql.toString());
            ps.setInt(1, idPromotion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                promotion = new Promotion();
                promotion.setIdPromotion(rs.getInt("idPromotion"));
                promotion.setTitle(rs.getString("title"));
                promotion.setDiscount(rs.getFloat("discount"));
                promotion.setStartDate(rs.getDate("startDate").toLocalDate());
                promotion.setEndDate(rs.getDate("endDate").toLocalDate());
                promotion.setImageUrl(rs.getString("imageUrl"));
                promotion.setCreatedAt(rs.getDate("created_at").toLocalDate());
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar la promoción: " + e.getMessage());
        }

        return promotion;
    }

    public Promotion getLastInsertedPromotion() {
        Promotion promotion = null;
        String sql = "SELECT * FROM promotion ORDER BY idPromotion DESC LIMIT 1";

        try (Connection cn = ConnectionDB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                promotion = new Promotion();
                promotion.setIdPromotion(rs.getInt("idPromotion"));
                promotion.setTitle(rs.getString("title"));
                promotion.setDiscount(rs.getFloat("discount"));
                promotion.setStartDate(rs.getDate("startDate").toLocalDate());
                promotion.setEndDate(rs.getDate("endDate").toLocalDate());
                promotion.setImageUrl(rs.getString("imageUrl"));
                promotion.setCreatedAt(rs.getDate("created_at").toLocalDate());
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la última promoción: " + e.getMessage());
        }

        return promotion;
    }

}
