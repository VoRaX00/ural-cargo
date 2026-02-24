package ural.ru.controllers;

import lombok.*;
import org.springframework.http.*;
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
            UserPrincipals userPrincipals
    ) {
        return new ResponseEntity<>(cargoService.create(cargoRequest, userPrincipals.getUuid()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> update(Long id, CargoRequest cargoRequest, UserPrincipals userPrincipals) {
        cargoService.update(id, cargoRequest, userPrincipals);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PageDto<CargoResponse>> getPaginatedList(PaginatedParamsDto paramsDto) {
        return ResponseEntity.ok(cargoService.getPage(paramsDto));
    }

}
