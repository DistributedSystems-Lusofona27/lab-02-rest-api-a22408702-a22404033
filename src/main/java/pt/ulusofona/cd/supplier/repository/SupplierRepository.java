package pt.ulusofona.cd.supplier.repository;

import org.springframework.stereotype.Repository;
import pt.ulusofona.cd.product.repository.InMemoryRepository;
import pt.ulusofona.cd.supplier.model.Supplier;

import java.util.Optional;

@Repository
public class SupplierRepository extends InMemoryRepository<Supplier> {

    public Optional<Supplier> findByTaxIdIgnoreCase(String taxId) {
        return stream()
                .filter(supplier -> supplier.getTaxId().equalsIgnoreCase(taxId))
                .findFirst();
    }

    public boolean existsByTaxIdIgnoreCase(String taxId) {
        return findByTaxIdIgnoreCase(taxId).isPresent();
    }
}