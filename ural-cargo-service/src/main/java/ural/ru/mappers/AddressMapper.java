package ural.ru.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ural.ru.dto.AddressDto;
import ural.ru.entities.Address;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    Address toEntity(AddressDto addressDto);

    AddressDto toDto(Address address);

}
