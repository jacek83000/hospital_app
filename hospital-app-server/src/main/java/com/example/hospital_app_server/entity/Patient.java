package com.example.hospital_app_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient extends Person {

    @Column(name = "age")
    private int age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private int address;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String contactNumber, String email, int age, String sex, int address) {
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

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
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
