package com.example.apijavaspringboot.services;

import com.example.apijavaspringboot.entities.Account;
import com.example.apijavaspringboot.entities.BillingAddress;
import com.example.apijavaspringboot.entities.Stock;
import com.example.apijavaspringboot.entities.User;
import com.example.apijavaspringboot.entities.dtos.*;
import com.example.apijavaspringboot.repositories.AccountRepository;
import com.example.apijavaspringboot.repositories.BillingAddressRepository;
import com.example.apijavaspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;


    public UUID createUser(CreateUserDto createUserDto) {

        // DTO -> ENTITY
        User entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );
        var userToSave = userRepository.save(entity);
        return userToSave.getId();
    }

    public Optional<User> getUserById(UUID userid) {
        return userRepository.findById(userid);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(UUID userid, UpdateUserDto updateUserDto) {
        var userToUpdate = userRepository.findById(userid);

        if (userToUpdate.isPresent()) {
            var user = userToUpdate.get();
            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);
        }
    }

    public void deleteUser(UUID userid) {
        var userExists = userRepository.existsById(userid);

        if (userExists) {
            userRepository.deleteById(userid);
        }
    }

    public void createAccount(UUID userid, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(userid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        var account = new Account(
                UUID.randomUUID(),
                createAccountDto.description(),
                user,
                null,
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAdress = new BillingAddress(
                accountCreated.getId(),
                createAccountDto.street(),
                createAccountDto.number(),
                account
        );

        billingAddressRepository.save(billingAdress);
    }

    public List<AccountResponseDto> listAccounts(UUID userid) {

        var user = userRepository.findById(userid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return  user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getId(), ac.getDescription())).toList();
    }
}
