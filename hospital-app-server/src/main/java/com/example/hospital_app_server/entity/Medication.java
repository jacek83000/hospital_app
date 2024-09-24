package com.example.hospital_app_server.entity;

import com.example.hospital_app_server.validation.DecimalRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


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

    @NotBlank(message = "{messages.validation.required}")
    @Column(name = "company_name")
    private String companyName;

    @DecimalRange(min = 0.0, max = 100_000.0, message = "{messages.validation.range}")
    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    public Medication() {
    }

    public Medication(String name, String companyName, double price, String description) {
        this.name = name;
        this.companyName = companyName;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
