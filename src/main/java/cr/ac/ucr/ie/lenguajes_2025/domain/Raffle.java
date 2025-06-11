/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author josia
 */

@Entity
@Table(name = "raffle")
public class Raffle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRaffle")
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private String conditions;

    @Column(name = "raffleDate")
    private LocalDate raffledate;

    @Column(length = 25, nullable = false)
    private String status;  // Valid values: open, closed, finished

     @Column(name = "imageUrl", length = 255)
    private String imageurl;

    @Column(name = "created_at")
    private LocalDate creationdate;

    public Raffle() {}
    
    public Raffle(int id, String title, String description, String conditions, LocalDate raffledate, String status, String imageurl, LocalDate creationdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.conditions = conditions;
        this.raffledate = raffledate;
        this.status = status;
        this.imageurl = imageurl;
        this.creationdate = creationdate;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public LocalDate getRaffledate() {
        return raffledate;
    }

    public void setRaffledate(LocalDate raffledate) {
        this.raffledate = raffledate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public LocalDate getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(LocalDate creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public String toString() {
        return "Raffle{" + "id=" + id + ", title=" + title + ", description=" + description + ", conditions=" + conditions + ", raffledate=" + raffledate + ", status=" + status + ", imageurl=" + imageurl + ", creationdate=" + creationdate + '}';
    }
}
