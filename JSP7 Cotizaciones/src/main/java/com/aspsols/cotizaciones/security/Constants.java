package com.aspsols.cotizaciones.security;

public class Constants {

	// Spring Security
	public static final String LOGIN_URL = "/auth";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final String ISSUER_INFO = "https://www.aspsols.com/";
	public static final String SECRET_KEY = "S3CUR1TY_JSP7";
	public static final long TOKEN_EXPIRATION_TIME = 43_200_000; // 1/2 day (Milliseconds)

}
