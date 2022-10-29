package com.mafuentes.springboot.apirest.springbootapirest.constants;

public class SwaggerConstants {
    
    public static final String SECURITY_REFERENCE = "Token Access";
    public static final String AUTHORIZATION_DESCRIPTION = "Full api access";
    public static final String AUTHORIZATION_SCOPE = "Unlimited";
    public static final String SECURE_PATH = "/*/.*";
    public static final String API_TITLE = "Disney API";
    public static final String API_DESCRIPTION = "Api with information about characters,movies and series of Disney<br>" +
            "In order to execute requests you first need to register at */auth/register* and then to login at */auth/login* to get a token," +
            "which you need to execute requests, to use it you need the enter the following 'Bearer *Your token*' in the authorize field.<br>" +
            "The token will expire after 10 minutes so you also get a refresh token when you login, and when your access token expires use your refresh token to get a new one"+
            " at *auth/token/refresh*";
    public static final String API_VERSION = "1.0";
    public static final String LICENSE = "";
    public static final String LICENSE_URL = "";
    public static final String CONTACT_NAME = "Marcos";
    public static final String CONTACT_URL = "";
    public static final String CONTACT_EMAIL = "marcos691@gmail.com";
    public static final String TERM_OF_SERVICE = "";

}