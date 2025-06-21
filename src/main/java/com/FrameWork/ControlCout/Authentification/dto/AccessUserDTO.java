/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class AccessUserDTO {

    private Long id;

    private String fullName;

    private String userName;

    private String email;

    private String password;
        private String passwordDecry;

    private Date createdAt;

    private Date updatedAt;

    private byte[] signature;
    private String sig;
    
        private byte[] imageProfil;
            private String imgProfil;

        
      private Integer codeMedecin;
      
        private String permissionDMI;

    public AccessUserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getPasswordDecry() {
        return passwordDecry;
    }

    public void setPasswordDecry(String passwordDecry) {
        this.passwordDecry = passwordDecry;
    }

    public Integer getCodeMedecin() {
        return codeMedecin;
    }

    public void setCodeMedecin(Integer codeMedecin) {
        this.codeMedecin = codeMedecin;
    }

    public String getPermissionDMI() {
        return permissionDMI;
    }

    public void setPermissionDMI(String permissionDMI) {
        this.permissionDMI = permissionDMI;
    }

    public byte[] getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(byte[] imageProfil) {
        this.imageProfil = imageProfil;
    }

    public String getImgProfil() {
        return imgProfil;
    }

    public void setImgProfil(String imgProfil) {
        this.imgProfil = imgProfil;
    }

    
    
    
}
