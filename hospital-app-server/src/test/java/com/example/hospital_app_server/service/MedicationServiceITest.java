package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.repository.MedicationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidMedication;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MedicationServiceITest {
    private final MedicationRepository medicationRepository;
    private final int id = 10001;

    @Autowired
    public MedicationServiceITest(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Test
    void when_allMedicationsAreRetrieved_then_allMedicationsAreReturned() {
        List<Medication> medications = medicationRepository.findAll();
        assertThat(medications.size()).isGreaterThan(0);
    }

    @Test
    void when_medicationIsRetrievedById_then_medicationIsReturned() {
        Optional<Medication> optionalMedication = medicationRepository.findById(id);
        assertTrue(optionalMedication.isPresent());

        Medication foundMedication = optionalMedication.get();
        assertThat(foundMedication.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void when_medicationIsCreated_then_medicationIsSaved() {
        Medication createdMedication = medicationRepository.save(getValidMedication(null));
        assertThat(createdMedication.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void when_medicationIsUpdated_then_medicationIsSaved() {
        Optional<Medication> optionalMedication = medicationRepository.findById(id);
        assertTrue(optionalMedication.isPresent());

        Medication foundMedication = optionalMedication.get();
        assertThat(foundMedication.getId()).isEqualTo(id);

        foundMedication.setPrice(49.99);
        Medication updatedMedication = medicationRepository.save(foundMedication);

        assertThat(updatedMedication.getPrice()).isEqualTo(49.99);
    }

    @Test
    @Transactional
    void when_medicationIsDeletedById_then_medicationIsDeleted() {
        medicationRepository.deleteById(id);
        Optional<Medication> optionalMedication = medicationRepository.findById(id);
        assertTrue(optionalMedication.isEmpty());
    }
}
