package com.example.hospital_app_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor extends Person {

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String contactNumber, String email, String specialization, int yearsOfExperience) {
        super(firstName, lastName, contactNumber, email);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                "specialization='" + specialization + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                "} ";
    }
}
