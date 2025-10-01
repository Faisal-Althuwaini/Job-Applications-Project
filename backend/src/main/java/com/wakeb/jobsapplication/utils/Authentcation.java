package com.wakeb.jobsapplication.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Authentcation {
    public static String getAuthenticatedEmail() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
