package ru.ural.cargo.mappers;

import org.mapstruct.*;
import ru.ural.cargo.dto.CargoRequest;
import ru.ural.cargo.dto.CargoResponse;
import ru.ural.cargo.entities.Cargo;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = AddressMapper.class
)
public interface CargoMapper {

    @Mapping(target = "name", source = "cargoName")
    @Mapping(target = "type", source = "cargoType")
    CargoResponse toDto(Cargo cargo);

    List<CargoResponse> toDto(List<Cargo> cargos);

    @Mapping(target = "cargoType", expression = "java(CargoType.valueOf(request.getCargoType()))")
    Cargo toEntity(CargoRequest request);

    void mapCargoDtoToEntity(@MappingTarget Cargo cargo, CargoRequest cargoRequest);

}
