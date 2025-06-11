/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.repository;

import cr.ac.ucr.ie.lenguajes_2025.domain.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author josia
 */

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Integer> {
    
}
