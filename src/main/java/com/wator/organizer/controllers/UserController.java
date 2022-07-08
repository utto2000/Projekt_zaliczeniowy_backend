
package com.wator.organizer.controllers;

import com.wator.organizer.controllers.LogsController.*;
import com.wator.organizer.entities.UserEntity;
import com.wator.organizer.repositories.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private LogsController logsController;

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
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Integer userId) {
        return ResponseEntity.of(this.usersRepository.findById(Long.valueOf(userId)));
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
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Integer userId, @RequestBody UserEntity newUserEntity) {
        Optional<UserEntity> foundUserOptional = this.usersRepository.findById(Long.valueOf(userId));
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
    public ResponseEntity<UserEntity> removeUser(@PathVariable("id") Integer userId) {
        Optional<UserEntity> foundUserOptional = this.usersRepository.findById(Long.valueOf(userId));
        if (foundUserOptional.isPresent()) {
            this.usersRepository.deleteById(Long.valueOf(userId));
        }
        return ResponseEntity.of(foundUserOptional);
    }

    @GetMapping (
            value = "/api/users/login/{email}/{password}",

            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<UserEntity> loginUser(@PathVariable("email") String email , @PathVariable("password")String password, HttpServletRequest request){

        Optional<UserEntity> u = this.usersRepository.findByEmailAndPassword(email,password);
     if (u.isPresent()) {
         HttpSession session = request.getSession();

         System.out.println(session);
         Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");

         session.setAttribute("logged-user-id", u.get().getId());
         System.out.println(session.getAttribute("logged-user-id"));
         logsController.addToLogs(request);
         return ResponseEntity.of(this.usersRepository.findById(u.get().getId()));

     }else {
         logsController.addToLogs(request);
         return ResponseEntity.of(this.usersRepository.findById(0));
     }


    }

    @GetMapping("/api/user/{id}/home")
    public String userHome(@PathVariable("id") Integer UserId, HttpServletRequest request){
        if(checkIfSessionWasLogedIn(request)) {
            return "youAreLogedIn";
        }else{
            return "index";
        }
    }


    public boolean checkIfSessionWasLogedIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");
        if (loggedUserId == null){
            return false;
        }else {
            return true;
        }

    }

}
