package com.wator.organizer.controllers;


import com.wator.organizer.entities.LogsEntity;
import com.wator.organizer.repositories.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
public class LogsController {
    @Autowired
    private LogsRepository logsRepository;

    @GetMapping(
            value = "/api/logs",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<LogsEntity>> getUserLogs(HttpServletRequest request){

        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");

        return ResponseEntity.ok(this.logsRepository.findAllByUserid(loggedUserId));
    }
    @PostMapping(
            value = "/api/logs/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<LogsEntity> addToLogs( HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");
        LogsEntity logsEntity = new LogsEntity();
        logsEntity.setuserId(loggedUserId.intValue());
        logsEntity.setDate(new Date());

        return ResponseEntity.ok(this.logsRepository.save(logsEntity));
    }


}
