package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 15, message = "Firsy naame should be between 5 - 10 characters.")
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Length(min = 2, max = 25, message = "Middle name should be between 5 - 10 characters.")
    @Column(name = "middle_name")
    private String middleName;
    @Length(min = 2, max = 25, message = "Last name should be between 5 - 10 characters.")
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "sex")
    private String sex;
    @Past
    @Column(name = "birthday")
    private Date birthday;
    @ManyToMany(mappedBy = "authors",fetch = FetchType.LAZY)
    private Set<Book> bookSet;

    public Author() {
    }

    public Author(String firstName, String middleName, String lastName,
            String sex, Date birthday) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }
}
