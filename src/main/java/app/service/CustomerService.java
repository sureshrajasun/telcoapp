package app.service;

import app.entity.Customer;
import app.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private transient CustomerRepository customerRepository;

    /**
     * Gets a list of customers.
     * @return A list of customers.
     */
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Gets a customer by id.
     * @param id - The id of the customer.
     * @return The customer.
     */
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow();
    }

    /**
     * Creates a customer.
     * @param customer - The customer to insert.
     * @return The inserted customer.
     */
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Updates a customer.
     * @param id - The id of the customer.
     * @param customer - The customer to update.
     * @return The updated customer.
     */
    public Customer update(Integer id, Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Deletes a customer by id.
     * @param id - The id of the customer to delete.
     */
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
