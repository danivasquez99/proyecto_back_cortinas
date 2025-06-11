package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.dao_implement.PromotionDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Promotion;
import java.util.LinkedList;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
public class PromotionService {

    private static PromotionDAOImplement service = new PromotionDAOImplement();

    ;

    public PromotionService() {
    }

    public static LinkedList<Promotion> getAllPromotions() {
        return service.getAll();
    }

    public static Promotion getPromotionById(int id) {
        return service.findById(id);
    }

    public static void insertPromotion(Promotion newPromotion) {
        service.insert(newPromotion);
    }

    public static void updatePromotion(Promotion modifyPromotion) {
        service.update(modifyPromotion);
    }

    public static void deletePromotion(int promotionId) {
        service.deleteById(promotionId);
    }

    public static void insertPromotionWithImage(Promotion promotion, MultipartFile imageFile) throws Exception {
        // 1. Insertar primero la promoción sin imagen
        service.insert(promotion);

        // 2. Obtener la última promoción insertada (para conocer su ID)
        Promotion lastInsertedPromotion = service.getLastInsertedPromotion();

        // 3. Guardar la imagen en disco y obtener la URL
        String imageUrl = PromotionImageService.savePromotionImage(imageFile);

        // 4. Asignar URL a la promoción y actualizarla
        lastInsertedPromotion.setImageUrl(imageUrl);
        service.update(lastInsertedPromotion);
    }

    public static void updatePromotionWithImage(Promotion promotion, MultipartFile imageFile) throws Exception {
        if (imageFile != null && !imageFile.isEmpty()) {
            // 1. Guardar imagen nueva en disco
            String imageUrl = PromotionImageService.savePromotionImage(imageFile);

            // 2. Asignar la nueva URL
            promotion.setImageUrl(imageUrl);
        } else {
            // 3. Mantener la imagen actual si no se sube una nueva
            Promotion existingPromotion = service.findById(promotion.getIdPromotion());
            promotion.setImageUrl(existingPromotion.getImageUrl());
        }

        // 4. Actualizar promoción en DB
        service.update(promotion);
    }

}
