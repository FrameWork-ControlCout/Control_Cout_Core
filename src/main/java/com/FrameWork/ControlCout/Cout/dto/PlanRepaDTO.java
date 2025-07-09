/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Parametrage.dto.TypeRepaDTO;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class PlanRepaDTO {

    private Integer code;

    private Date datePlan;

    private String userCreate;

    private Date dateCreate;
    private TypeRepaDTO typeRepaDTO;

    private Integer codeTypeRepa;

    private FicheTechDTO techCardDTO;

    private Integer codeFicheTechnique;

    public PlanRepaDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDatePlan() {
        return datePlan;
    }

    public void setDatePlan(Date datePlan) {
        this.datePlan = datePlan;
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

    public TypeRepaDTO getTypeRepaDTO() {
        return typeRepaDTO;
    }

    public void setTypeRepaDTO(TypeRepaDTO typeRepaDTO) {
        this.typeRepaDTO = typeRepaDTO;
    }

    public Integer getCodeTypeRepa() {
        return codeTypeRepa;
    }

    public void setCodeTypeRepa(Integer codeTypeRepa) {
        this.codeTypeRepa = codeTypeRepa;
    }

    public FicheTechDTO getFicheTechniqueDTO() {
        return techCardDTO;
    }

    public void setFicheTechniqueDTO(FicheTechDTO techCardDTO) {
        this.techCardDTO = techCardDTO;
    }

    public Integer getCodeFicheTechnique() {
        return codeFicheTechnique;
    }

    public void setCodeFicheTechnique(Integer codeFicheTechnique) {
        this.codeFicheTechnique = codeFicheTechnique;
    }

}
