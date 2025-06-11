package cr.ac.ucr.ie.lenguajes_2025.domain;

import java.time.LocalDate;

/**
 *
 * @author Daniel
 */
public class Promotion {

    private int idPromotion;
    private String title;
    private float discount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String imageUrl;
    private LocalDate createdAt;

    public Promotion() {
    }

    public Promotion(int idPromotion, String title, float discount, LocalDate startDate, LocalDate endDate, String imageUrl, LocalDate createdAt) {
        this.idPromotion = idPromotion;
        this.title = title;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    // toString (opcional)
    @Override
    public String toString() {
        return "Promotion{"
                + "idPromotion=" + idPromotion
                + ", title='" + title + '\''
                + ", discount=" + discount
                + ", startDate=" + startDate
                + ", endDate=" + endDate
                + ", imageUrl='" + imageUrl + '\''
                + ", createdAt=" + createdAt
                + '}';
    }
}
