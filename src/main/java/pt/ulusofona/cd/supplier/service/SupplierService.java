package pt.ulusofona.cd.supplier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pt.ulusofona.cd.supplier.exception.DuplicateTaxIdException;
import pt.ulusofona.cd.supplier.exception.SupplierNotFoundException;
import pt.ulusofona.cd.supplier.mapper.SupplierMapper;
import pt.ulusofona.cd.supplier.model.Supplier;
import pt.ulusofona.cd.supplier.model.dto.SupplierRequest;
import pt.ulusofona.cd.supplier.model.dto.SupplierResponse;
import pt.ulusofona.cd.supplier.repository.SupplierRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    public SupplierResponse createSupplier(SupplierRequest request) {
        if (repository.existsByTaxIdIgnoreCase(request.taxId())) {
            throw new DuplicateTaxIdException(request.taxId());
        }

        Supplier supplier = mapper.toEntity(request);
        Instant now = Instant.now();
        supplier.setCreatedAt(now);
        supplier.setUpdatedAt(now);
        supplier.setVersion(0);

        return mapper.toResponse(repository.save(supplier));
    }

    public List<SupplierResponse> getAllSuppliers() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public SupplierResponse getSupplierById(UUID id) {
        return mapper.toResponse(findOrThrow(id));
    }

    public SupplierResponse updateSupplier(UUID id, SupplierRequest request) {
        Supplier supplier = findOrThrow(id);

        repository.findByTaxIdIgnoreCase(request.taxId())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new DuplicateTaxIdException(request.taxId());
                });

        mapper.applyTo(supplier, request);
        supplier.setUpdatedAt(Instant.now());
        supplier.setVersion(supplier.getVersion() + 1);

        return mapper.toResponse(repository.save(supplier));
    }

    public void deleteSupplier(UUID id) {
        if (!repository.deleteById(id)) {
            throw new SupplierNotFoundException(id);
        }
    }

    private Supplier findOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));
    }
}