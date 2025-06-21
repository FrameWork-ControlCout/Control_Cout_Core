/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.dto;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class LoginResponse {

    private String token;

    private long expiresIn;

    private Instant expiration;
    
        private String refreshToken;  
        
        private String userName;


        private Integer codeMedecin;

   
  private String permissionDMI;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public LoginResponse setExpiration(Instant expiration) {
        this.expiration = expiration;
           return this;
    }

  

    public String getRefreshToken() {
        return refreshToken;
    }

    public LoginResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
             return this;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public LoginResponse setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
            return this;
    }

    public String getPermissionDMI() {
        return permissionDMI;
    }

    public LoginResponse setPermissionDMI(String permissionDMI) {
        this.permissionDMI = permissionDMI;
         return this;
    }

    public String getUserName() {
        return userName;
    }

    public LoginResponse setUserName(String userName) {
        this.userName = userName;
           return this;
    }

    

    
    

   
    
    
 

  

    
     

}
