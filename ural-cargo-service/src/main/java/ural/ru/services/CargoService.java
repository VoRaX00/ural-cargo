package ural.ru.services;

import java.time.*;
import java.util.Objects;
import java.util.UUID;

import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import ural.ru.dto.*;
import ural.ru.enums.CargoType;
import ural.ru.exceptions.BadRequestException;
import ural.ru.exceptions.NotFoundException;
import ural.ru.mappers.CargoMapper;
import ural.ru.mappers.PaginatedMapper;
import ural.ru.repositories.CargoRepository;
import ural.ru.repositories.CustomCargoRepository;

@Service
@RequiredArgsConstructor
public class CargoService  {

    private final CargoRepository cargoRepository;

    private final CustomCargoRepository customCargoRepository;

    private final PaginatedMapper paginatedMapper;

    private final CargoMapper cargoMapper;

    public CargoResponse create(CargoRequest cargoRequest, String userUuid) {
        var cargo = cargoMapper.toEntity(cargoRequest);
        cargo.setCargoType(CargoType.SEARCH);
        cargo.setCreatedAt(ZonedDateTime.now());
        cargo.setUserUuid(UUID.fromString(userUuid));

        var savedCargo = cargoRepository.save(cargo);
        return cargoMapper.toDto(savedCargo);
    }

    public PageDto<CargoResponse> getPage(PaginatedParamsDto paramsDto) {
        var paramsModel = paginatedMapper.toModel(paramsDto);
        var items = customCargoRepository.getItems(paramsModel);
        int totalResultCount = customCargoRepository.getTotalResultCount(paramsModel.getFilters());
        int totalPageCount = totalResultCount % paramsModel.getItemsOnPage() == 0
                ? totalResultCount / paramsModel.getItemsOnPage()
                : totalResultCount / paramsModel.getItemsOnPage() + 1;

        return new PageDto<>(
                paramsModel.getCurrentPageNumber(),
                totalPageCount,
                totalResultCount,
                cargoMapper.toDto(items),
                paramsModel.getItemsOnPage()
        );
    }

    @Transactional
    public void update(Long id, CargoRequest cargoRequest, UserPrincipals userPrincipals) {
        var cargoFromDb = cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Cargo not found by id: %d",
                        id
                )));

        if (!Objects.equals(cargoFromDb.getUserUuid().toString(), userPrincipals.getUuid())) {
            throw new BadRequestException("Only maintainer can update cargo");
        }

        cargoMapper.mapCargoDtoToEntity(cargoFromDb, cargoRequest);
    }

    @Transactional
    public void delete(Long id, UserPrincipals userPrincipals) {
        var cargoFromDb = cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Cargo not found by id: %d",
                        id
                )));

        if (!Objects.equals(cargoFromDb.getUserUuid().toString(), userPrincipals.getUuid())) {
            throw new BadRequestException("Only maintainer can delete cargo");
        }

        cargoRepository.deleteById(id);
    }

}
