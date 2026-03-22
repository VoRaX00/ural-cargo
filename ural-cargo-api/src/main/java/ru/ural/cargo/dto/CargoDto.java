package ru.ural.cargo.dto;

import java.math.*;
import java.time.*;

import lombok.*;
import ru.ural.dto.AddressDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CargoDto {

    private Long id;

    private String userUuid;

    private String name;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal volume;

    private BigDecimal weight;

    private AddressDto loadingPlace;

    private AddressDto unloadingPlace;

    private BigDecimal price;

    private String comment;

    private String type;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
