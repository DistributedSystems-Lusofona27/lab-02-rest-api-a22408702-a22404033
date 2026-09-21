package pt.ulusofona.cd.supplier.exception;

public class DuplicateTaxIdException extends RuntimeException {

    public DuplicateTaxIdException(String taxId) {
        super("A supplier with tax ID " + taxId + " already exists");
    }
}