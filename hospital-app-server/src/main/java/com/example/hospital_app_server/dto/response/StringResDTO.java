package com.example.hospital_app_server.dto.response;

public class StringResDTO {
    private String message;

    public StringResDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
