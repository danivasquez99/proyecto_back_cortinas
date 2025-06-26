package cr.ac.ucr.ie.lenguajes_2025.dto;

import java.time.LocalDateTime;

public class ProductImageDTO {

    private int idImage;
    private String fileName;
    private String urlPath;
    private LocalDateTime uploadedAt;
    private int productId;

    public ProductImageDTO() {
    }

    public ProductImageDTO(int idImage, String fileName, String urlPath, LocalDateTime uploadedAt, int productId) {
        this.idImage = idImage;
        this.fileName = fileName;
        this.urlPath = urlPath;
        this.uploadedAt = uploadedAt;
        this.productId = productId;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
