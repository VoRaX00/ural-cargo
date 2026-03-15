package ural.ru.services;

import java.time.*;
import java.util.Objects;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import ural.ru.dto.*;
import ural.ru.enums.CargoType;
import ru.ural.enums.UserRole;
import ural.ru.exceptions.BadRequestException;
import ural.ru.exceptions.NotFoundException;
import ural.ru.mappers.CargoMapper;
import ural.ru.mappers.PaginatedMapper;
import ru.ural.models.UserPrincipals;
import ural.ru.repositories.CargoRepository;
import ural.ru.repositories.CustomCargoRepository;
import ru.ural.utils.JwtUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CargoService  {

    private final CargoRepository cargoRepository;

    private final CustomCargoRepository customCargoRepository;

    private final PaginatedMapper paginatedMapper;

    private final CargoMapper cargoMapper;

    public CargoResponse create(@NonNull CargoRequest cargoRequest, @Nullable Authentication authentication) {
        var cargo = cargoMapper.toEntity(cargoRequest);
        cargo.setCargoType(CargoType.SEARCH);
        cargo.setCreatedAt(ZonedDateTime.now());

        UserPrincipals user = JwtUtils.getUser(authentication);
        cargo.setUserId(user.getId());

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
    public void update(
            @NonNull Long id,
            @NonNull CargoRequest cargoRequest,
            @Nullable Authentication authentication
    ) {
        var cargoFromDb = cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Cargo not found by id: %d",
                        id
                )));

        UserPrincipals user = JwtUtils.getUser(authentication);
        if (!Objects.equals(cargoFromDb.getUserId(), user.getId())) {
            throw new BadRequestException("Only maintainer can update cargo");
        }

        cargoMapper.mapCargoDtoToEntity(cargoFromDb, cargoRequest);
    }

    @Transactional
    public void delete(Long id, Authentication authentication) {
        var cargoFromDb = cargoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Cargo not found by id: %d",
                        id
                )));

        UserPrincipals user = JwtUtils.getUser(authentication);
        if (!Objects.equals(cargoFromDb.getUserId(), user.getId())
                && !user.getRoles().contains(UserRole.ADMIN)) {
            throw new BadRequestException("Only maintainer can delete cargo");
        }

        cargoRepository.deleteById(id);
    }

}
