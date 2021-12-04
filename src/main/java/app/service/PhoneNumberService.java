package app.service;

import app.entity.PhoneNumber;
import app.repository.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    @Autowired
    private transient PhoneNumberRepository phoneNumberRepository;

    /**
     * Gets a list of Phone numbers.
     * @return A list of Phone numbers.
     */
    public List<PhoneNumber> findAll(Integer pageNo, Integer pageSize, String sortBy) {

            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

            Page<PhoneNumber> pagedResult = phoneNumberRepository.findAll(paging);

            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<>();
            }
        }


    /**
     * Gets a Phone number by id.
     * @param id - The id of the Phone number.
     * @return The Phone number.
     */
    public PhoneNumber findById(Integer id) {
        return phoneNumberRepository.findById(id).orElseThrow();
    }

    /**
     * Creates a Phone number.
     * @param phoneNumber - The Phone number to insert.
     * @return The inserted Phone number.
     */
    public PhoneNumber create(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    /**
     * Updates a Phone number.
     * @param id - The id of the Phone number.
     * @param phoneNumber - The Phone number to update.
     * @return The updated Phone number.
     */
    public PhoneNumber update(Integer id, PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    /**
     * Deletes a Phone number by id.
     * @param id - The id of the Phone number to delete.
     */
    public void delete(Integer id) {
        phoneNumberRepository.deleteById(id);
    }
}
