package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.repository.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository repository;

    public ReceiptServiceImpl(ReceiptRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Receipt> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Receipt> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public void save(Receipt receipt) {
        repository.save(receipt);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
