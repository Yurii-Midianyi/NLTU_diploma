package com.nltu.app.diplomaproject.dto;

public class AuthenticationResponse {
    private String token;
    AuthenticationResponse(){}

    public AuthenticationResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
