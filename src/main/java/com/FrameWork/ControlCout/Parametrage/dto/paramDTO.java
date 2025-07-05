/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;

import jakarta.persistence.Column;

/**
 *
 * @author Administrator
 */
public class paramDTO {

    private Integer code;   
    private String codeParam;

    private Boolean Visible;
    private String description;

    private String valeur;

    public paramDTO() {
    }

 
 
 

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Boolean getVisible() {
        return Visible;
    }

    public void setVisible(Boolean Visible) {
        this.Visible = Visible;
    }
    
    
    

}
