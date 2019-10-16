package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "composition_comment")
public class CompositionComment implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SystemUser user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;
    @NotNull
    @Length(min = 10, max = 300)
    @Column(name = "comment")
    private String comment;
    @NotNull
    @Column(name = "comment_date")
    private Date commentDate;

    public CompositionComment() {
    }

    public CompositionComment(SystemUser user, Book book, String comment, Date commentDate) {
        this.user = user;
        this.book = book;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
