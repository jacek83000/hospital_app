package com.example.hospital_app_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Person {
    @Range(min = 0, max = 200, message = "{messages.validation.range}")
    @Column(name = "age")
    private int age;

    @NotNull(message = "{messages.validation.notnull}")
    @Pattern(regexp = "^(male|female)$", message = "{messages.validation.sex}")
    @Column(name = "sex")
    private String sex;

    @NotBlank(message = "{messages.validation.required}")
    @Column(name = "address")
    private String address;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient", cascade = CascadeType.ALL)
    @Valid
    private List<Visit> visits;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String contactNumber, String email, int age, String sex, String address) {
        super(firstName, lastName, contactNumber, email);
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", address=" + address +
                '}';
    }
}
