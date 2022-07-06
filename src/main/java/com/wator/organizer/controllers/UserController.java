package com.wator.organizer.controllers;

import com.wator.organizer.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(
            value = "/user/add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public Object addUser(@RequestBody UserEntity data){
        String sql = "INSERT INTO users (FirstName, SecondName, Email, UserPassword) VALUES (?, ?, ?, ? )";
        int result = jdbcTemplate.update(sql, data.getFirstName(), data.getSecondName(), data.getEmail(), data.getPassword());
        if (result > 0) {
            System.out.println("new raw is added");
        }

        return data;
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
