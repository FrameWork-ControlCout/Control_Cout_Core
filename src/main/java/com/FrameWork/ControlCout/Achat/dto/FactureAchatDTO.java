/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.CostProfitCentre;
import com.FrameWork.ControlCout.Parametrage.dto.CostProfitCentreDTO;
import com.FrameWork.ControlCout.Parametrage.dto.EtatFactureDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
 
public class FactureAchatDTO {

     private Integer code;

     private String codeSaisie; 
 
     private String userCreate;

     private Date dateCreate;

     private EtatFactureDTO etatFactureDTO;

     private Integer codeEtatFacture;
    
    
   
     private String userApprove;

    private FournisseurDTO fournisseurDTO;

     private Integer codeFournisseur;

     private BigDecimal montantHt;

    private BigDecimal montantTva;

     private BigDecimal montantTTC;

    
     private List<DetailsFactureAchatDTO> detailsFactureAchatsAchatDTOs; 
     
        private CostProfitCentreDTO costProfitCentreDTO;
 
    private Integer codeCodeProfitCentre;
    
    private String type;
     
    public FactureAchatDTO() {
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

   
    public Integer getCodeEtatFacture() {
        return codeEtatFacture;
    }

    public void setCodeEtatFacture(Integer codeEtatFacture) {
        this.codeEtatFacture = codeEtatFacture;
    }

    public String getUserApprove() {
        return userApprove;
    }

    public void setUserApprove(String userApprove) {
        this.userApprove = userApprove;
    }

  
    public Integer getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(Integer codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public BigDecimal getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(BigDecimal montantHt) {
        this.montantHt = montantHt;
    }

    public BigDecimal getMontantTva() {
        return montantTva;
    }

    public void setMontantTva(BigDecimal montantTva) {
        this.montantTva = montantTva;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    public EtatFactureDTO getEtatFactureDTO() {
        return etatFactureDTO;
    }

    public void setEtatFactureDTO(EtatFactureDTO etatFactureDTO) {
        this.etatFactureDTO = etatFactureDTO;
    }

    public FournisseurDTO getFournisseurDTO() {
        return fournisseurDTO;
    }

    public void setFournisseurDTO(FournisseurDTO fournisseurDTO) {
        this.fournisseurDTO = fournisseurDTO;
    }

    public List<DetailsFactureAchatDTO> getDetailsFactureAchatsAchatDTOs() {
        return detailsFactureAchatsAchatDTOs;
    }

    public void setDetailsFactureAchatsAchatDTOs(List<DetailsFactureAchatDTO> detailsFactureAchatsAchatDTOs) {
        this.detailsFactureAchatsAchatDTOs = detailsFactureAchatsAchatDTOs;
    }

    public CostProfitCentreDTO getCostProfitCentreDTO() {
        return costProfitCentreDTO;
    }

    public void setCostProfitCentreDTO(CostProfitCentreDTO costProfitCentreDTO) {
        this.costProfitCentreDTO = costProfitCentreDTO;
    }

    public Integer getCodeCodeProfitCentre() {
        return codeCodeProfitCentre;
    }

    public void setCodeCodeProfitCentre(Integer codeCodeProfitCentre) {
        this.codeCodeProfitCentre = codeCodeProfitCentre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  
    
    
    
    

}
