package com.samuel.passwordgen.password_generator.service;

public interface PasswordServiceImpl {
    public String generatePassword(int length, boolean useUppercase, boolean useNumbers, boolean useSymbols);
}
