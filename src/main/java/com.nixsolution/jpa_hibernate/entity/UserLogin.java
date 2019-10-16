package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_login")
public class UserLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 8, max = 15)
    @Column(name = "login_name")
    private String loginName;
    @NotNull
    @Length(min = 8, max = 20)
    @Column(name = "password")
    private String password;
    @NotNull
    @Length(min = 8, max = 20)
    @Email
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "userLogin")
    private SystemUser systemUser;

    public UserLogin() {
    }

    public UserLogin(String loginName, String password, String email) {
        this.loginName = loginName;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
