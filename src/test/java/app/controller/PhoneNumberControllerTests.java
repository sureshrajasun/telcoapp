package app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.entity.PhoneNumber;
import app.service.PhoneNumberService;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("UserController")
@ExtendWith(MockitoExtension.class)
public class PhoneNumberControllerTests {

    @Mock
    transient PhoneNumberService phoneNumberService;

    @InjectMocks
    transient PhoneNumberController phoneNumberController;

    @Test
    @DisplayName("#findAll returns an array of phoneNumbers")
    void findAll() {
        when(phoneNumberService.findAll(anyInt(), anyInt(), anyString())).thenReturn(new ArrayList<>());

        assertThat(phoneNumberController.findAll(0,2,"ID")).isEqualTo(new ArrayList<>());
    }

    @Test
    @DisplayName("#findById returns an phoneNumber")
    void findById() {
        PhoneNumber phoneNumber = new PhoneNumber();
        when(phoneNumberService.findById(1)).thenReturn(phoneNumber);

        assertThat(phoneNumberController.findById(1)).isEqualTo(phoneNumber);
    }

    @Test
    @DisplayName("#create returns a new phoneNumber")
    void createUser() {
        PhoneNumber phoneNumber = new PhoneNumber();
        when(phoneNumberService.create(phoneNumber)).thenReturn(phoneNumber);

        assertThat(phoneNumberController.create(phoneNumber)).isEqualTo(phoneNumber);
    }

    @Test
    @DisplayName("#update returns an updated phoneNumber")
    void updatePhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber();
        when(phoneNumberService.update(1, phoneNumber)).thenReturn(phoneNumber);

        assertThat(phoneNumberController.update(1, phoneNumber)).isEqualTo(phoneNumber);
    }

    @Test
    @DisplayName("#delete calls #delete method")
    void deletePhoneNumber() {
        phoneNumberController.delete(1);

        verify(phoneNumberService).delete(1);
    }
}
