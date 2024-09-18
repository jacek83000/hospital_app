package com.example.hospital_app_server.repository;

import com.example.hospital_app_server.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
}
