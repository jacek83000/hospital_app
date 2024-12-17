package com.example.hospital_app_server.dto.response;

public class CompanyGetResDTO {
    private String name;
    private String phone;
    private String website;

    public CompanyGetResDTO() {
    }

    public CompanyGetResDTO(String string) {
        this.name = string;
        this.phone = string;
        this.website = string;
    }

    public CompanyGetResDTO(String name, String phone, String website) {
        this.name = name;
        this.phone = phone;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "CompanyGetResDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
