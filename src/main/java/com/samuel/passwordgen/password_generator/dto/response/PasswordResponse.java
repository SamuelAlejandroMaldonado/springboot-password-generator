package com.samuel.passwordgen.password_generator.dto.response;

import lombok.Data;

@Data
public class PasswordResponse {
    private String password;
    public PasswordResponse(String password) {
        this.password = password;
    }
}