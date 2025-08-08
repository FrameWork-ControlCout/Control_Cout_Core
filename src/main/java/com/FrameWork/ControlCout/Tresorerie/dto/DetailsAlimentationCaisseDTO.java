/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;

import com.FrameWork.ControlCout.Tresorerie.dto.TypeRecetteDTO; 
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsAlimentationCaisseDTO {

 

    private int codeAlimentationCaisse;
    private String codeSaisieAlimentationCaisse;

    @NotNull
    private TypeRecetteDTO typeRecetteDTO;
    private String codeSaisieTypeRecette;  
    private Integer codeTypeRecette; 
    private String designationArTypeRecette;
    private String designationLtTypeRecette;

    private String usercreate;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    private BigDecimal montant;
        private BigDecimal montantDevise;
        

    public DetailsAlimentationCaisseDTO() {
    }

    public int getCodeAlimentationCaisse() {
        return codeAlimentationCaisse;
    }

    public void setCodeAlimentationCaisse(int codeAlimentationCaisse) {
        this.codeAlimentationCaisse = codeAlimentationCaisse;
    }

    public String getCodeSaisieAlimentationCaisse() {
        return codeSaisieAlimentationCaisse;
    }

    public void setCodeSaisieAlimentationCaisse(String codeSaisieAlimentationCaisse) {
        this.codeSaisieAlimentationCaisse = codeSaisieAlimentationCaisse;
    }

    public TypeRecetteDTO getTypeRecetteDTO() {
        return typeRecetteDTO;
    }

    public void setTypeRecetteDTO(TypeRecetteDTO typeRecetteDTO) {
        this.typeRecetteDTO = typeRecetteDTO;
    }

    public Integer getCodeTypeRecette() {
        return codeTypeRecette;
    }

    public void setCodeTypeRecette(Integer codeTypeRecette) {
        this.codeTypeRecette = codeTypeRecette;
    }

    

  
    public String getCodeSaisieTypeRecette() {
        return codeSaisieTypeRecette;
    }

    public void setCodeSaisieTypeRecette(String codeSaisieTypeRecette) {
        this.codeSaisieTypeRecette = codeSaisieTypeRecette;
    }

    public String getDesignationArTypeRecette() {
        return designationArTypeRecette;
    }

    public void setDesignationArTypeRecette(String designationArTypeRecette) {
        this.designationArTypeRecette = designationArTypeRecette;
    }

    public String getDesignationLtTypeRecette() {
        return designationLtTypeRecette;
    }

    public void setDesignationLtTypeRecette(String designationLtTypeRecette) {
        this.designationLtTypeRecette = designationLtTypeRecette;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantDevise() {
        return montantDevise;
    }

    public void setMontantDevise(BigDecimal montantDevise) {
        this.montantDevise = montantDevise;
    }
 

}
