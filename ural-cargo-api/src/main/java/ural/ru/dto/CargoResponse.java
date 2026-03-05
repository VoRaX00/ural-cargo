package ural.ru.dto;

import java.math.*;
import java.time.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CargoResponse {

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
