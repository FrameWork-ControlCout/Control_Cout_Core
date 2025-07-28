/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
 
public class TraceSocieteDTO {

     private Integer code;

    private SocieteDTO societeDTO;

     private Integer codeSociete;

     private Date dateUpdate;

    private String userCreate;

    private Date dateCreate;

    private Integer nbrePersonOld;

     private Integer nbrePersonNew;

    public TraceSocieteDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
 

    public Integer getCodeSociete() {
        return codeSociete;
    }

    public void setCodeSociete(Integer codeSociete) {
        this.codeSociete = codeSociete;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
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

    public Integer getNbrePersonOld() {
        return nbrePersonOld;
    }

    public void setNbrePersonOld(Integer nbrePersonOld) {
        this.nbrePersonOld = nbrePersonOld;
    }

    public Integer getNbrePersonNew() {
        return nbrePersonNew;
    }

    public void setNbrePersonNew(Integer nbrePersonNew) {
        this.nbrePersonNew = nbrePersonNew;
    }

    public SocieteDTO getSocieteDTO() {
        return societeDTO;
    }

    public void setSocieteDTO(SocieteDTO societeDTO) {
        this.societeDTO = societeDTO;
    }
    
    
    
}
