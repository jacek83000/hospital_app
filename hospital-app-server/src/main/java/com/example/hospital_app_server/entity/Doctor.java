package com.example.hospital_app_server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor extends Person {

    @NotBlank(message = "{messages.validation.required}")
    @Column(name = "specialization")
    private String specialization;

    @Range(min = 0, max = 100, message = "{messages.validation.range}")
    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Visit> visits;

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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
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
