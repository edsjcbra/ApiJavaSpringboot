package com.example.apijavaspringboot.services;

import com.example.apijavaspringboot.entities.User;
import com.example.apijavaspringboot.entities.dtos.CreateUserDto;
import com.example.apijavaspringboot.entities.dtos.UpdateUserDto;
import com.example.apijavaspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


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
}
