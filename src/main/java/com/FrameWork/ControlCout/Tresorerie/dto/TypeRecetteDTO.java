/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class TypeRecetteDTO {

    private Integer code;

    private String codeSaisie;

    private String codeSaisieTypeRecette;

    private String designationAr;

    private String designationArTypeRecette;

    private String designationLt;

    private String designationLtTypeRecette;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    public TypeRecetteDTO() {
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

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
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

    public void setCodeSaisieTypeRecette(String codeSaisieTypeRecette) {
        this.codeSaisieTypeRecette = codeSaisieTypeRecette;
    }

    public void setDesignationArTypeRecette(String designationArTypeRecette) {
        this.designationArTypeRecette = designationArTypeRecette;
    }

    public void setDesignationLtTypeRecette(String designationLtTypeRecette) {
        this.designationLtTypeRecette = designationLtTypeRecette;
    }

    public String getCodeSaisieTypeRecette() {
        return codeSaisieTypeRecette;
    }

    public String getDesignationArTypeRecette() {
        return designationArTypeRecette;
    }

    public String getDesignationLtTypeRecette() {
        return designationLtTypeRecette;
    }

}
