package pt.ulusofona.cd.supplier.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SupplierRequest(

        @Schema(description = "Trading name", example = "Ibérica Componentes, Lda")
        @NotBlank(message = "companyName is required")
        @Size(min = 2, max = 150, message = "companyName must be between 2 and 150 characters")
        String companyName,

        @Schema(description = "Contact email", example = "encomendas@ibericacomponentes.pt")
        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid email address")
        String email,

        @Schema(description = "Portuguese NIF, unique across all suppliers", example = "501234567")
        @NotBlank(message = "taxId is required")
        @Pattern(regexp = "^[0-9]{9}$", message = "taxId must be exactly 9 digits")
        String taxId,

        @Schema(description = "Contact phone", example = "+351 210 000 000")
        @Pattern(regexp = "^\\+?[0-9 ]{9,15}$", message = "phone must be a valid phone number")
        String phone,

        @Schema(description = "Street address", example = "Rua da Prata 120, 2.º Esq.")
        @NotBlank(message = "address is required")
        @Size(max = 200, message = "address must be at most 200 characters")
        String address,

        @Schema(description = "City", example = "Lisboa")
        @NotBlank(message = "city is required")
        @Size(max = 100, message = "city must be at most 100 characters")
        String city,

        @Schema(description = "Portuguese postal code", example = "1100-052")
        @NotBlank(message = "postalCode is required")
        @Pattern(regexp = "^[0-9]{4}-[0-9]{3}$", message = "postalCode must match the format 1234-567")
        String postalCode,

        @Schema(description = "ISO 3166-1 alpha-2 country code, defaults to PT", example = "PT")
        @Pattern(regexp = "^[A-Z]{2}$", message = "country must be a 2-letter uppercase code")
        String country,

        @Schema(description = "Whether the supplier is active, defaults to true", example = "true")
        Boolean active
) {
}