package com.nixsolution.jpa_hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "publish_year")
    private int publishYear;
    @NotNull
    @Column(name = "publish_office")
    private String publishOffice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "book_id")
    private Book book;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_status_id")
    private ItemStatus itemStatus;
    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StatusComment> statusComments;
    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserOrder> userOrders;

    public Item() {
    }

    public Item(int publishYear, String publishOffice, Book book,
            ItemStatus itemStatus, List<StatusComment> statusComments) {
        this.publishYear = publishYear;
        this.publishOffice = publishOffice;
        this.book = book;
        this.itemStatus = itemStatus;
        this.statusComments = statusComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublishOffice() {
        return publishOffice;
    }

    public void setPublishOffice(String publishOffice) {
        this.publishOffice = publishOffice;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
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
