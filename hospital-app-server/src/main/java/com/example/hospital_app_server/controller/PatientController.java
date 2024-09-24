package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.StringResponseDTO;
import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.service.PatientService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> readAllPatients() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> readPatient(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        return new ResponseEntity<>(service.create(patient), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient) {
        return new ResponseEntity<>(service.update(patient), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResponseDTO> deletePatient(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.patient.deleted", id);
        return new ResponseEntity<>(new StringResponseDTO(message), HttpStatus.OK);
    }
}
