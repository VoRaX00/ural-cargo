package ural.cargo.ru.services.impl;

import java.time.*;

import lombok.*;
import org.springframework.stereotype.*;
import ural.cargo.ru.entities.*;
import ural.cargo.ru.repositories.*;
import ural.cargo.ru.services.*;
import ural.exception.*;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private static final String DEFAULT_TYPE_CODE = "SEARCH";

    private final CargoRepository cargoRepository;

    private final CargoTypesRepository cargoTypesRepository;

    @Override
    public Cargo create(Cargo cargo) {
        var type = cargoTypesRepository.findByCode(DEFAULT_TYPE_CODE)
            .orElseThrow(() -> new InternalServerError("Тип груза не найден"));

        cargo.setType(type);
        cargo.setCreatedAt(ZonedDateTime.now());
        return cargoRepository.save(cargo);
    }

}
