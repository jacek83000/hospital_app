package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.mapper.MedicationMapper;
import com.example.hospital_app_server.dto.response.MedicationGetResDTO;
import com.example.hospital_app_server.dto.response.StringResDTO;
import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.service.CompanyService;
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
    private final MedicationMapper mapper;

    public MedicationController(MedicationService service, MedicationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationGetResDTO> getMedication(@PathVariable int id) {
        Medication medication = service.findById(id);
        return new ResponseEntity<>(mapper.toDTO(medication), HttpStatus.OK);
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
    public ResponseEntity<StringResDTO> deleteMedication(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.medication.deleted", id);
        return new ResponseEntity<>(new StringResDTO(message), HttpStatus.OK);
    }
}
