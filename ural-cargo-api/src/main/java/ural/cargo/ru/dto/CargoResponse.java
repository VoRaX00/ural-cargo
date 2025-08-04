package ural.cargo.ru.dto;

import java.math.*;
import java.time.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CargoResponse {

    private long id;

    private long userId;

    private String name;

    private double length;

    private double width;

    private double height;

    private double volume;

    private double weight;

    private String phoneNumber;

    private String loadingPlace;

    private String unloadingPlace;

    private BigDecimal price;

    private String comment;

    private CargoTypeDto type;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

}
