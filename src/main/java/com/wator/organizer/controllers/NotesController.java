package com.wator.organizer.controllers;

import com.wator.organizer.entities.CalculatorEntity;
import com.wator.organizer.entities.NotesEntity;
import com.wator.organizer.repositories.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class NotesController {
    @Autowired
    private NotesRepository notesRepository;

    @GetMapping(
            value = "/api/notes/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<List<NotesEntity>> getUserData(HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");

        return ResponseEntity.ok(this.notesRepository.findAllByUserid(loggedUserId));
    }

    @PostMapping(
            value = "/api/notes/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<NotesEntity> addToUserHistory(@RequestBody NotesEntity notesEntity, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer loggedUserId = (Integer) session.getAttribute("logged-user-id");
        notesEntity.setUserid(loggedUserId.intValue());
        notesEntity.setDate(new Date());
        return ResponseEntity.ok(this.notesRepository.save(notesEntity));
    }

}
