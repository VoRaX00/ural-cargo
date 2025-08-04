package ural.cargo.ru.service;

import java.math.*;
import java.time.*;
import java.util.*;

import org.mockito.*;
import org.mockito.junit.jupiter.*;
import ural.cargo.ru.entities.*;
import ural.cargo.ru.repositories.*;
import ural.cargo.ru.services.impl.*;
import ural.exception.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CargoServiceImplTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private CargoTypesRepository cargoTypesRepository;

    @InjectMocks
    private CargoServiceImpl cargoService;

    private Cargo cargo;

    private CargoType cargoType;

    private final String defaultCode = "SEARCH";

    @BeforeEach
    void setUp() {
        cargoType = CargoType.builder()
            .id(1L)
            .name("test")
            .code("TEST")
            .build();

        cargo = Cargo.builder()
            .id(1L)
            .name("Cargo")
            .price(BigDecimal.valueOf(1))
            .comment("test")
            .height(1.0)
            .volume(1.0)
            .length(1.0)
            .width(1.0)
            .loadingPlace("test")
            .type(cargoType)
            .createdAt(ZonedDateTime.now())
            .phoneNumber("88005553555")
            .unloadingPlace("test")
            .build();
    }

    @Test
    void create_Success() {
        when(cargoTypesRepository.findByCode(defaultCode)).thenReturn(Optional.of(cargoType));
        when(cargoRepository.save(cargo)).thenReturn(cargo);
        var result = cargoService.create(cargo);
        assertNotNull(result);
    }

    @Test
    void cargoTypeNotFound_NotFound() {
        when(cargoTypesRepository.findByCode(defaultCode)).thenReturn(Optional.empty());
        assertThrows(InternalServerError.class, () -> cargoService.create(cargo));
    }

}
