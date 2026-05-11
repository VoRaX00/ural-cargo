package ru.ural.cargo.entities;

import java.math.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.ural.cargo.enums.BodyType;
import ru.ural.cargo.enums.LoadingType;
import ru.ural.entities.BaseEntity;
import ru.ural.cargo.enums.CargoStatus;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cargo extends BaseEntity {

    @Column(nullable = false)
    private String name;

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
    @JdbcTypeCode(SqlTypes.JSON)
    private Address loadingPlace;

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Address unloadingPlace;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String comment;

    @Column(nullable = false)
    private String userUuid;

    @Column(nullable = false)
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime updatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoStatus status;

    @Builder.Default
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Long> fileIds = new ArrayList<>();

    @Builder.Default
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<BodyType> bodyTypes = new ArrayList<>();

    @Builder.Default
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<LoadingType> loadingTypes = new ArrayList<>();

    @Builder.Default
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private List<LoadingType> unloadingTypes = new ArrayList<>();

}
