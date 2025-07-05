/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.FrameWork.ControlCout.Achat.domaine.*;
import com.FrameWork.ControlCout.Parametrage.domaine.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Plan_Repa", schema = "cout")
@Audited
@AuditTable("Plan_Repa_AUD")
public class PlanRepa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Plan", nullable = false, columnDefinition = "datetime  ")
    private Date datePlan;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Type_Repa", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeRepa typeRepa;

    @NotNull
    @Column(name = "Code_Type_Repa", updatable = false, insertable = false, nullable = false)
    private Integer codeTypeRepa;
    
    
 
    
    

    @JoinColumn(name = "Code_Repa", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TechCard techCard;

    @Column(name = "Code_Repa", updatable = false, insertable = false, nullable = false)
    private Integer codeTechCard;

    public PlanRepa() {
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

    public TypeRepa getTypeRepa() {
        return typeRepa;
    }

    public void setTypeRepa(TypeRepa typeRepa) {
        this.typeRepa = typeRepa;
    }

    public Integer getCodeTypeRepa() {
        return codeTypeRepa;
    }

    public void setCodeTypeRepa(Integer codeTypeRepa) {
        this.codeTypeRepa = codeTypeRepa;
    }

    public TechCard getTechCard() {
        return techCard;
    }

    public void setTechCard(TechCard techCard) {
        this.techCard = techCard;
    }

    public Integer getCodeTechCard() {
        return codeTechCard;
    }

    public void setCodeTechCard(Integer codeTechCard) {
        this.codeTechCard = codeTechCard;
    }

}
