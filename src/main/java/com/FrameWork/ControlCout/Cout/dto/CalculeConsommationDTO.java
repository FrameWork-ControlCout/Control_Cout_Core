/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
 
public class CalculeConsommationDTO {

     private Integer code;

    
    private Date dateDebut;
       
           
    private Date dateFin;
       
       
    private Integer nbreJours;
      
     private List<DetailsCalculeConsommationDTO> detailsCalculeConsommationsCardDTOs;
    private BigDecimal prixTotal;
    
    private boolean actif;
           private String userCreate;

    private Date dateCreate;
        
        
    public CalculeConsommationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNbreJours() {
        return nbreJours;
    }

    public void setNbreJours(Integer nbreJours) {
        this.nbreJours = nbreJours;
    }

   
    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public List<DetailsCalculeConsommationDTO> getDetailsCalculeConsommationsCardDTOs() {
        return detailsCalculeConsommationsCardDTOs;
    }

    public void setDetailsCalculeConsommationsCardDTOs(List<DetailsCalculeConsommationDTO> detailsCalculeConsommationsCardDTOs) {
        this.detailsCalculeConsommationsCardDTOs = detailsCalculeConsommationsCardDTOs;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
 
    
    
}
