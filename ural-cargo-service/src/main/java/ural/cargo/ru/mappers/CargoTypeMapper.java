package ural.cargo.ru.mappers;

import org.mapstruct.*;
import ural.cargo.ru.dto.*;
import ural.cargo.ru.entities.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CargoTypeMapper {

    CargoTypeDto toDto(CargoType cargoType);

}
