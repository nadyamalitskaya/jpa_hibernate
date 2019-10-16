package com.nixsolution.jpa_hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_order")
public class UserOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private SystemUser systemUser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @NotNull
    @Column(name = "address", nullable = false)
    private String address;
    @NotNull
    @FutureOrPresent
    @Column(name = "order_date")
    private Date orderDate;
    @NotNull
    @Future
    @Column(name = "return_date")
    private Date returnDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    public UserOrder() {
    }

    public UserOrder(SystemUser systemUser, Item item, String address,
            Date orderDate, Date returnDate, OrderStatus orderStatus) {
        this.systemUser = systemUser;
        this.item = item;
        this.address = address;
        this.orderDate = orderDate;
        this.returnDate = returnDate;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
