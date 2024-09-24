package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.StringResponseDTO;
import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.service.VisitService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {
    private final VisitService service;

    public VisitController(VisitService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Visit>> readAllVisits() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> readVisit(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@Valid @RequestBody Visit visit) {
        return new ResponseEntity<>(service.create(visit), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Visit> updateVisit(@Valid @RequestBody Visit visit) {
        return new ResponseEntity<>(service.update(visit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResponseDTO> deleteVisit(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.visit.deleted", id);
        return new ResponseEntity<>(new StringResponseDTO(message), HttpStatus.OK);
    }
}