package com.samuel.passwordgen.password_generator.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordService implements PasswordServiceImpl{
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private SecureRandom random = new SecureRandom();
    @Override
    public String generatePassword(int length, boolean useUppercase, boolean useNumbers, boolean useSymbols) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        StringBuilder characterPool = new StringBuilder(LOWERCASE);
        if (useUppercase) {
            characterPool.append(UPPERCASE);
        }
        if (useNumbers) {
            characterPool.append(NUMBERS);
        }
        if (useSymbols) {
            characterPool.append(SYMBOLS);
        }
        if (characterPool.isEmpty()) {
            throw new IllegalArgumentException("At least one character set must be selected");
        }
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterPool.length());
            password.append(characterPool.charAt(index));
        }

        return password.toString();
    }
}