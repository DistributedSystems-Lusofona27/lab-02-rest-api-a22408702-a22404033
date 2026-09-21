package pt.ulusofona.cd.supplier.exception;

import java.util.UUID;

public class SupplierNotFoundException extends RuntimeException {

    public SupplierNotFoundException(UUID id) {
        super("Supplier not found: " + id);
    }
}