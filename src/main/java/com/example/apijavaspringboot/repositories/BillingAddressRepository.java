package com.example.apijavaspringboot.repositories;

import com.example.apijavaspringboot.entities.BillingAddress;
import com.example.apijavaspringboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {
}
