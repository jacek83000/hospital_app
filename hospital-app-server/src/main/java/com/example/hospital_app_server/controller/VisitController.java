package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.mapper.VisitMapper;
import com.example.hospital_app_server.dto.request.VisitCreateReqDTO;
import com.example.hospital_app_server.dto.request.VisitUpdateReqDTO;
import com.example.hospital_app_server.dto.response.StringResDTO;
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
    private final VisitMapper mapper;

    public VisitController(VisitService service, VisitMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisit(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@Valid @RequestBody VisitCreateReqDTO dto) {
        Visit visit = mapper.toEntity(dto);
        return new ResponseEntity<>(service.create(visit), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Visit> updateVisit(@Valid @RequestBody VisitUpdateReqDTO dto) {
        Visit visit = mapper.toEntity(dto);
        return new ResponseEntity<>(service.update(visit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResDTO> deleteVisit(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.visit.deleted", id);
        return new ResponseEntity<>(new StringResDTO(message), HttpStatus.OK);
    }
}