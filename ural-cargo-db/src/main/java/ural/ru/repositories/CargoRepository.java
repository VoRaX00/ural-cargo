package ural.ru.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ural.ru.entities.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
