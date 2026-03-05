package ural.ru.controllers;

import lombok.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ural.ru.api.CargoApi;
import ural.ru.dto.*;
import ural.ru.services.CargoService;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoService cargoService;

    @Override
    public ResponseEntity<CargoResponse> create(
            CargoRequest cargoRequest,
            Authentication authentication
    ) {
        return new ResponseEntity<>(cargoService.create(cargoRequest, authentication), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, CargoRequest cargoRequest, Authentication authentication) {
        cargoService.update(id, cargoRequest, authentication);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PageDto<CargoResponse>> getPaginatedList(PaginatedParamsDto paramsDto) {
        return ResponseEntity.ok(cargoService.getPage(paramsDto));
    }

    @Override
    public ResponseEntity<Void> delete(Long id, Authentication authentication) {
        cargoService.delete(id, authentication);
        return ResponseEntity.ok().build();
    }

}
