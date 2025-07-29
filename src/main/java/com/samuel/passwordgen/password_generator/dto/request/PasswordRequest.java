package com.samuel.passwordgen.password_generator.dto.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private int length;
    private boolean useUppercase;
    private boolean useNumbers;
    private boolean useSymbols;

}