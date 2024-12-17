package com.example.hospital_app_server.entity;

import com.example.hospital_app_server.validation.DecimalRange;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "{messages.validation.required}")
    @Column(name = "name")
    private String name;

    @DecimalRange(max = 100_000.0, message = "{messages.validation.range}")
    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "{messages.validation.positive-or-zero}")
    @Column(name = "company_id")
    private int companyId;

    @JsonIgnore
    @ManyToMany(mappedBy = "medications", cascade = CascadeType.ALL)
    private final Set<Receipt> receipts = new HashSet<>();

    public Medication() {
    }

    public Medication(String name, int companyId, double price, String description) {
        this.name = name;
        this.companyId = companyId;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "{messages.validation.required}") String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Set<Receipt> getReceipts() {
        return receipts;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", receipts=" + receipts +
                '}';
    }
}
