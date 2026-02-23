package ural.ru.services;

import java.time.*;
import java.util.UUID;

import lombok.*;
import org.springframework.stereotype.*;
import ural.ru.entities.Cargo;
import ural.ru.enums.CargoType;
import ural.ru.models.PageModel;
import ural.ru.models.PaginatedParamsModel;
import ural.ru.repositories.CargoRepository;
import ural.ru.repositories.CustomCargoRepository;

@Service
@RequiredArgsConstructor
public class CargoService  {

    private final CargoRepository cargoRepository;
    private final CustomCargoRepository customCargoRepository;

    public Cargo create(Cargo cargo, String userUuid) {
        cargo.setCargoType(CargoType.SEARCH);
        cargo.setCreatedAt(ZonedDateTime.now());
        cargo.setUserUuid(UUID.fromString(userUuid));
        return cargoRepository.save(cargo);
    }

    public PageModel<Cargo> getPage(PaginatedParamsModel paramsModel) {
        var items = customCargoRepository.getItems(paramsModel);;
        int totalResultCount = customCargoRepository.getTotalResultCount(paramsModel.getFilters());
        int totalPageCount = totalResultCount % paramsModel.getItemsOnPage() == 0
                ? totalResultCount / paramsModel.getItemsOnPage()
                : totalResultCount / paramsModel.getItemsOnPage() + 1;

        return new PageModel<>(
                paramsModel.getCurrentPageNumber(),
                totalPageCount,
                totalResultCount,
                items,
                paramsModel.getItemsOnPage()
        );
    }


}
