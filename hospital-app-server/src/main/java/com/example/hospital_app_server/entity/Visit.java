package com.example.hospital_app_server.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "assurance")
    private boolean assurance;

    @Column(name = "price")
    private double price;

    public Visit() {
    }

    public Visit(Date date, boolean assurance, double price) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
