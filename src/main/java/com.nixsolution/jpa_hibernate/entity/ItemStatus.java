package com.nixsolution.jpa_hibernate.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "item_status")
public class ItemStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Length(min = 2, max = 30)
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "itemStatus", cascade = CascadeType.ALL)
    private List<Item> itemList;

    public ItemStatus() {
    }

    public ItemStatus(String name, List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
