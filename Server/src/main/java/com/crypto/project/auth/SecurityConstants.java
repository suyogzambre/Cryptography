package com.crypto.project.auth;


public class SecurityConstants {
    public static final String SECRET = "BulletCareAshishKey";
    public static final long EXPIRATION_TIME = 86_400_000; // 10 days
    //public static final long EXPIRATION_TIME = 60_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}