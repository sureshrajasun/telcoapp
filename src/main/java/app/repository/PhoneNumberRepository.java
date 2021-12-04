package app.repository;

import app.entity.PhoneNumber;
import app.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

    PhoneNumber findByNumber(String number);

    @Modifying
    @Query("update PhoneNumber p set p.status = :status where p.id = :id")
    void updateStatus(@Param(value = "id") Integer id, @Param(value = "status") Status status);


}
