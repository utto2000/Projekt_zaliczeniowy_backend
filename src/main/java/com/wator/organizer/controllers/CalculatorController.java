package com.wator.organizer.controllers;

import com.wator.organizer.entities.CalculatorEntity;
import com.wator.organizer.entities.UserEntity;
import com.wator.organizer.repositories.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CalculatorController {
    @Autowired
    private CalculatorRepository calculatorRepository;

    @GetMapping(
            value = "/api/calculator/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<CalculatorEntity>> getUserData(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");

        return ResponseEntity.ok(this.calculatorRepository.findAllByUserId(loggedUserId));
    }

    @PostMapping(
            value = "/api/calculator/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<CalculatorEntity> addToUserHistory(@RequestBody CalculatorEntity calculatorEntity,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");
        calculatorEntity.setUserId(loggedUserId.intValue());
        System.out.println(loggedUserId);
        System.out.println(calculatorEntity.getUserId());
        return ResponseEntity.ok(this.calculatorRepository.save(calculatorEntity));
    }

}
