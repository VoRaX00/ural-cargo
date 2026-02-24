package ural.ru.api;

import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ural.ru.annotations.RequestPrincipals;
import ural.ru.dto.CargoRequest;
import ural.ru.dto.CargoResponse;
import ural.ru.dto.PageDto;
import ural.ru.dto.PaginatedParamsDto;
import ural.ru.dto.UserPrincipals;

@Validated
@RequestMapping("/api/cargo")
@Tag(name = "Cargo api", description = "API грузов платформы BACAR")
public interface CargoApi {

    @PostMapping
    ResponseEntity<CargoResponse> create(
            @RequestBody @Valid CargoRequest cargoRequest,
            @RequestPrincipals UserPrincipals userInfo
    );

    @PutMapping("/{id}")
    ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid CargoRequest cargoRequest,
            @RequestPrincipals UserPrincipals userInfo
    );

    @GetMapping
    ResponseEntity<PageDto<CargoResponse>> getPaginatedList(@RequestParam PaginatedParamsDto paginatedParamsDto);

}
