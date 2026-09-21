package pt.ulusofona.cd.supplier.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.ulusofona.cd.product.model.Identifiable;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Supplier implements Identifiable {

    private UUID id;
    private String companyName;
    private String email;
    private String taxId;
    private String phone;
    private String address;
    private String city;
    private String postalCode;
    private String country = "PT";
    private boolean active = true;
    private Instant createdAt;
    private Instant updatedAt;
    private int version;
}