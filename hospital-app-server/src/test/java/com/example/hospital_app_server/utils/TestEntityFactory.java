package com.example.hospital_app_server.utils;

import com.example.hospital_app_server.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class TestEntityFactory {
    private TestEntityFactory() {
    }

    public static Doctor getValidDoctor(Visit visit) {
        Doctor doctor = new Doctor();
        doctor.setFirstName("string");
        doctor.setLastName("string");
        doctor.setEmail("string@hospital.com");
        doctor.setContactNumber("string");
        doctor.setSpecialization("string");
        doctor.setYearsOfExperience(20);
        doctor.setVisits(Collections.singletonList(visit));
        return doctor;
    }

    public static Patient getValidPatient(Visit visit) {
        Patient patient = new Patient();
        patient.setFirstName("string");
        patient.setLastName("string");
        patient.setContactNumber("123");
        patient.setEmail("string@hospital.com");
        patient.setAge(30);
        patient.setAddress("string");
        patient.setSex("male");
        patient.setVisits(Collections.singletonList(visit));

        return patient;
    }

    public static Medication getValidMedication(Receipt receipt) {
        Medication medication = new Medication();
        medication.setName("string");
        medication.setCompanyId(1);
        medication.setPrice(10.0);
        medication.getReceipts().add(receipt);

        return medication;
    }

    public static Receipt getValidReceipt() {
        Receipt receipt = new Receipt();
        receipt.getMedications().addAll(
                IntStream.range(0, 2).boxed().map(_ -> getValidMedication(receipt)).collect(Collectors.toSet()));
        receipt.setVisit(getValidVisit(receipt));
        receipt.setCreatedAt(LocalDateTime.now());
        receipt.setExpirationDate(LocalDate.now().atStartOfDay().plusDays(14));
        return receipt;
    }

    public static Visit getValidVisit(Receipt receipt) {
        Visit visit = new Visit();
        visit.setDate(LocalDateTime.now());
        visit.setAssurance(false);
        visit.setPrice(100.0);
        visit.setReceipts(Collections.singletonList(receipt));
        visit.setDoctor(getValidDoctor(visit));
        visit.setPatient(getValidPatient(visit));

        return visit;
    }
}
