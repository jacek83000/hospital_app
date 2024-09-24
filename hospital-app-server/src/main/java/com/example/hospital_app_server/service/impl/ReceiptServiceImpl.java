package com.example.hospital_app_server.service.impl;

import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.exception.ResourceNotFoundException;
import com.example.hospital_app_server.repository.ReceiptRepository;
import com.example.hospital_app_server.service.ReceiptService;
import com.example.hospital_app_server.utils.MessageUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository repository;

    public ReceiptServiceImpl(ReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public Receipt findById(int id) {
        String message = MessageUtil.getMessage("messages.resource.receipt.not-found", id);
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    @Override
    public List<Receipt> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Receipt create(Receipt receipt) {
        receipt.setId(0);
        return repository.save(receipt);
    }

    @Transactional
    @Override
    public Receipt update(Receipt receipt) {
        findById(receipt.getId());
        return repository.save(receipt);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        findById(id);
        repository.deleteById(id);
    }
}
