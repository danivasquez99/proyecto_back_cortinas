package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_material")
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 150)
    private String colors;

    private double pricePerM2;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getColors() { return colors; }
    public void setColors(String colors) { this.colors = colors; }

    public double getPricePerM2() { return pricePerM2; }
    public void setPricePerM2(double pricePerM2) { this.pricePerM2 = pricePerM2; }
}
