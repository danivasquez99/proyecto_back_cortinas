package cr.ac.ucr.ie.lenguajes_2025.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDTO {

    private int idProduct;
    private String name;
    private String details;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
    private LocalDate entryDate;
    private String createdAt;

    public ProductDTO() {
    }

    public ProductDTO(int idProduct, String name, String details, BigDecimal price, int stock,
                      String imageUrl, LocalDate entryDate, String createdAt) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
