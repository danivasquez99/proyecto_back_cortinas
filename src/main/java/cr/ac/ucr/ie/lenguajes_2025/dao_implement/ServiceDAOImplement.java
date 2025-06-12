package cr.ac.ucr.ie.lenguajes_2025.dao_implement;

import cr.ac.ucr.ie.lenguajes_2025.domain.Service;
import cr.ac.ucr.ie.lenguajes_2025.repository.ServiceRepository;
import java.util.List;

public class ServiceDAOImplement {

    private final ServiceRepository repository;

    public ServiceDAOImplement(ServiceRepository repository) {
        this.repository = repository;
    }

    public List<Service> listarServicios() {
        return repository.findAll();
    }

    public void insertar(Service servicio) {
        repository.save(servicio);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}
