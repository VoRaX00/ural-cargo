package ural.ru.mappers;

import org.mapstruct.*;
import ural.ru.dto.CargoRequest;
import ural.ru.dto.CargoResponse;
import ural.ru.entities.Cargo;

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
