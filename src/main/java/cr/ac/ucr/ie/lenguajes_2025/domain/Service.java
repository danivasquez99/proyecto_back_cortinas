package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService")
    private int id;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 25)
    private String category;

    @Column(length = 200)
    private String description;

    @Column(name = "estimatedCost")
    private float estimatedCost;

    @Column(name = "estimatedDuration")
    private Time estimatedDuration;

    @Column(length = 1)
    private char status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;
    
    @Column(name = "imageUrl")
    private String imageUrl;


    public Service() {}

    public Service(int id, String name, String category, String description, float estimatedCost,
                   Time estimatedDuration, char status, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.estimatedCost = estimatedCost;
        this.estimatedDuration = estimatedDuration;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public float getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(float estimatedCost) { this.estimatedCost = estimatedCost; }

    public Time getEstimatedDuration() { return estimatedDuration; }
    public void setEstimatedDuration(Time estimatedDuration) { this.estimatedDuration = estimatedDuration; }

    public char getStatus() { return status; }
    public void setStatus(char status) { this.status = status; }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
