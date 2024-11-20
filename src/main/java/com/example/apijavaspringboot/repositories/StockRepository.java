package com.example.apijavaspringboot.repositories;

import com.example.apijavaspringboot.entities.Stock;
import com.example.apijavaspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
