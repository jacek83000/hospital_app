package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}
