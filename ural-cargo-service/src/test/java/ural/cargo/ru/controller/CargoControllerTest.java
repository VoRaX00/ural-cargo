package ural.cargo.ru.controller;

import java.math.*;
import java.time.*;

import com.fasterxml.jackson.databind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import ural.auth.ru.*;
import ural.cargo.ru.dto.*;
import ural.cargo.ru.entities.*;
import ural.cargo.ru.mappers.*;
import ural.cargo.ru.services.*;
import ural.exception.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CargoControllerTest {

    @MockBean
    private CargoService cargoService;

    @MockBean
    private CargoMapper cargoMapper;

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

    @Autowired
    private MockMvc mockMvc;

    private CargoRequest cargoRequest;

    private CargoResponse cargoResponse;

    private Cargo cargo;

    @Autowired
    private ObjectMapper objectMapper;

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
                .name("test")
                .code("TEST")
                .build()
            )
            .createdAt(ZonedDateTime.now())
            .unloadingPlace(cargoRequest.getUnloadingPlace())
            .volume(cargoRequest.getVolume())
            .weight(cargoRequest.getWeight())
            .userId(1L)
            .id(1L)
            .build();

        cargoResponse = CargoResponse.builder()
            .id(cargo.getId())
            .length(cargo.getLength())
            .height(cargo.getHeight())
            .price(cargo.getPrice())
            .comment(cargo.getComment())
            .phoneNumber(cargo.getPhoneNumber())
            .type(CargoTypeDto.builder()
                .id(cargo.getType().getId())
                .name(cargo.getType().getName())
                .code(cargo.getType().getCode())
                .build()
            )
            .createdAt(cargo.getCreatedAt())
            .unloadingPlace(cargo.getUnloadingPlace())
            .volume(cargo.getVolume())
            .weight(cargo.getWeight())
            .userId(cargo.getUserId())
            .loadingPlace(cargo.getLoadingPlace())
            .build();
    }

    @Test
    void create_Success() throws Exception {
        when(cargoMapper.toEntity(cargoRequest)).thenReturn(cargo);
        when(cargoService.create(cargo)).thenReturn(cargo);
        when(cargoMapper.toDto(cargo)).thenReturn(cargoResponse);

        mockMvc.perform(post("/api/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cargoRequest))
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isCreated());
    }

    @Test
    void create_InternalServerError() throws Exception {
        when(cargoMapper.toEntity(cargoRequest)).thenReturn(cargo);
        when(cargoService.create(cargo)).thenThrow(new InternalServerError("internal error"));

        mockMvc.perform(post("/api/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cargoRequest))
                .header("Authorization", "Bearer " + accessToken))
            .andExpect(status().isInternalServerError());
    }

    @Test
    void createInvalidToken_Unauthorized() throws Exception {
        mockMvc.perform(post("/api/cargo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cargoRequest))
                .header("Authorization", "Bearer " + accessToken + "1"))
            .andExpect(status().isUnauthorized());
    }

}
