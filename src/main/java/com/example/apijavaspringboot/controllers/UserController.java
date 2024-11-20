package com.example.apijavaspringboot.controllers;

import com.example.apijavaspringboot.entities.User;
import com.example.apijavaspringboot.entities.dtos.*;
import com.example.apijavaspringboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {

        var userid = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/v1/users/" + userid.toString())).build();
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") UUID userid) {

        var user = userService.getUserById(userid);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUses() {
        var users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable("userid") UUID userid, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(userid, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") UUID userid) {
        userService.deleteUser(userid);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userid}/accounts")
    public ResponseEntity<Void> createAccount(@PathVariable("userid") UUID userid, @RequestBody CreateAccountDto createAccountDto) {

        userService.createAccount(userid, createAccountDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userid}/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccounts(@PathVariable("userid") UUID userid) {

        var accounts = userService.listAccounts(userid);

        return ResponseEntity.ok(accounts);
    }
}
