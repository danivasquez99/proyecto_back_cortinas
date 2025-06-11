package static.images.promotions;
import java.nio.file.Paths;

public static void updatePromotionWithImage(Promotion promotion, MultipartFile imageFile) throws Exception {
    // Obtener la promoción actual desde la base de datos
    Promotion existingPromotion = service.findById(promotion.getIdPromotion());

    if (imageFile != null && !imageFile.isEmpty()) {
        // Obtener URL de la imagen anterior
        String oldImageUrl = existingPromotion.getImageUrl();

        // Guardar la nueva imagen en disco
        String newImageUrl = PromotionImageService.savePromotionImage(imageFile);

        // Asignar la nueva URL a la promoción
        promotion.setImageUrl(newImageUrl);

        // Eliminar imagen anterior si existía
        if (oldImageUrl != null && !oldImageUrl.isBlank()) {
            // Extraer solo el nombre del archivo de la URL
            String fileName = Paths.get(oldImageUrl).getFileName().toString();
            Utils.deleteFile("static/images/promotions", fileName);
        }
    } else {
        // No se subió una nueva imagen, mantener la actual
        promotion.setImageUrl(existingPromotion.getImageUrl());
    }

    // Actualizar la promoción con los nuevos datos
    service.update(promotion);
}
