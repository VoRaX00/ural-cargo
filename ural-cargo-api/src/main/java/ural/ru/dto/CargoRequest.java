package ural.ru.dto;

import java.math.*;
import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CargoRequest {

    @NotNull
    @Pattern(
        regexp = "^[a-zA-Zа-яА-ЯёЁ]+$",
        message = "Название груза состоит только из букв кириллицы и латиницы"
    )
    @Size(max = 255, min = 2, message = "Название груза должно состоять из 2 до 255 символов")
    private String name;

    private BigDecimal length;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal volume;

    private BigDecimal weight;

    @NotNull
    private String loadingPlace;

    @NotNull
    private String unloadingPlace;

    @NotNull
    private BigDecimal price;

    @NotNull
    private UUID userUuid;

    @NotNull
    private String comment;

}
