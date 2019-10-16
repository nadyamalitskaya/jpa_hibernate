package com.nixsolution.jpa_hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "order_status")
public class OrderStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    private List<UserOrder> userOrderList;

    public OrderStatus() {
    }

    public OrderStatus(String name, List<UserOrder> userOrderList) {
        this.name = name;
        this.userOrderList = userOrderList;
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

    public List<UserOrder> getUserOrderList() {
        return userOrderList;
    }

    public void setUserOrderList(List<UserOrder> userOrderList) {
        this.userOrderList = userOrderList;
    }
}
