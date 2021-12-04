package app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    public String name;

    @NotNull
    @Column(name = "email", nullable = false)
    public String email;

    @NotNull
    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable( name="customer_phone",
        joinColumns=@JoinColumn( name="customer_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="phoneNumbers_id", referencedColumnName="id"))
    private List<PhoneNumber> phoneNumbers= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
