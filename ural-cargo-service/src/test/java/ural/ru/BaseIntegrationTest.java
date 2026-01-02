package ural.ru;

import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.boot.test.web.server.*;
import org.springframework.boot.web.client.*;
import ural.*;

import org.junit.jupiter.api.*;
import ural.ru.repositories.CargoRepository;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.cache.type=none"
)
public class BaseIntegrationTest extends ContextInitializer {

    protected TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @AfterEach
    void setup() {
        cargoRepository.deleteAll();
    }

    @PostConstruct
    public void init() {
        RestTemplateBuilder restTemplate = restTemplateBuilder.rootUri("http://localhost:" + port);
        this.testRestTemplate = new TestRestTemplate(restTemplate);
    }

}
