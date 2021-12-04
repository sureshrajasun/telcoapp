package app.controller;

import app.entity.PhoneNumber;
import app.service.PhoneNumberService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phonenumber")
public class PhoneNumberController {

    @Autowired
    private transient PhoneNumberService phoneNumberService;

    @GetMapping
    public List<PhoneNumber> findAll( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy ) {
        return phoneNumberService.findAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public PhoneNumber findById(@PathVariable Integer id) {
        return phoneNumberService.findById(id);
    }

    @PostMapping
    public PhoneNumber create(@Valid @RequestBody PhoneNumber phoneNumber) {
        return phoneNumberService.create(phoneNumber);
    }

    @PutMapping("/{id}")
    public PhoneNumber update(@PathVariable Integer id, @Valid @RequestBody PhoneNumber phoneNumber) {
        return phoneNumberService.update(id, phoneNumber);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        phoneNumberService.delete(id);
    }
}


