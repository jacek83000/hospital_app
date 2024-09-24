package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.StringResponseDTO;
import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.service.ReceiptService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService service;

    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Receipt>> readAllReceipts() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> readReceipt(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@Valid @RequestBody Receipt receipt) {
        return new ResponseEntity<>(service.create(receipt), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Receipt> updateReceipt(@Valid @RequestBody Receipt receipt) {
        return new ResponseEntity<>(service.update(receipt), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResponseDTO> deleteReceipt(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.receipt.deleted", id);
        return new ResponseEntity<>(new StringResponseDTO(message), HttpStatus.OK);
    }
}
