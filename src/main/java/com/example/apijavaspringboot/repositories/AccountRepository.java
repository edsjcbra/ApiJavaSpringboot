package com.example.apijavaspringboot.repositories;

import com.example.apijavaspringboot.entities.Account;
import com.example.apijavaspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
