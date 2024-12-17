package com.example.hospital_app_server.service;

import com.example.hospital_app_server.dto.response.CompanyGetResDTO;

public interface CompanyService {
    CompanyGetResDTO findById(int id);
}
