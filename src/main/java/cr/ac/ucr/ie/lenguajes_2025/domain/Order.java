package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @Column(length = 25)
    private String status;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "estimatedDeliveryDate")
    private Date estimatedDeliveryDate;

    private Float total;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Date createdAt;

    public Order() {
    }

    public Order(Integer idOrder, String status, Date orderDate, Date estimatedDeliveryDate, Float total, Date createdAt) {
        this.idOrder = idOrder;
        this.status = status;
        this.orderDate = orderDate;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.total = total;
        this.createdAt = createdAt;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
