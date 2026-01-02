package ural.ru.mappers;

import org.mapstruct.*;
import ural.ru.dto.CargoRequest;
import ural.ru.dto.CargoResponse;
import ural.ru.entities.Cargo;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CargoMapper {

    CargoResponse toDto(Cargo cargo);

    Cargo toEntity(CargoRequest request);

}
