package ural.cargo.ru.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CargoTypeDto {

    private long id;

    private String name;

    private String code;

}
