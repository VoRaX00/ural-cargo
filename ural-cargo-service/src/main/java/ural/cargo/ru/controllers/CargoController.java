package ural.cargo.ru.controllers;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ural.auth.ru.*;
import ural.cargo.ru.api.*;
import ural.cargo.ru.dto.*;
import ural.cargo.ru.mappers.*;
import ural.cargo.ru.services.*;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoService cargoService;

    private final CargoMapper cargoMapper;

    private final JwtAuthHolder jwtAuthHolder;

    @Override
    public ResponseEntity<CargoResponse> create(CargoRequest cargoRequest) {
        var cargo = cargoMapper.toEntity(cargoRequest);
        cargo.setUserId(jwtAuthHolder.getUserId());

        cargo = cargoService.create(cargo);
        var dto = cargoMapper.toDto(cargo);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
