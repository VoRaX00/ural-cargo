package ural.cargo.ru.mappers;

import org.mapstruct.*;
import ural.cargo.ru.dto.*;
import ural.cargo.ru.entities.*;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = CargoTypeMapper.class
)
public interface CargoMapper {

    CargoResponse toDto(Cargo cargo);

    Cargo toEntity(CargoRequest request);

}
