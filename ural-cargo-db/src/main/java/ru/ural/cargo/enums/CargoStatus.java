package ru.ural.cargo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ural.entities.ValuableEnum;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum CargoStatus implements ValuableEnum<CargoStatus> {

    SEARCH("В поиске"),
    AWAITING("Ожидает перевозки"),
    PROCESS("В процессе перевозки"),
    DELIVERED("Доставлен");

    private final String value;

    public static CargoStatus parse(String value) {
        return CargoStatus.AWAITING.parseValue(value);
    }

    @Override
    public List<CargoStatus> getValues() {
        return Arrays.stream(CargoStatus.values()).toList();
    }
}
