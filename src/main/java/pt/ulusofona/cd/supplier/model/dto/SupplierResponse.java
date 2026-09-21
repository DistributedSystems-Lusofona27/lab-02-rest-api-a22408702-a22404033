package pt.ulusofona.cd.supplier.model.dto;

import java.time.Instant;
import java.util.UUID;

public record SupplierResponse(
        UUID id,
        String companyName,
        String email,
        String taxId,
        String phone,
        String address,
        String city,
        String postalCode,
        String country,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        int version
) {
}