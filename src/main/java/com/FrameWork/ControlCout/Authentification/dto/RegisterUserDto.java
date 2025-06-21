/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.dto;

/**
 *
 * @author Administrator
 */
public class RegisterUserDto {
     private String email;
    
    private String password;
    
    private String fullName;  
    
    private String userName;
        private String passwordDecry;


    public RegisterUserDto() {
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordDecry() {
        return passwordDecry;
    }

    public void setPasswordDecry(String passwordDecry) {
        this.passwordDecry = passwordDecry;
    }
    
    
}
