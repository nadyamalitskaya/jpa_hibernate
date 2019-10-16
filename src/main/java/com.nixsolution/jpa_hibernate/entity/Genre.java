package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 2)
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "description")
    @Length(min = 40)
    private String description;
    @ManyToMany(mappedBy = "genres")
    private Set<Book> bookSet;

    public Genre() {
    }

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }
}
