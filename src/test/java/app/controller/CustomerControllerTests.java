package app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.entity.Customer;
import app.service.CustomerService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserController")
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {

    @Mock
    transient CustomerService customerService;

    @InjectMocks
    transient CustomerController customerController;

    @Test
    @DisplayName("#findAll returns an array of customers")
    void findAll() {
        when(customerService.findAll()).thenReturn(new ArrayList<>());

        assertThat(customerController.findAll()).isEqualTo(new ArrayList<>());
    }

    @Test
    @DisplayName("#findById returns an customer")
    void findById() {
        Customer customer = new Customer();
        when(customerService.findById(1)).thenReturn(customer);

        assertThat(customerController.findById(1)).isEqualTo(customer);
    }

    @Test
    @DisplayName("#create returns a new customer")
    void createUser() {
        Customer customer = new Customer();
        when(customerService.create(customer)).thenReturn(customer);

        assertThat(customerController.create(customer)).isEqualTo(customer);
    }

    @Test
    @DisplayName("#update returns an updated customer")
    void updateCustomer() {
        Customer customer = new Customer();
        when(customerService.update(1, customer)).thenReturn(customer);

        assertThat(customerController.update(1, customer)).isEqualTo(customer);
    }

    @Test
    @DisplayName("#delete calls #delete method")
    void deleteCustomer() {
        customerController.delete(1);

        verify(customerService).delete(1);
    }
}
