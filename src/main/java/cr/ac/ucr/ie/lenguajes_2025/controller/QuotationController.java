package cr.ac.ucr.ie.lenguajes_2025.controller;

import cr.ac.ucr.ie.lenguajes_2025.domain.Quotation;
import cr.ac.ucr.ie.lenguajes_2025.services.QuotationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quotations")
@CrossOrigin(origins = "http://localhost:3000")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping
    public List<Quotation> getAll() {
        return quotationService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Quotation> getById(@PathVariable int id) {
        return quotationService.obtenerPorId(id);
    }

    @PostMapping
    public Quotation create(@RequestBody Quotation quotation) {
        return quotationService.guardar(quotation);
    }

    @PutMapping("/{id}")
    public Quotation update(@PathVariable int id, @RequestBody Quotation quotation) {
        quotation.setIdQuotation(id);
        return quotationService.guardar(quotation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        quotationService.eliminar(id);
    }
}
