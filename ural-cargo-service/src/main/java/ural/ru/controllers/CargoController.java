package ural.ru.controllers;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ural.ru.api.CargoApi;
import ural.ru.dto.CargoRequest;
import ural.ru.dto.CargoResponse;
import ural.ru.mappers.CargoMapper;
import ural.ru.services.CargoService;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoService cargoService;

    private final CargoMapper cargoMapper;

    @Override
    public ResponseEntity<CargoResponse> create(CargoRequest cargoRequest) {
        var cargo = cargoMapper.toEntity(cargoRequest);

        cargo = cargoService.create(cargo);
        var dto = cargoMapper.toDto(cargo);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
