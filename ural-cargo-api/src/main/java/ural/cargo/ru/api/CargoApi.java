package ural.cargo.ru.api;

import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import ural.cargo.ru.dto.*;

@Validated
@Tag(name = "Cargo api", description = "API грузов платформы BACAR")
public interface CargoApi {

    @PostMapping("/api/cargo")
    ResponseEntity<CargoResponse> create(@RequestBody @Valid CargoRequest cargoRequest);

}
