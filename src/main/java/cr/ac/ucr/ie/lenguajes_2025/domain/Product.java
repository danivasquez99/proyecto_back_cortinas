package cr.ac.ucr.ie.lenguajes_2025.domain;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private int idProduct;

    @Column(length = 25)
    private String name;

    @Column(length = 200)
    private String details;

    private float price;

    private int stock;

    @Column(name= "imageUrl",length = 255)
    private String imageUrl;

    @Column(name = "entryDate")
    private Date entryDate;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    public Product() {
    }

    public Product(int idProduct, String name, String details, float price, int stock, String imageUrl, Date entryDate, Timestamp createdAt) {
        this.idProduct = idProduct;
        this.name = name;
        this.details = details;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.entryDate = entryDate;
        this.createdAt = createdAt;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{"
                + "idProduct=" + idProduct
                + ", name='" + name + '\''
                + ", details='" + details + '\''
                + ", price=" + price
                + ", stock=" + stock
                + ", imageUrl='" + imageUrl + '\''
                + ", entryDate=" + entryDate
                + ", createdAt=" + createdAt
                + '}';
    }
}
