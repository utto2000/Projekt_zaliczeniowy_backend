
package com.wator.organizer.controllers;

import com.wator.organizer.UserEntity;
import com.wator.organizer.repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;


    @GetMapping(
            value = "/api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<List<UserEntity>> getUsers() {
        return  ResponseEntity.ok(this.usersRepository.findAll());
    }


    @GetMapping(
            value = "/api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.of(this.usersRepository.findById(userId));
    }

    // POST http://localhost:8080/api/user/create
    // echo '{"name":"john","email":"john@email.com"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/user/create
    //
    @PostMapping(
            value = "/api/user/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok(this.usersRepository.save(userEntity));
    }

    // POST http://localhost:8080/api/users/1/update
    // echo '{"name":"chris","email":"chris@email.com"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/users/1/update
    //
    @PostMapping(
            value = "/api/users/{id}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long userId, @RequestBody UserEntity newUserEntity) {
        Optional<UserEntity> foundUserOptional = this.usersRepository.findById(userId);
        if (foundUserOptional.isPresent()) {
            UserEntity foundUserEntity = foundUserOptional.get();
            foundUserEntity.setFirstName (newUserEntity.getFirstName());
            foundUserEntity.setEmail(newUserEntity.getEmail());
            foundUserEntity.setPassword (newUserEntity.getPassword());
            foundUserEntity.setSecondName (newUserEntity.getSecondName());
            this.usersRepository.save(foundUserEntity);
        }
        return ResponseEntity.of(foundUserOptional);
    }

    // http://localhost:8080/api/users/1/remove
    //
    @GetMapping(
            value = "/api/users/{id}/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<UserEntity> removeUser(@PathVariable("id") Long userId) {
        Optional<UserEntity> foundUserOptional = this.usersRepository.findById(userId);
        if (foundUserOptional.isPresent()) {
            this.usersRepository.deleteById(userId);
        }
        return ResponseEntity.of(foundUserOptional);
    }
}
