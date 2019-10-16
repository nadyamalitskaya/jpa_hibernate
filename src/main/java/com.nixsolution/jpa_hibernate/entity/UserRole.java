package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 2, max = 10)
    @Column(name = "role_name", unique = true)
    private String roleName;
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<SystemUser> systemUsers;

    public UserRole() {
    }

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public void setSystemUsers(List<SystemUser> systemUsers) {
        this.systemUsers = systemUsers;
    }
}