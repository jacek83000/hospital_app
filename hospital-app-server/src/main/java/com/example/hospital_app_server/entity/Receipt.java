package com.example.hospital_app_server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

import static com.example.hospital_app_server.utils.date.DateUtils.getDateXDaysLaterFromNow;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @DecimalMin(value = "0.0")
    @Column(name = "total_price")
    private double totalPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<Medication> medications;

    @OneToOne(mappedBy = "receipt", cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id")
    private Visit visit;

    public Receipt() {
    }

    public Receipt(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @PrePersist
    protected void onCreate() {
        this.expirationDate = getDateXDaysLaterFromNow(14);
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
