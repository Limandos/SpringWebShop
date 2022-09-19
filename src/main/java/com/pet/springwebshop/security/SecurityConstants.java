package com.pet.springwebshop.security;

import java.util.Arrays;
import java.util.List;

public class SecurityConstants {
    public static final String PUBLIC_URLs = "/public/**";
    public static final String ADMIN_URLs = "/admin/**";
    public static final String AUTH_URLs = "/auth/**";
    public static final String LOGIN_URL = "/auth/login";
    public static final String SECRET_CODE = "VerysecretSecretcode";
    public static final List ALLOWED_ORIGINS = Arrays.asList("http://localhost:");
    public static final List ALLOWED_METHODS = Arrays.asList("GET", "POST", "DELETE", "PUT");
    public static final List ALLOWED_HEADERS = Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
    public static final int EXPIRE_TIME = 900000;
}
