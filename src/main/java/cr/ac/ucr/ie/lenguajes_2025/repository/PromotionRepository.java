package cr.ac.ucr.ie.lenguajes_2025.repository;

import cr.ac.ucr.ie.lenguajes_2025.domain.Promotion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Daniel
 */
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    
    @Query(value = "CALL sp_get_enabled_promotions()", nativeQuery = true)
    public abstract List<Promotion> getEnabledPromotions();
}
