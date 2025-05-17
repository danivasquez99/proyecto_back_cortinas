package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.dao.ServiceDAO;
import cr.ac.ucr.ie.lenguajes_2025.dao_implement.ServiceDAOImplement;
import cr.ac.ucr.ie.lenguajes_2025.domain.Service;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*") // permite llamadas desde el front
public class ServiceController {

    private final ServiceDAO serviceDAO = new ServiceDAOImplement();

    // GET /api/services
    @GetMapping
    public LinkedList<Service> getAllServices() {
        return serviceDAO.getAll();
    }

    // GET /api/services/{id}
    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable int id) {
        return serviceDAO.findById(id);
    }

    // POST /api/services
    @PostMapping
    public void createService(@RequestBody Service service) {
        serviceDAO.insert(service);
    }

    // PUT /api/services/{id}
    @PutMapping("/{id}")
    public void updateService(@PathVariable int id, @RequestBody Service service) {
        service.setId(id); // asegurarse que use el ID de la URL
        serviceDAO.update(service);
    }

    // DELETE /api/services/{id}
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable int id) {
        serviceDAO.deleteById(id); // elimina lógicamente (o físicamente si así está hecho)
    }
}
