package com.aleklew.ballot.modules.profiles.models;

public class RequestPasswordRecoveryRequest {
    private String email;

    public RequestPasswordRecoveryRequest() {
    }

    public RequestPasswordRecoveryRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
