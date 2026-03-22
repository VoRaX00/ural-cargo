package ru.ural.cargo.controllers;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.ural.cargo.api.CargoApi;
import ru.ural.cargo.dto.CargoDto;
import ru.ural.cargo.dto.CargoRequest;
import ru.ural.cargo.services.CargoService;
import ru.ural.dto.PageDto;
import ru.ural.dto.PaginatedParamsDto;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoService cargoService;

    @Override
    public ResponseEntity<CargoDto> create(CargoRequest cargoRequest) {
        return new ResponseEntity<>(cargoService.create(cargoRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, CargoRequest cargoRequest) {
        cargoService.update(id, cargoRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PageDto<CargoDto>> getPaginatedList(PaginatedParamsDto paramsDto) {
        return ResponseEntity.ok(cargoService.getPage(paramsDto));
    }

    @Override
    public ResponseEntity<CargoDto> getById(Long id) {
        return ResponseEntity.ok(cargoService.getById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        cargoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
