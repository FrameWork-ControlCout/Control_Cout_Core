/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class TechCardDTO {

    private Integer code;

    private String codeSaisie;   
    private String designationAr;


    private String userCreate;

    private Date dateCreate;

    private BigDecimal prixTotal;
    
    private List<DetailsTechCardDTO> detailsTechCardsCardDTOs;

      private boolean actif;
          private BigDecimal coutUnitaire;
      
      
    public TechCardDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeSaisie() {
        return codeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        this.codeSaisie = codeSaisie;
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

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public List<DetailsTechCardDTO> getDetailsTechCardsCardDTOs() {
        return detailsTechCardsCardDTOs;
    }

    public void setDetailsTechCardsCardDTOs(List<DetailsTechCardDTO> detailsTechCardsCardDTOs) {
        this.detailsTechCardsCardDTOs = detailsTechCardsCardDTOs;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public BigDecimal getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(BigDecimal coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

  

}
