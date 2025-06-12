package cr.ac.ucr.ie.lenguajes_2025.repository;

import cr.ac.ucr.ie.lenguajes_2025.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProduct_IdProduct(int idProduct);
}
