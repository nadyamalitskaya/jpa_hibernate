package com.nixsolution.jpa_hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "address_in_storage", nullable = false)
    private String addressInStorage;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "volume")
    private int volume;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_genre",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<CompositionComment> compositionComments;

    public Book() {
    }

    public Book(String addressInStorage, String name, String language,
            int volume) {
        this.addressInStorage = addressInStorage;
        this.name = name;
        this.language = language;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressInStorage() {
        return addressInStorage;
    }

    public void setAddressInStorage(String addressInStorage) {
        this.addressInStorage = addressInStorage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<CompositionComment> getCompositionComments() {
        return compositionComments;
    }

    public void setCompositionComments(
            List<CompositionComment> compositionComments) {
        this.compositionComments = compositionComments;
    }
}
