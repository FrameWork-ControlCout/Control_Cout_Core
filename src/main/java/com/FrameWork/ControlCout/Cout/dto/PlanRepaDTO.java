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

    private TechCardDTO techCardDTO;

    private Integer codeTechCard;

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

    public TechCardDTO getTechCardDTO() {
        return techCardDTO;
    }

    public void setTechCardDTO(TechCardDTO techCardDTO) {
        this.techCardDTO = techCardDTO;
    }

    public Integer getCodeTechCard() {
        return codeTechCard;
    }

    public void setCodeTechCard(Integer codeTechCard) {
        this.codeTechCard = codeTechCard;
    }

}
