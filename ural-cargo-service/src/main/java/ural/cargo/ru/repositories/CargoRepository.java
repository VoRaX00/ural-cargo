package ural.cargo.ru.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ural.cargo.ru.entities.*;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

}
