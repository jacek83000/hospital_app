package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Receipt;

import java.util.List;

public interface ReceiptService {
    Receipt findById(int id);

    List<Receipt> findAll();

    Receipt create(Receipt receipt);

    Receipt update(Receipt receipt);

    void deleteById(int id);
}
