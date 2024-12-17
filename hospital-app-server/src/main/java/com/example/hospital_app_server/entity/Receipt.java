package com.example.hospital_app_server.entity;

import com.example.hospital_app_server.validation.Validatable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt implements Validatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Transient
    @Column(name = "total_price")
    private double totalPrice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "receipt_medication",
            joinColumns = @JoinColumn(name = "receipt_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    @Size(min = 1, max = 12, message = "{messages.validation.size}")
    private Set<Medication> medications = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "visit_id", nullable = false)
    @NotNull(message = "{messages.validation.notnull}")
    private Visit visit;

    public Receipt() {
    }

    @PrePersist
    protected void onCreate() {
        this.expirationDate = LocalDate.now().atStartOfDay().plusDays(14);
        this.totalPrice = calculateTotalPrice();
    }

    @PostLoad
    private void onLoad() {
        this.totalPrice = calculateTotalPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(@NotNull(message = "{messages.validation.notnull}") Visit visit) {
        this.visit = visit;
    }

    public double calculateTotalPrice() {
        return medications.stream().map(Medication::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", expirationDate=" + expirationDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
