package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Patient;
import com.example.hospital_app_server.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidPatient;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientServiceITest {
    private final PatientRepository patientRepository;
    private final int id = 10003;

    @Autowired
    public PatientServiceITest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Test
    void when_allPatientsAreRetrieved_then_allPatientsAreReturned() {
        List<Patient> patients = patientRepository.findAll();
        assertThat(patients.size()).isGreaterThan(0);
    }

    @Test
    void when_patientIsRetrievedById_then_patientIsReturned() {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        assertTrue(optionalPatient.isPresent());

        Patient foundPatient = optionalPatient.get();
        assertThat(foundPatient.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void when_patientIsCreated_then_patientIsSaved() {
        Patient createdPatient = patientRepository.save(getValidPatient(null));
        assertThat(createdPatient.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void when_patientIsUpdated_then_patientIsSaved() {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        assertTrue(optionalPatient.isPresent());

        Patient foundPatient = optionalPatient.get();
        assertThat(foundPatient.getId()).isEqualTo(id);

        foundPatient.setAddress("modifiedString");
        Patient updatedPatient = patientRepository.save(foundPatient);

        assertThat(updatedPatient.getAddress()).isEqualTo("modifiedString");
    }

    @Test
    @Transactional
    void when_patientIsDeletedById_then_patientIsDeleted() {
        patientRepository.deleteById(id);
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        assertTrue(optionalPatient.isEmpty());
    }
}
