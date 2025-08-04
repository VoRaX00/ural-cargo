package ural.cargo.ru.entities;

import java.math.*;
import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cargo")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_seq")
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double length;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double volume;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String loadingPlace;

    @Column(nullable = false)
    private String unloadingPlace;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String comment;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private CargoType type;

}
