package ural.cargo.ru.dto;

import java.math.*;

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

    private double length;

    private double width;

    private double height;

    private double volume;

    private double weight;

    @Pattern(regexp = "\\d{11}")
    @Size(max = 11, min = 11, message = "Номер телефона должен быть длинной в 11 символов")
    private String phoneNumber;

    @NotNull
    @Pattern(
        regexp = "^[а-яА-ЯёЁ]+$",
        message = "Название места загрузки состоит только из букв кириллицы"
    )
    private String loadingPlace;

    @NotNull
    @Pattern(
        regexp = "^[а-яА-ЯёЁ]+$",
        message = "Название места разгрузки состоит только из букв кириллицы"
    )
    private String unloadingPlace;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String comment;

}
