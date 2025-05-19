package cr.ac.ucr.ie.lenguajes_2025.domain;

import java.sql.Date;

public class Service {

    private int id;
    private String name;
    private String category; // Ej: "Instalación", "Asesoría"
    private String description;
    private float estimatedCost;
    private String estimatedDuration; // Ej: "02:30:00"
    private char status; // 'A' = Available, 'N' = Not Available
    private Date creationDate;
    private String imageUrl;


    public Service() {
    }

    public Service(int id, String name, String category, String description, float estimatedCost, String estimatedDuration, char status, Date creationDate, String imageUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.estimatedCost = estimatedCost;
        this.estimatedDuration = estimatedDuration;
        this.status = status;
        this.creationDate = creationDate;
        this.imageUrl = imageUrl;
    }
    
    

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(float estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(String estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}
