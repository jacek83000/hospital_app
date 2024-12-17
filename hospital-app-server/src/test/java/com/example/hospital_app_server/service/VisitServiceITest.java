package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidVisit;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitServiceITest {
    private final VisitRepository visitRepository;
    private final int id = 10001;

    @Autowired
    public VisitServiceITest(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Test
    void when_allVisitsAreRetrieved_then_allVisitsAreReturned() {
        List<Visit> visits = visitRepository.findAll();
        assertThat(visits.size()).isGreaterThan(0);
    }

    @Test
    void when_visitIsRetrievedById_then_visitIsReturned() {
        Optional<Visit> optionalVisit = visitRepository.findById(id);
        assertTrue(optionalVisit.isPresent());

        Visit foundVisit = optionalVisit.get();
        assertThat(foundVisit.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void when_visitIsCreated_then_visitIsSaved() {
        Visit createdVisit = visitRepository.save(getValidVisit(null));
        assertThat(createdVisit.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void when_visitIsUpdated_then_visitIsSaved() {
        Optional<Visit> optionalVisit = visitRepository.findById(id);
        assertTrue(optionalVisit.isPresent());

        Visit foundVisit = optionalVisit.get();
        assertThat(foundVisit.getId()).isEqualTo(id);

        foundVisit.setPrice(78.78);
        Visit updatedVisit = visitRepository.save(foundVisit);

        assertThat(updatedVisit.getPrice()).isEqualTo(78.78);
    }

    @Test
    @Transactional
    void when_visitIsDeletedById_then_visitIsDeleted() {
        visitRepository.deleteById(id);
        Optional<Visit> optionalVisit = visitRepository.findById(id);
        assertTrue(optionalVisit.isEmpty());
    }
}
