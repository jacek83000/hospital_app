package com.example.hospital_app_server.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private Map<String, String> messages;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, Map<String, String> messages) {
        this.timestamp = timestamp;
        this.messages = messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
}
