package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.RawMaterial;
import cr.ac.ucr.ie.lenguajes_2025.repository.RawMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialService {

    @Autowired
    private RawMaterialRepository repository;

    public List<RawMaterial> getAll() {
        return repository.findAll();
    }

    public RawMaterial save(RawMaterial material) {
        return repository.save(material);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public RawMaterial findById(int id) {
        return repository.findById(id).orElse(null);
    }
}
