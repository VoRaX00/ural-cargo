package ural.ru.controllers;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ural.ru.api.CargoApi;
import ural.ru.dto.*;
import ural.ru.mappers.CargoMapper;
import ural.ru.mappers.PaginatedMapper;
import ural.ru.services.CargoService;

@RestController
@RequiredArgsConstructor
public class CargoController implements CargoApi {

    private final CargoService cargoService;
    private final CargoMapper cargoMapper;
    private final PaginatedMapper paginatedMapper;

    @Override
    public ResponseEntity<CargoResponse> create(
            CargoRequest cargoRequest,
            UserPrincipals userPrincipals
    ) {
        var cargo = cargoMapper.toEntity(cargoRequest);
        cargo = cargoService.create(cargo, userPrincipals.getUuid());
        var dto = cargoMapper.toDto(cargo);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PageDto<CargoResponse>> getPaginatedList(PaginatedParamsDto paginatedParamsDto) {
        var paramsModel = paginatedMapper.toModel(paginatedParamsDto);
        var pageModel = cargoService.getPage(paramsModel);
        return ResponseEntity.ok(paginatedMapper.toPageDto(pageModel, cargoMapper::toDto));
    }

}
