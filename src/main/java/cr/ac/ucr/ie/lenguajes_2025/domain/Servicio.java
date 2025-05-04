package cr.ac.ucr.ie.lenguajes_2025.domain;
import java.util.Date;

public class Servicio {

    private int id;
    private String name;
    private String category;
    private String description;
    private float estimatedCost;
    private String estimatedDuration;
    private String status; 
    private Date creationDate;

    public Servicio() {
    }

    public Servicio(int id, String name, String category, String description, float estimatedCost,
                    String estimatedDuration, String status, Date creationDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.estimatedCost = estimatedCost;
        this.estimatedDuration = estimatedDuration;
        this.status = status;
        this.creationDate = creationDate;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
