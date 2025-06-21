package com.FrameWork.ControlCout.Authentification.web.Request;

//import jakarta.validation.constraints.NotBlank;
 
 


public class TokenRefreshRequest {
    private String refreshToken;
    private Long userId;

    public TokenRefreshRequest() {
        // Default constructor
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}