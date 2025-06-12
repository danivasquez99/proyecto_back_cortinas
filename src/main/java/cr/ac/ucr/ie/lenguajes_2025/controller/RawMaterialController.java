package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.RawMaterial;
import cr.ac.ucr.ie.lenguajes_2025.services.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@CrossOrigin(origins = "http://localhost:3000")
public class RawMaterialController {

    @Autowired
    private RawMaterialService service;

    @GetMapping("")
    public List<RawMaterial> getAll() {
        return service.getAll();
    }

    @PostMapping("")
    public RawMaterial create(@RequestBody RawMaterial material) {
        return service.save(material);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
public RawMaterial update(@PathVariable int id, @RequestBody RawMaterial material) {
    material.setId(id); // asegurar que el ID se actualice correctamente
    return service.save(material);
}

    
}
