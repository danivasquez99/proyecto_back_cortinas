/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Raffle;
import cr.ac.ucr.ie.lenguajes_2025.repository.RaffleRepository;
import cr.ac.ucr.ie.lenguajes_2025.services.RaffleImageService;
import cr.ac.ucr.ie.lenguajes_2025.services.RaffleService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josia
 */

@RestController
@RequestMapping("/api/raffle")
@CrossOrigin(origins = "http://localhost:3000")
public class RaffleController {
    private final RaffleService raffleService;
    private final RaffleRepository raffleRepository;

    public RaffleController(RaffleService raffleService, RaffleRepository raffleRepository) {
        this.raffleService = raffleService;
        this.raffleRepository = raffleRepository;
    }

    // GET /api/raffle - Obtener todos los sorteos
    @GetMapping
    public List<Raffle> getAllRaffles() {
        return raffleService.getAllRaffles();
    }

    // GET /api/raffle/{id} - Obtener un sorteo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Raffle> getRaffleById(@PathVariable int id) {
        Raffle raffle = raffleService.findRaffleById(id);
        if (raffle != null) {
            return ResponseEntity.ok(raffle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createRaffle(@RequestBody Raffle raffle) {
    raffleService.addRaffle(raffle);
    return ResponseEntity.status(201).build(); // 201 Created
}
    
    @PostMapping("/create/with-image")
    public ResponseEntity<Void> createRaffleWithImage(
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam(required = false) String conditions,
        @RequestParam String status,
        @RequestParam String raffledate, // Formato esperado: YYYY-MM-DD
        @RequestParam("image") MultipartFile image) {

    try {
        Raffle raffle = new Raffle();
        raffle.setTitle(title);
        raffle.setDescription(description);
        raffle.setConditions(conditions);
        raffle.setStatus(status);
        raffle.setRaffledate(LocalDate.parse(raffledate)); // Conversión necesaria
        raffle.setCreationdate(LocalDate.now()); // set automático de fecha actual

        raffleService.addRaffleWithImage(raffle, image);

        String locationPath = "/api/raffle/" + raffle.getId();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", locationPath)
                .build();

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRaffle(@PathVariable int id, @RequestBody Raffle raffle) {
    Raffle existing = raffleService.findRaffleById(id);
    if (existing != null) {
        raffle.setId(id);
        raffleService.updateRaffle(raffle);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}
    
    public void updateRaffleWithImage(Raffle raffle, MultipartFile imageFile) throws Exception {
    if (imageFile != null && !imageFile.isEmpty()) {
        String imageUrl = RaffleImageService.saveRaffleImage(imageFile);
        raffle.setImageurl(imageUrl); // nombre del campo corregido
    } else {
        Raffle existing = raffleRepository.findById(raffle.getId()).orElse(null);
        if (existing != null) {
            raffle.setImageurl(existing.getImageurl());
        }
    }
    raffleRepository.save(raffle);
}

    // DELETE /api/raffle/{id} - Eliminar un sorteo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaffle(@PathVariable int id) {
        Raffle existing = raffleService.findRaffleById(id);
        if (existing != null) {
            raffleService.deleteRaffleById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
