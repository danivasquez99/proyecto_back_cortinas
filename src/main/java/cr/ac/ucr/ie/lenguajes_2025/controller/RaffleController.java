/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Raffle;
import cr.ac.ucr.ie.lenguajes_2025.services.RaffleService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author josia
 */

@RestController
@RequestMapping("/api/raffle")
@CrossOrigin(origins = "http://localhost:3000")
public class RaffleController {
    private final RaffleService raffleService;

    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
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
