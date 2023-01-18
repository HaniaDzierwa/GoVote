package com.aleklew.ballot.modules.profiles.models;

import java.util.Date;

public class LoginUserResponse {
    private String accessToken;
    private Date expirationDate;

    public LoginUserResponse(String accessToken, Date expirationDate) {
        this.accessToken = accessToken;
        this.expirationDate = expirationDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
