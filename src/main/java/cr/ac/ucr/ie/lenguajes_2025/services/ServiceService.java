package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<cr.ac.ucr.ie.lenguajes_2025.domain.Service> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<cr.ac.ucr.ie.lenguajes_2025.domain.Service> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public cr.ac.ucr.ie.lenguajes_2025.domain.Service guardar(cr.ac.ucr.ie.lenguajes_2025.domain.Service servicio) {
        return repository.save(servicio);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}
