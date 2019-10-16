package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "system_user")
public class SystemUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=15, message="Firsy naame should be between 5 - 10 characters.")
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Length(min = 2, max = 25, message="Last name should be between 5 - 10 characters.")
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Length(min = 8, max = 9)
    @Column(name = "passport_number", unique = true)
    private String passportNumber;
    @NotNull
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Length(min = 5, max = 35)
    @Column(name = "address")
    private String address;
    @Past
    @Column(name = "birthday")
    private Date birthday;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "role_id")
    private UserRole userRole;
    @OneToOne
    @JoinColumn(name = "login_id", unique = true, nullable = false)
    private UserLogin userLogin;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<CompositionComment> compositionComments;
    @OneToMany(mappedBy = "systemUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StatusComment> statusComments;
    @OneToMany(mappedBy = "systemUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserOrder> userOrders;

    public SystemUser() {
    }

    public SystemUser(String firstName, String lastName, UserLogin userLogin,
            String passportNumber, String phoneNumber, String address,
            Date birthday, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userLogin = userLogin;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthday = birthday;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<CompositionComment> getCompositionComments() {
        return compositionComments;
    }

    public void setCompositionComments(
            List<CompositionComment> compositionComments) {
        this.compositionComments = compositionComments;
    }

    public List<StatusComment> getStatusComments() {
        return statusComments;
    }

    public void setStatusComments(List<StatusComment> statusComments) {
        this.statusComments = statusComments;
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }
}
