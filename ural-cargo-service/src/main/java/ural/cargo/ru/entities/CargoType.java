package ural.cargo.ru.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cargo_types")
public class CargoType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_types_id_seq")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

}
