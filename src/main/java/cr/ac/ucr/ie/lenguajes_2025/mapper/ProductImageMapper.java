package cr.ac.ucr.ie.lenguajes_2025.mapper;

import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import cr.ac.ucr.ie.lenguajes_2025.dto.ProductImageDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductImageMapper {

    public ProductImageDTO toDTO(ProductImage entity) {
        ProductImageDTO dto = new ProductImageDTO();
        dto.setIdImage(entity.getIdImage());
        dto.setFileName(entity.getFileName());
        dto.setUrlPath(entity.getUrlPath());
        dto.setUploadedAt(entity.getUploadedAt());
        dto.setProductId(entity.getProduct().getIdProduct());
        return dto;
    }
}
