package pt.ulusofona.cd.supplier.mapper;

import org.springframework.stereotype.Component;
import pt.ulusofona.cd.supplier.model.Supplier;
import pt.ulusofona.cd.supplier.model.dto.SupplierRequest;
import pt.ulusofona.cd.supplier.model.dto.SupplierResponse;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequest request) {
        Supplier supplier = new Supplier();
        applyTo(supplier, request);
        return supplier;
    }

    public void applyTo(Supplier supplier, SupplierRequest request) {
        supplier.setCompanyName(request.companyName());
        supplier.setEmail(request.email());
        supplier.setTaxId(request.taxId());
        supplier.setPhone(request.phone());
        supplier.setAddress(request.address());
        supplier.setCity(request.city());
        supplier.setPostalCode(request.postalCode());
        supplier.setCountry(request.country() == null ? "PT" : request.country());
        supplier.setActive(request.active() == null ? true : request.active());
    }

    public SupplierResponse toResponse(Supplier supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getCompanyName(),
                supplier.getEmail(),
                supplier.getTaxId(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getCity(),
                supplier.getPostalCode(),
                supplier.getCountry(),
                supplier.isActive(),
                supplier.getCreatedAt(),
                supplier.getUpdatedAt(),
                supplier.getVersion()
        );
    }
}