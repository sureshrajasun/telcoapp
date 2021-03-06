package app.service;

import app.entity.PhoneNumber;
import app.entity.Status;
import app.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneNumberService.class);

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
     * Updates a Phone number.
     * @param phoneNumber - The Phone number to activate.
     * @return The success status.
     */
    @Transactional
    public boolean activate(String phoneNumber) {
        try {
            PhoneNumber number = phoneNumberRepository.findByNumber(phoneNumber);
            if(number != null && Status.INACTIVE.equals(number.getStatus())) {
                phoneNumberRepository.updateStatus(number.getId(), Status.ACTIVE);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            logger.error("Exception ",e);
            return false;
        }
    }

    /**
     * Deletes a Phone number by id.
     * @param id - The id of the Phone number to delete.
     */
    public void delete(Integer id) {
        phoneNumberRepository.deleteById(id);
    }
}
