package cr.ac.ucr.ie.lenguajes_2025.domain;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tony
 */

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_tracking")
public class OrderTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrderTracking;

    @ManyToOne
    @JoinColumn(name = "idOrder", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "idAdmin", nullable = false)
    private User admin;

    @ManyToOne
    @JoinColumn(name = "idClient", nullable = false)
    private User client;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters y setters

    public Integer getIdOrderTracking() {
        return idOrderTracking;
    }

    public void setIdOrderTracking(Integer idOrderTracking) {
        this.idOrderTracking = idOrderTracking;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
