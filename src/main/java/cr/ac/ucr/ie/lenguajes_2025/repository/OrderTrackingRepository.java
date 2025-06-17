/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.repository;

/**
 *
 * @author Tony
 */

import cr.ac.ucr.ie.lenguajes_2025.domain.OrderTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTrackingRepository extends JpaRepository<OrderTracking, Integer> {

    // Opcional: obtener seguimientos por admin
    List<OrderTracking> findByAdminIdUser(Integer idAdmin);

    // Opcional: obtener seguimientos por cliente
    List<OrderTracking> findByClientIdUser(Integer idClient);

    // Opcional: obtener seguimientos por orden
    List<OrderTracking> findByOrderIdOrder(Integer idOrder);
}
