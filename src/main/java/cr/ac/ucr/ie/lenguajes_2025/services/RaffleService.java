/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Raffle;
import cr.ac.ucr.ie.lenguajes_2025.repository.RaffleRepository;
import java.util.LinkedList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author josia
 */

@Service
public class RaffleService {
     private final RaffleRepository raffleRepository;

     @Autowired
    public RaffleService(RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
    }

    // Obtener todos los sorteos
    public LinkedList<Raffle> getAllRaffles() {
        return new LinkedList<>(raffleRepository.findAll());
    }

    // Agregar un nuevo sorteo
    public void addRaffle(Raffle raffle) {
        raffleRepository.save(raffle);
    }
    
    // Agregar sorteo con imagen
    public void addRaffleWithImage(Raffle raffle, MultipartFile imageFile) throws Exception {
        Raffle savedRaffle = raffleRepository.save(raffle);
        String imageUrl = RaffleImageService.saveRaffleImage(imageFile);

        savedRaffle.setImageurl(imageUrl);
        raffleRepository.save(savedRaffle);
    }

    // Actualizar un sorteo existente
    public void updateRaffle(Raffle raffle) {
        raffleRepository.save(raffle);
    }
    
    // Actualizar sorteo con imagen
    public void updateRaffleWithImage(Raffle raffle, MultipartFile imageFile) throws Exception {
    if (imageFile != null && !imageFile.isEmpty()) {
        String imageUrl = RaffleImageService.saveRaffleImage(imageFile);
        raffle.setImageurl(imageUrl);
    } else {
        Raffle existing = raffleRepository.findById(raffle.getId()).orElse(null);
        if (existing != null) {
            raffle.setImageurl(existing.getImageurl());
        }
    }
    raffleRepository.save(raffle);
}

    // Eliminar un sorteo por ID
    public void deleteRaffleById(int idRaffle) {
        raffleRepository.deleteById(idRaffle);
    }

    // Buscar un sorteo por ID
    public Raffle findRaffleById(int idRaffle) {
        Optional<Raffle> optionalRaffle = raffleRepository.findById(idRaffle);
        return optionalRaffle.orElse(null);
    }
}
