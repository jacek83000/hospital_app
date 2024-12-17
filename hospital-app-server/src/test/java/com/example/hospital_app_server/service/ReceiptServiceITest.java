package com.example.hospital_app_server.service;

import com.example.hospital_app_server.entity.Medication;
import com.example.hospital_app_server.entity.Receipt;
import com.example.hospital_app_server.entity.Visit;
import com.example.hospital_app_server.repository.MedicationRepository;
import com.example.hospital_app_server.repository.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.hospital_app_server.utils.TestEntityFactory.getValidVisit;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReceiptServiceITest {
    private final ReceiptRepository receiptRepository;
    private final MedicationRepository medicationRepository;
    private final int id = 10001;

    @Autowired
    public ReceiptServiceITest(ReceiptRepository receiptRepository, MedicationRepository medicationRepository) {
        this.receiptRepository = receiptRepository;
        this.medicationRepository = medicationRepository;
    }

    @Test
    void when_allReceiptsAreRetrieved_then_allReceiptsAreReturned() {
        List<Receipt> receipts = receiptRepository.findAll();
        assertThat(receipts.size()).isGreaterThan(0);
    }

    @Test
    void when_receiptIsRetrievedById_then_receiptIsReturned() {
        Optional<Receipt> optionalReceipt = receiptRepository.findById(id);
        assertTrue(optionalReceipt.isPresent());

        Receipt foundReceipt = optionalReceipt.get();
        assertThat(foundReceipt.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    void when_receiptIsCreated_then_receiptIsSaved() {
        Receipt receipt = new Receipt();

        Visit visit = getValidVisit(receipt);
        receipt.setVisit(visit);

        Optional<Medication> optionalMedication = medicationRepository.findById(10001);
        assertTrue(optionalMedication.isPresent());
        Medication medication = optionalMedication.get();

        medication.getReceipts().add(receipt);
        receipt.getMedications().add(medication);

        Receipt createdReceipt = receiptRepository.save(receipt);
        assertThat(createdReceipt.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    void when_receiptIsUpdated_then_receiptIsSaved() {
        Optional<Receipt> optionalReceipt = receiptRepository.findById(id);
        assertTrue(optionalReceipt.isPresent());
        Receipt foundReceipt = optionalReceipt.get();
        assertThat(foundReceipt.getId()).isEqualTo(id);

        LocalDateTime newDate = LocalDateTime.now().plusDays(30);
        foundReceipt.setExpirationDate(newDate);

        Receipt updatedReceipt = receiptRepository.save(foundReceipt);

        assertThat(updatedReceipt.getExpirationDate()).isEqualTo(newDate);
    }

    @Test
    @Transactional
    void when_receiptIsDeletedById_then_receiptIsDeleted() {
        receiptRepository.deleteById(id);
        Optional<Receipt> optionalReceipt = receiptRepository.findById(id);
        assertTrue(optionalReceipt.isEmpty());
    }
}
