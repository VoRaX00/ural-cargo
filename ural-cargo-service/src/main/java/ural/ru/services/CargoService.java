package ural.ru.services;

import java.time.*;

import lombok.*;
import org.springframework.stereotype.*;
import ural.exception.*;
import ural.ru.entities.Cargo;
import ural.ru.enums.CargoType;
import ural.ru.repositories.CargoRepository;

@Service
@RequiredArgsConstructor
public class CargoService  {

    private final CargoRepository cargoRepository;

    public Cargo create(Cargo cargo) {
        cargo.setCargoType(CargoType.SEARCH);
        cargo.setCreatedAt(ZonedDateTime.now());
        return cargoRepository.save(cargo);
    }

}
