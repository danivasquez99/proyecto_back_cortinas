package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Promotion;
import cr.ac.ucr.ie.lenguajes_2025.repository.PromotionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@Service
public class PromotionService {

    private final PromotionRepository repo;
    private final PromotionImageService imageService;

    @Autowired
    public PromotionService(PromotionRepository repo, PromotionImageService imageService) {
        this.repo = repo;
        this.imageService = imageService;
    }

    public List<Promotion> getAllPromotions() {
        return repo.findAll();
    }

    public List<Promotion> getEnabledPromotions() {
        return repo.getEnabledPromotions();
    }
    
    public Promotion getPromotionById(int id) {
        return repo.findById(id).get();
    }

    public void insertPromotion(Promotion newPromotion) {
        repo.save(newPromotion);
    }

    public void updatePromotion(Promotion modifyPromotion) {
        repo.save(modifyPromotion);
    }

    public void deletePromotion(int promotionId) {
        repo.deleteById(promotionId);
    }

    public void insertPromotionWithImage(Promotion promotion, MultipartFile imageFile) throws Exception {
        Promotion lastInsertedPromotion = repo.save(promotion);

        String imageUrl = PromotionImageService.savePromotionImage(imageFile);

        // 4. Asignar URL a la promoción y actualizarla
        lastInsertedPromotion.setImageUrl(imageUrl);
        repo.save(lastInsertedPromotion);
    }

    public void updatePromotionWithImage(Promotion promotion, MultipartFile imageFile) throws Exception {
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = PromotionImageService.savePromotionImage(imageFile);
            promotion.setImageUrl(imageUrl);
        } else {
            Promotion existing = repo.findById(promotion.getIdPromotion()).get();
            promotion.setImageUrl(existing.getImageUrl()); // <- mantener la imagen actual
        }
        repo.save(promotion);
    }

}
