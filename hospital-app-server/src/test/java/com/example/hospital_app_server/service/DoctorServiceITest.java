package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Doctor;
import com.example.hospital_app_server.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidDoctor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DoctorServiceITest {
    private final DoctorRepository doctorRepository;
    private final int id = 10001;

    @Autowired
    public DoctorServiceITest(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Test
    void when_allDoctorsAreRetrieved_then_allDoctorsAreReturned() {
        List<Doctor> doctors = doctorRepository.findAll();
        assertThat(doctors.size()).isGreaterThan(0);
    }

    @Test
    void when_doctorIsRetrievedById_then_doctorIsReturned() {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        assertTrue(optionalDoctor.isPresent());

        Doctor foundDoctor = optionalDoctor.get();
        assertThat(foundDoctor.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void when_doctorIsCreated_then_doctorIsSaved() {
        Doctor createdDoctor = doctorRepository.save(getValidDoctor(null));
        assertThat(createdDoctor.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void when_doctorIsUpdated_then_doctorIsSaved() {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        assertTrue(optionalDoctor.isPresent());

        Doctor foundDoctor = optionalDoctor.get();
        assertThat(foundDoctor.getId()).isEqualTo(id);

        foundDoctor.setYearsOfExperience(10);
        Doctor updatedDoctor = doctorRepository.save(foundDoctor);

        assertThat(updatedDoctor.getYearsOfExperience()).isEqualTo(10);
    }

    @Test
    @Transactional
    void when_doctorIsDeletedById_then_doctorIsDeleted() {
        doctorRepository.deleteById(id);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        assertTrue(optionalDoctor.isEmpty());
    }
}

