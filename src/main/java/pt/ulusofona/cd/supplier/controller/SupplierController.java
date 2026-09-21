package pt.ulusofona.cd.supplier.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pt.ulusofona.cd.supplier.model.dto.SupplierRequest;
import pt.ulusofona.cd.supplier.model.dto.SupplierResponse;
import pt.ulusofona.cd.supplier.service.SupplierService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Suppliers", description = "Create, read, update and delete suppliers")
@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @Operation(summary = "Create a supplier",
            description = "Creates a supplier and returns its location in the Location header. "
                    + "The taxId must not already be in use.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Supplier created"),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "taxId already in use",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierRequest request,
                                                   UriComponentsBuilder uriBuilder) {
        SupplierResponse created = service.createSupplier(request);

        URI location = uriBuilder.path("/api/v1/suppliers/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "List all suppliers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Suppliers returned")
    })
    @GetMapping
    public List<SupplierResponse> getAll() {
        return service.getAllSuppliers();
    }

    @Operation(summary = "Get a supplier by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier found"),
            @ApiResponse(responseCode = "400", description = "Malformed id",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public SupplierResponse getById(@PathVariable UUID id) {
        return service.getSupplierById(id);
    }

    @Operation(summary = "Replace a supplier",
            description = "Full replace. The taxId must not belong to another supplier.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supplier updated"),
            @ApiResponse(responseCode = "400", description = "Validation failed",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "taxId already in use",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{id}")
    public SupplierResponse update(@PathVariable UUID id,
                                   @Valid @RequestBody SupplierRequest request) {
        return service.updateSupplier(id, request);
    }

    @Operation(summary = "Delete a supplier")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Supplier deleted"),
            @ApiResponse(responseCode = "400", description = "Malformed id",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Supplier not found",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.deleteSupplier(id);
    }
}