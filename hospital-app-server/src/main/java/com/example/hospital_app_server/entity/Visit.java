package com.example.hospital_app_server.entity;

import com.example.hospital_app_server.validation.DecimalRange;
import com.example.hospital_app_server.validation.Validatable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "visit")
public class Visit implements Validatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "{messages.validation.notnull}")
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "assurance")
    private boolean assurance;

    @DecimalRange(max = 100_000.0, message = "{messages.validation.range}")
    @Column(name = "price")
    private double price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "visit", cascade = CascadeType.ALL)
    @Size(max = 12, message = "{messages.validation.size}")
    @Valid
    private List<Receipt> receipts;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "{messages.validation.notnull}")
    private Patient patient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "{messages.validation.notnull}")
    private Doctor doctor;

    public Visit() {
    }

    public Visit(LocalDateTime date, boolean assurance, double price) {
        this.date = date;
        this.assurance = assurance;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isAssurance() {
        return assurance;
    }

    public void setAssurance(boolean assurance) {
        this.assurance = assurance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date=" + date +
                ", assurance=" + assurance +
                ", price=" + price +
                '}';
    }
}
