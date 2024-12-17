package com.example.hospital_app_server.dto.response;

public class MedicationGetResDTO {
    private int id;
    private String name;
    private int companyId;
    private String companyName;
    private String companyWebsite;
    private String companyContactNumber;
    private double price;
    private String description;


    public MedicationGetResDTO() {
    }

    public MedicationGetResDTO(int id, String name, int companyId, String companyName, String companyWebsite, String companyContactNumber, double price, String description) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyWebsite = companyWebsite;
        this.companyContactNumber = companyContactNumber;
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyContactNumber() {
        return companyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        this.companyContactNumber = companyContactNumber;
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

    @Override
    public String toString() {
        return "MedicationGetResDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", companyContactNumber='" + companyContactNumber + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
