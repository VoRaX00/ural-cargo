package ural.cargo.ru.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ural.cargo.ru.entities.*;

@Repository
public interface CargoTypesRepository extends JpaRepository<CargoType, Long> {

    Optional<CargoType> findByCode(String code);

}
