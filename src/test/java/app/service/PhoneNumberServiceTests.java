package app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.entity.PhoneNumber;
import app.entity.Status;
import app.repository.PhoneNumberRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

@DisplayName("UserService")
@ExtendWith(MockitoExtension.class)
public class PhoneNumberServiceTests {

    @Mock
    transient PhoneNumberRepository phoneNumberRepository;

    @InjectMocks
    transient PhoneNumberService phoneNumberService;

    @Test
    @DisplayName("#findAll returns an array of phoneNumbers")
    void findAll() {

        List<PhoneNumber> tasks = new ArrayList<>();
        Page<PhoneNumber> pagedTasks = new PageImpl(tasks);

        Page<PhoneNumber> dummyPage = new PageImpl<PhoneNumber>(Arrays.asList(new PhoneNumber(1, "A", Status.ACTIVE)));
        when(phoneNumberRepository.findAll(isA(Pageable.class))).thenReturn(dummyPage);
        assertThat(phoneNumberService.findAll(0, 1, "id")).size().isEqualTo(1);
    }

    @Test
    @DisplayName("#findById returns an phoneNumber")
    void findById() {
        Optional<PhoneNumber> phoneNumber = Optional.of(new PhoneNumber());
        when(phoneNumberRepository.findById(1)).thenReturn(phoneNumber);

        assertThat(phoneNumberService.findById(1)).isInstanceOf(PhoneNumber.class);
    }

    @Test
    @DisplayName("#create returns a new phoneNumber")
    void createPhoneNumber() {
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(new PhoneNumber());

        assertThat(phoneNumberService.create(new PhoneNumber())).isInstanceOf(PhoneNumber.class);
    }

    @Test
    @DisplayName("#update returns an updated phoneNumber")
    void updatePhoneNumber() {
        when(phoneNumberRepository.save(any(PhoneNumber.class))).thenReturn(new PhoneNumber());

        assertThat(phoneNumberService.update(1, new PhoneNumber())).isInstanceOf(PhoneNumber.class);
    }

    @Test
    @DisplayName("#delete deletes the current phoneNumber")
    void deletePhoneNumber() {
        phoneNumberService.delete(1);

        verify(phoneNumberRepository).deleteById(1);
    }
}
