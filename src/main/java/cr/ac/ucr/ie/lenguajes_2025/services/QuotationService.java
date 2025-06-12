package cr.ac.ucr.ie.lenguajes_2025.services;

import cr.ac.ucr.ie.lenguajes_2025.domain.Quotation;
import cr.ac.ucr.ie.lenguajes_2025.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {

    private final QuotationRepository repository;

    public QuotationService(QuotationRepository repository) {
        this.repository = repository;
    }

    public List<Quotation> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Quotation> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public Quotation guardar(Quotation cotizacion) {
        return repository.save(cotizacion);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}
