package com.example.hospital_app_server.dto.mapper;

import com.example.hospital_app_server.dto.response.CompanyGetResDTO;
import com.example.hospital_app_server.dto.response.MedicationGetResDTO;
import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.service.CompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {
    private final CompanyService companyService;

    public MedicationMapper(CompanyService companyService) {
        this.companyService = companyService;
    }

    public MedicationGetResDTO toDTO(Medication medication) {
        CompanyGetResDTO companyDTO = companyService.findById(medication.getCompanyId());

        MedicationGetResDTO medicationDTO = new MedicationGetResDTO();
        BeanUtils.copyProperties(medication, medicationDTO);
        medicationDTO.setCompanyName(companyDTO.getName());
        medicationDTO.setCompanyContactNumber(companyDTO.getPhone());
        medicationDTO.setCompanyWebsite(companyDTO.getWebsite());

        return medicationDTO;
    }
}
