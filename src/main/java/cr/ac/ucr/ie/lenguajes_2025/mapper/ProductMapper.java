package cr.ac.ucr.ie.lenguajes_2025.mapper;

import cr.ac.ucr.ie.lenguajes_2025.domain.Product;
import cr.ac.ucr.ie.lenguajes_2025.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        LocalDate entryDate = product.getEntryDate() != null
                ? product.getEntryDate().toLocalDate()
                : null;

        String createdAt = product.getCreatedAt() != null
                ? product.getCreatedAt().toString()
                : null;

        return new ProductDTO(
                product.getIdProduct(),
                product.getName(),
                product.getDetails(),
                BigDecimal.valueOf(product.getPrice()),
                product.getStock(),
                product.getImageUrl(),
                entryDate,
                createdAt
        );
    }
}
