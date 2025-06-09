package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Service;
import cr.ac.ucr.ie.lenguajes_2025.services.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // GET /api/services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.obtenerTodos();
    }

    // GET /api/services/{id}
    @GetMapping("/{id}")
    public Optional<Service> getServiceById(@PathVariable int id) {
        return serviceService.obtenerPorId(id);
    }

    // POST /api/services
    @PostMapping
    public Service createService(@RequestBody Service service) {
        return serviceService.guardar(service);
    }

    // PUT /api/services/{id}
    @PutMapping("/{id}")
    public Service updateService(@PathVariable int id, @RequestBody Service service) {
        service.setId(id);
        return serviceService.guardar(service);
    }

    // DELETE /api/services/{id}
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable int id) {
        serviceService.eliminar(id);
    }
}
