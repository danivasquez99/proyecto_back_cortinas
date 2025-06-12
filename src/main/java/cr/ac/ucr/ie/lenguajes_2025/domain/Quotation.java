package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "quotation")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuotation")
    private int idQuotation;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(length = 255)
    private String description;

    private float estimatedTotal;

    @Column(length = 20)
    private String status;

    private Date requestDate;

    private Date responseDate;

    @Column(name = "creationDate", insertable = false, updatable = false)
    private Timestamp creationDate;

    public Quotation() {}

    public Quotation(int idQuotation, int userId, String description, float estimatedTotal, String status, Date requestDate, Date responseDate, Timestamp creationDate) {
        this.idQuotation = idQuotation;
        this.userId = userId;
        this.description = description;
        this.estimatedTotal = estimatedTotal;
        this.status = status;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.creationDate = creationDate;
    }

    public int getIdQuotation() {
        return idQuotation;
    }

    public void setIdQuotation(int idQuotation) {
        this.idQuotation = idQuotation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(float estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
