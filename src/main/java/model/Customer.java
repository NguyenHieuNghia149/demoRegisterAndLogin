package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends User {

    @Column(nullable = true)
    private String phoneNumber;

    @Column(nullable = true)
    private Date registerDate;

    @Column(nullable = true)
    private Date birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "userId") // Chỉnh sửa tên khóa ngoại cho đúng
    private List<Address> addresses;

    public Customer() {
        super(); // Gọi constructor của lớp cha
        this.phoneNumber = "";
        this.registerDate = new Date();
        this.birthDate = new Date();
        this.addresses = new ArrayList<>();
    }

    public Customer(String firstName, String lastName, String email, String password, String status, String phoneNumber, Date registerDate, Date birthDate) {
        super(firstName, lastName, email, password, status);
        this.phoneNumber = phoneNumber;
        this.registerDate = registerDate;
        this.birthDate = birthDate;
        this.addresses = new ArrayList<>();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
