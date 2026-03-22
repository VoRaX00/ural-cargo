package ru.ural.cargo.mappers;

import org.mapstruct.*;
import ru.ural.cargo.dto.CargoDto;
import ru.ural.cargo.dto.CargoRequest;
import ru.ural.cargo.entities.Cargo;
import ru.ural.cargo.enums.CargoStatus;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = AddressMapper.class
)
public interface CargoMapper {

    @Mapping(target = "status", expression = "java(cargo.getStatus().getValue())")
    CargoDto toDto(Cargo cargo);

    List<CargoDto> toDto(List<Cargo> cargos);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "userUuid", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapCargoStatus")
    Cargo toEntity(CargoRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "userUuid", ignore = true)
    @Mapping(target = "status", ignore = true)
    void mapCargoDtoToEntity(@MappingTarget Cargo cargo, CargoRequest cargoRequest);

    @Named("mapCargoStatus")
    default CargoStatus mapCargoStatus(String value) {
        return CargoStatus.parse(value);
    }

}
