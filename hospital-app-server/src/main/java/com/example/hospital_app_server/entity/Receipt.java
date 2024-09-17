package com.example.hospital_app_server.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<Medication> medications;

    @OneToOne(mappedBy = "receipt", cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id")
    private Visit visit;

    public Receipt() {
    }

    public Receipt(Date createdAt, Date expirationDate, double totalPrice) {
        this.createdAt = createdAt;
        this.expirationDate = expirationDate;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
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
