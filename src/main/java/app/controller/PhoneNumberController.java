package app.controller;

import app.entity.PhoneNumber;
import app.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/activate/{phoneNumber}")
    public ResponseEntity activate(@PathVariable String phoneNumber) {
         if(phoneNumberService.activate(phoneNumber)){
             return ResponseEntity.ok("Phone Number Activated!!");
         } else {
             return ResponseEntity
                     .status(HttpStatus.BAD_REQUEST)
                     .body("Phone Number not found or already activated!");
         }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        phoneNumberService.delete(id);
    }
}


