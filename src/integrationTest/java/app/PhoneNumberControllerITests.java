package app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("PhoneNumber")
@SpringBootTest
@Testcontainers
@TestMethodOrder(OrderAnnotation.class)
public class PhoneNumberControllerITests {

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
    @DisplayName("/phonenumber(GET)")
    void getAllPhoneNumbers() throws Exception {
        MvcResult response = mockMvc.perform(get("/phonenumber")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(2)
    @DisplayName("/phonenumber(POST)")
    void addPhoneNumber() throws Exception {
        String phoneNumber = "{\"number\":\"0411272222\",\"status\":\"ACTIVE\"}";
        MvcResult response = mockMvc
                .perform(post("/phonenumber").content(phoneNumber).contentType(APPLICATION_JSON))
                .andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(4)
    @DisplayName("/phonenumber/{id} (GET)")
    void getPhoneNumberById() throws Exception {
        MvcResult response = mockMvc.perform(get("/phonenumber/1")).andReturn();

        assertThat(response.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(5)
    @DisplayName("/phonenumber/{id} (DELETE)")
    void deletePhoneNumber() throws Exception {

        String phoneNumber = "{\"number\":\"0411272222\",\"status\":\"INACTIVE\"}";
        MvcResult response = mockMvc
                .perform(post("/phonenumber").content(phoneNumber).contentType(APPLICATION_JSON))
                .andDo(print())
                .andReturn();
        System.out.println(response.toString());
        MvcResult response1 = mockMvc.perform(delete("/phonenumber/8")).andReturn();

        assertThat(response1.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    @Order(6)
    @DisplayName("/phonenumber/activate/{id} (POST)")
    void activatePhoneNumber() throws Exception {

        String phoneNumber = "{\"number\":\"0411272222\",\"status\":\"INACTIVE\"}";
        MvcResult response = mockMvc
                .perform(post("/phonenumber").content(phoneNumber).contentType(APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        MvcResult response1 = mockMvc.perform(put("/phonenumber/activate/0411272222"))
                .andReturn();

        assertThat(response1.getResponse().getStatus()).isEqualTo(200);
    }
}
