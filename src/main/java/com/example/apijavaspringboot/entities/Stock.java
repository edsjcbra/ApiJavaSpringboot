package com.example.apijavaspringboot.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_stocks")
public class Stock {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "stock")
    private List<AccountStock> stockList;

    public Stock() {
    }

    public Stock(String id, String description, List<AccountStock> stockList) {
        this.id = id;
        this.description = description;
        this.stockList = stockList;
    }

    public Stock(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
