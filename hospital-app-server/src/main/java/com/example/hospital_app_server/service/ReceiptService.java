package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    Optional<Receipt> findById(int id);

    List<Receipt> findAll();

    void save(Receipt receipt);

    void deleteById(int id);
}
