package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.StringResponseDTO;
import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.service.DoctorService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> readAllDoctors() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> readDoctor(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        return new ResponseEntity<>(service.create(doctor), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor) {
        return new ResponseEntity<>(service.update(doctor), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResponseDTO> deleteDoctor(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.doctor.deleted", id);
        return new ResponseEntity<>(new StringResponseDTO(message), HttpStatus.OK);
    }
}
