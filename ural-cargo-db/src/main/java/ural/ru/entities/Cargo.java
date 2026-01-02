package ural.ru.entities;

import java.math.*;
import java.time.*;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import ural.ru.enums.CargoType;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cargoName;

    @Column(nullable = false)
    private BigDecimal length;

    @Column(nullable = false)
    private BigDecimal width;

    @Column(nullable = false)
    private BigDecimal height;

    @Column(nullable = false)
    private BigDecimal volume;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private String loadingPlace;

    @Column(nullable = false)
    private String unloadingPlace;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String comment;

    @Column(nullable = false)
    private UUID userUuid;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoType cargoType;

}
