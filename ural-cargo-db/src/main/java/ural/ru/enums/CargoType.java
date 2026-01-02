package ural.ru.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CargoType {

    SEARCH("В поиске"),
    AWAITING("Ожидает перевозки"),
    PROCESS("В процессе перевозки"),
    DELIVERED("Доставлен");

    private final String value;

}
