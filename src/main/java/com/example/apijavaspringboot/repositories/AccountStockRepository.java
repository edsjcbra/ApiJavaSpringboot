package com.example.apijavaspringboot.repositories;

import com.example.apijavaspringboot.entities.AccountStock;
import com.example.apijavaspringboot.entities.AccountStockId;
import com.example.apijavaspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
