package com.example.hospital_app_server.controller;

import com.example.hospital_app_server.dto.mapper.ReceiptMapper;
import com.example.hospital_app_server.dto.request.ReceiptCreateReqDTO;
import com.example.hospital_app_server.dto.request.ReceiptUpdateReqDTO;
import com.example.hospital_app_server.dto.response.StringResDTO;
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
    private final ReceiptMapper mapper;

    public ReceiptController(ReceiptService service, ReceiptMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable int id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@Valid @RequestBody ReceiptCreateReqDTO dto) {
        Receipt receipt = mapper.toEntity(dto);
        return new ResponseEntity<>(service.create(receipt), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Receipt> updateReceipt(@Valid @RequestBody ReceiptUpdateReqDTO dto) {
        Receipt receipt = mapper.toEntity(dto);
        return new ResponseEntity<>(service.update(receipt), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StringResDTO> deleteReceipt(@PathVariable int id) {
        service.deleteById(id);
        String message = MessageUtil.getMessage("messages.resource.receipt.deleted", id);
        return new ResponseEntity<>(new StringResDTO(message), HttpStatus.OK);
    }
}
