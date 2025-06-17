/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.ucr.ie.lenguajes_2025.controller;

/**
 *
 * @author Tony
 */
import cr.ac.ucr.ie.lenguajes_2025.domain.OrderTracking;
import cr.ac.ucr.ie.lenguajes_2025.services.OrderTrackingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-tracking")
@CrossOrigin(origins = "*")
public class OrderTrackingController {

    @Autowired
    private OrderTrackingService trackingService;

    @PostMapping("/create")
    public ResponseEntity<OrderTracking> createTracking(@RequestParam Integer idOrder,
            @RequestParam Integer idAdmin,
            @RequestParam Integer idClient) {
        OrderTracking tracking = trackingService.createTracking(idOrder, idAdmin, idClient);
        return ResponseEntity.ok(tracking);
    }

    @GetMapping
    public ResponseEntity<List<OrderTracking>> getAllTrackings() {
        return ResponseEntity.ok(trackingService.getAllTrackings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTracking> getTrackingById(@PathVariable Integer id) {
        return trackingService.getTrackingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderTracking> updateTracking(@PathVariable Integer id,
            @RequestBody OrderTracking updatedTracking) {
        try {
            return ResponseEntity.ok(trackingService.updateTracking(id, updatedTracking));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracking(@PathVariable Integer id) {
        try {
            trackingService.deleteTracking(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
