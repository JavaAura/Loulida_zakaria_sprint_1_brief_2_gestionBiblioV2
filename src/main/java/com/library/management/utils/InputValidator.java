package com.library.management.utils;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");

    public static boolean isValidId(String id) {
        return ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidEmail(String email) {
        // Example validation for email
        return email != null && email.contains("@");
    }

    // Add more validation methods as needed
}

