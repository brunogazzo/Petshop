package br.com.tt.petshop.exception.dto;

import java.time.LocalDateTime;

public class ApiErrorDto {

    private String key;
    private String message;
    private LocalDateTime time;

    public ApiErrorDto(String key, String message) {
        this.key = key;
        this.message = message;
        this.time = LocalDateTime.now();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
