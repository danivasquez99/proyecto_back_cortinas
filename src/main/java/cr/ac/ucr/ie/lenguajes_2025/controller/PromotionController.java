package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Promotion;
import cr.ac.ucr.ie.lenguajes_2025.services.PromotionService;
import java.sql.Date;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping("api/promotions")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionController {

    private final PromotionService promotionService;
    
    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
    
    @GetMapping("/list")
    public Map getList() {
        return Collections.singletonMap("data", promotionService.getAllPromotions());
    }

    @GetMapping("/enabled")
    public Map getEnabledPromotions() {
        return Collections.singletonMap("data", promotionService.getEnabledPromotions());
    }
    
    @GetMapping("/getById")
    public Promotion findPromotionById(@RequestParam int promotionId) {
        return promotionService.getPromotionById(promotionId);
    }

    @PostMapping("/create")
    public Map insertPromotion(@RequestBody Promotion promotion) {
        promotionService.insertPromotion(promotion);
        return getList();
    }

    @PostMapping("/create/with-image")
    public ResponseEntity<Void> insertPromotionWithImage(
            @RequestParam String title,
            @RequestParam float discount,
            @RequestParam Date startDate,
            @RequestParam Date endDate,
            @RequestParam("image") MultipartFile image) {
        try {
            Promotion promotion = new Promotion();
            promotion.setTitle(title);
            promotion.setDiscount(discount);
            promotion.setStartDate(startDate);
            promotion.setEndDate(endDate);

            try {
                // Usar el método existente que maneja la inserción completa
                promotionService.insertPromotionWithImage(promotion, image);

                // Crear ubicación del recurso con URL absoluta
                String locationPath = "/api/promotions/" + promotion.getIdPromotion();
                return ResponseEntity.status(HttpStatus.CREATED)
                        .header("Location", locationPath)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update")
    public Map updatePromotion(@RequestBody Promotion promotion) {
        promotionService.updatePromotion(promotion);
        return getList();
    }

    @PutMapping("/update/with-image")
    public ResponseEntity<Void> updatePromotionWithImage(
            @RequestParam int idPromotion,
            @RequestParam String title,
            @RequestParam float discount,
            @RequestParam Date startDate,
            @RequestParam Date endDate,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            Promotion promotion = new Promotion();
            promotion.setIdPromotion(idPromotion);
            promotion.setTitle(title);
            promotion.setDiscount(discount);
            promotion.setStartDate(startDate);
            promotion.setEndDate(endDate);

            // Servicio se encarga de actualizar imagen si viene una nueva
            promotionService.updatePromotionWithImage(promotion, image);

            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete")
    public Map deletePromotion(@RequestParam int promotionId) {
        promotionService.deletePromotion(promotionId);
        return getList();
    }
}
