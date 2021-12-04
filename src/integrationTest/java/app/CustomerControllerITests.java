package app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Customer")
@SpringBootTest
@Testcontainers
@TestMethodOrder(OrderAnnotation.class)
public class CustomerControllerITests {

    @Autowired
    transient MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres")
        .withExposedPorts(5432)
        .withDatabaseName("test")
        .withUsername("test")
        .withPassword("test")
        .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", postgres::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", postgres::getPassword);
        propertyRegistry.add("spring.datasource.name", postgres::getUsername);
    }

    @Test
    @Order(1)
    @DisplayName("/customer (GET)")
    void getAllUsers() throws Exception {
        MvcResult response = mockMvc.perform(get("/customer")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(2)
    @DisplayName("/customer (POST)")
    void addUser() throws Exception {
        String customer = "{\"name\":\"name\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(post("/customer").content(customer).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(3)
    @DisplayName("/customer/{id} (PUT)")
    void updateUser() throws Exception {
        String customer = "{\"name\":\"name\",\"email\":\"string@string.com\"}";
        MvcResult response = mockMvc
            .perform(put("/customer/2").content(customer).contentType(APPLICATION_JSON))
            .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(4)
    @DisplayName("/customer/{id} (GET)")
    void getUserById() throws Exception {
        MvcResult response = mockMvc.perform(get("/customer/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(5)
    @DisplayName("/customer/{id} (DELETE)")
    void deleteUser() throws Exception {
        MvcResult response = mockMvc.perform(delete("/customer/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }
}
