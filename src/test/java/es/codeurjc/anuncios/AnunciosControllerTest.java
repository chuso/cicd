package es.codeurjc.anuncios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = AnunciosControllerTest.Initializer.class)
public class AnunciosControllerTest{
	
	@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7")
      .withDatabaseName("test")
      .withUsername("user")
      .withPassword("pass");
	
	static class Initializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
              "spring.datasource.url=" + mySQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @LocalServerPort
    int port;

	@BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void createNewItemTest(){
        given().
        when().
            get("/anuncios/").
		then().
            statusCode(200).
            body(
                "nombre", hasItems("Pepe", "Juan")
            );
    }

}
