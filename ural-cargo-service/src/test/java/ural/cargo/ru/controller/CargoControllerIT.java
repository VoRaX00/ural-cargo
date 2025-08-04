package ural.cargo.ru.controller;

import java.math.*;
import java.time.*;

import org.springframework.http.*;
import org.springframework.test.context.*;
import ural.cargo.ru.*;
import ural.cargo.ru.dto.*;
import ural.cargo.ru.entities.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class CargoControllerIT extends BaseIntegrationTest {

    private CargoRequest cargoRequest;

    private Cargo cargo;

    private final String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJmYzVkOGE" +
                                       "zOC0xMWFiLTRkM2EtYTUwNi1kMjE2NDIxYTI4ZDYiLCJ1c2VySWQiOjE" +
                                       "sImVtYWlsIjoiZXhhbXBsZUBnbWFpbC5jb20iLCJyb2xlcyI6WyJST0x" +
                                       "FX1VTRVIiXSwiaWF0IjoxNzUyOTkzMDM4LCJleHAiOjI3NTM1OTc4Mzh" +
                                       "9.BWLIMAnvsxKIkZpXqAvFCtnu02-7CqecGSnIaTfJowjYwKBHK7_usi" +
                                       "O1g98hFHVKdehxHmg6PdPEO6sXTkxMBcoQlEn_zwi9VXExkFuQoro2QV" +
                                       "11FGMw670kDq3dCSGwg3igsmGl0A6XKWEFqWOtryfnBitjONMuzpnBG4" +
                                       "0vkV1zx3A0O-WiP9QUCU0qEDgqBv9G6AbNzlRoEiMIxgZ2TxCc0H0BFP" +
                                       "7x6EIqTIPdsF6sWwkwI_14q6PMjPSG0JzofNd_wZipsMIxtaaP3csJgo" +
                                       "aUVlCY6ZEn8IUeWLO7UriUSHK_-hnBfyfK_--BCDWV5pvfimYsst6iRN" +
                                       "8UVQAb6Q";

    @BeforeEach
    void setUp() {
        cargoRequest = CargoRequest.builder()
            .name("Name")
            .comment("Comment")
            .height(1.0)
            .width(1.0)
            .length(1.0)
            .loadingPlace("Loading")
            .phoneNumber("88005553555")
            .price(BigDecimal.valueOf(1))
            .unloadingPlace("Unloading")
            .volume(1.0)
            .weight(1.0)
            .build();

        cargo = Cargo.builder()
            .name(cargoRequest.getName())
            .comment(cargoRequest.getComment())
            .height(cargoRequest.getHeight())
            .width(cargoRequest.getWidth())
            .length(cargoRequest.getLength())
            .loadingPlace(cargoRequest.getLoadingPlace())
            .phoneNumber(cargoRequest.getPhoneNumber())
            .price(cargoRequest.getPrice())
            .type(CargoType.builder()
                .id(1L)
                .name("В поиске")
                .code("SEARCH")
                .build()
            )
            .createdAt(ZonedDateTime.now())
            .unloadingPlace(cargoRequest.getUnloadingPlace())
            .volume(cargoRequest.getVolume())
            .weight(cargoRequest.getWeight())
            .userId(1L)
            .id(1L)
            .build();
    }

    @Test
    void create_Success() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        var response = testRestTemplate.postForEntity(
            "/api/cargo",
            new HttpEntity<>(cargo, headers),
            CargoResponse.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(cargo.getName(), response.getBody().getName());
        assertEquals(cargo.getComment(), response.getBody().getComment());
        assertEquals(cargo.getPhoneNumber(), response.getBody().getPhoneNumber());
        assertEquals(cargo.getPrice(), response.getBody().getPrice());
        assertEquals(cargo.getHeight(), response.getBody().getHeight());
        assertEquals(cargo.getWidth(), response.getBody().getWidth());
        assertEquals(cargo.getVolume(), response.getBody().getVolume());
        assertEquals(cargo.getWeight(), response.getBody().getWeight());
        assertEquals(cargo.getUserId(), response.getBody().getUserId());
        assertEquals(cargo.getLoadingPlace(), response.getBody().getLoadingPlace());
        assertEquals(cargo.getUnloadingPlace(), response.getBody().getUnloadingPlace());
        assertEquals(cargo.getType().getId(), response.getBody().getType().getId());
        assertEquals(cargo.getType().getName(), response.getBody().getType().getName());
        assertEquals(cargo.getType().getCode(), response.getBody().getType().getCode());
    }

    @Test
    void create_Unauthorized() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var response = testRestTemplate.postForEntity(
            "/api/cargo",
            new HttpEntity<>(cargo, headers),
            CargoResponse.class
        );
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void creteInvalidCargo

}
