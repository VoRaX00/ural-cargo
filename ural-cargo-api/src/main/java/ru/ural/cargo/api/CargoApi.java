package ru.ural.cargo.api;

import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ural.cargo.dto.CargoDto;
import ru.ural.cargo.dto.CargoRequest;
import ru.ural.dto.PageDto;
import ru.ural.dto.PaginatedParamsDto;

@Validated
@RequestMapping("/api/cargo")
@Tag(name = "Cargo api", description = "API для с грузами")
public interface CargoApi {

    @PostMapping
    ResponseEntity<CargoDto> create(@RequestBody @Valid CargoRequest cargoRequest);

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CargoRequest cargoRequest);

    @GetMapping
    ResponseEntity<PageDto<CargoDto>> getPaginatedList(@RequestParam PaginatedParamsDto paginatedParamsDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

}
