package app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.entity.Customer;
import app.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserService")
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
    transient CustomerRepository customerRepository;

    @InjectMocks
    transient CustomerService customerService;

    @Test
    @DisplayName("#findAll returns an array of customers")
    void findAll() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        assertThat(customerService.findAll()).isEqualTo(new ArrayList<>());
    }

    @Test
    @DisplayName("#findById returns an customer")
    void findById() {
        Optional<Customer> customer = Optional.of(new Customer());
        when(customerRepository.findById(1)).thenReturn(customer);

        assertThat(customerService.findById(1)).isInstanceOf(Customer.class);
    }

    @Test
    @DisplayName("#create returns a new customer")
    void createCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());

        assertThat(customerService.create(new Customer())).isInstanceOf(Customer.class);
    }

    @Test
    @DisplayName("#update returns an updated customer")
    void updateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());

        assertThat(customerService.update(1, new Customer())).isInstanceOf(Customer.class);
    }

    @Test
    @DisplayName("#delete deletes the current customer")
    void deleteCustomer() {
        customerService.delete(1);

        verify(customerRepository).deleteById(1);
    }
}
