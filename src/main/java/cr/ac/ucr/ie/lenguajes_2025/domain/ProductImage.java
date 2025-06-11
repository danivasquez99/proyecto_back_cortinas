package cr.ac.ucr.ie.lenguajes_2025.domain;

import java.time.LocalDateTime;

public class ProductImage {
    private int idImage;
    private int productId;
    private String fileName;
    private String urlPath;
    private LocalDateTime uploadedAt;

    public ProductImage() {
    }

    public ProductImage(int idImage, int productId, String fileName, String urlPath, LocalDateTime uploadedAt) {
        this.idImage = idImage;
        this.productId = productId;
        this.fileName = fileName;
        this.urlPath = urlPath;
        this.uploadedAt = uploadedAt;
    }

  
    
    public int getIdImage() { return idImage; }
    public void setIdImage(int idImage) { this.idImage = idImage; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getUrlPath() { return urlPath; }
    public void setUrlPath(String urlPath) { this.urlPath = urlPath; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
}
