package ru.ural.cargo.dto;

import java.math.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import ru.ural.dto.AddressDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoRequest {

    @NotNull
    @Schema(description = "Наименование груза")
    @Size(max = 255, min = 2, message = "Название груза должно состоять из 2 до 255 символов")
    private String cargoName;

    @NotNull
    @Schema(description = "Длина груза")
    private BigDecimal length;

    @NotNull
    @Schema(description = "Ширина груза")
    private BigDecimal width;

    @NotNull
    @Schema(description = "Высота груза")
    private BigDecimal height;

    @NotNull
    @Schema(description = "Объем груза")
    private BigDecimal volume;

    @NotNull
    @Schema(description = "Вес груза")
    private BigDecimal weight;

    @NotNull
    @Schema(description = "Место загрузки")
    private AddressDto loadingPlace;

    @NotNull
    @Schema(description = "Место разгрузки")
    private AddressDto unloadingPlace;

    @NotNull
    @Schema(description = "Желаемая стоимость")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Комментарий")
    private String comment;

    @NotNull
    @Schema(description = "Тип груза")
    private String cargoType;


}
