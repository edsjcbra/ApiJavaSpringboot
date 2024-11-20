package com.example.apijavaspringboot.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private BillingAddress billingAddress;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStockList;

    public Account() {
    }

    public Account(UUID id, String description, User user, BillingAddress billingAddress, List<AccountStock> accountStockList) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.billingAddress = billingAddress;
        this.accountStockList = accountStockList;
    }

    public Account(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<AccountStock> getAccountStockList() {
        return accountStockList;
    }

    public void setAccountStockList(List<AccountStock> accountStockList) {
        this.accountStockList = accountStockList;
    }
}
