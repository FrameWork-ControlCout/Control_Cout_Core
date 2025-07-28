/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.dto;

import com.FrameWork.ControlCout.Achat.dto.*;
import java.sql.Blob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
public class SocEditionDTO {

    @NotNull
    private Integer code;

    private Blob logo;

    @NotNull
    @Size(
            min = 1,
            max = 100
    )
    private String nomSociete;

    @Size(
            max = 100
    )
    private String nomSocieteAr;

    public SocEditionDTO(Integer code, Blob logo, String nomSociete, String nomSocieteAr) {
        this.code = code;
        this.logo = logo;
        this.nomSociete = nomSociete;
        this.nomSocieteAr = nomSocieteAr;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public void setNomSocieteAr(String nomSocieteAr) {
        this.nomSocieteAr = nomSocieteAr;
    }

    public Integer getCode() {
        return code;
    }

    public Blob getLogo() {
        return logo;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public String getNomSocieteAr() {
        return nomSocieteAr;
    }

   

   
 
   
    
 

   

    public Blob getLogoSociete() {
        return logo;
    }

}
