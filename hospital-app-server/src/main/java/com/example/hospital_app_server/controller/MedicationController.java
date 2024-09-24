package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.StringResponseDTO;
import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.service.MedicationService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService service;

    public MedicationController(MedicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> readAllMedications() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> readMedication(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medication> createMedication(@Valid @RequestBody Medication medication) {
        return new ResponseEntity<>(service.create(medication), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Medication> updateMedication(@Valid @RequestBody Medication medication) {
        return new ResponseEntity<>(service.update(medication), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResponseDTO> deleteMedication(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.medication.deleted", id);
        return new ResponseEntity<>(new StringResponseDTO(message), HttpStatus.OK);
    }
}
