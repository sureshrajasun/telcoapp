package app.service;

import app.entity.Customer;
import app.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

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
    @Transactional
    public Customer update(Integer id, Customer customer) throws EntityNotFoundException  {
        try{
            Customer cust = customerRepository.getById(id);
            customer.getPhoneNumbers().addAll(cust.getPhoneNumbers());
            return customerRepository.save(customer);

        }catch (EntityNotFoundException e){
            System.out.println(e.getMessage());
            throw e;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }

    }

    /**
     * Deletes a customer by id.
     * @param id - The id of the customer to delete.
     */
    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }
}
