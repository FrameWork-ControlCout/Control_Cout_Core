/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.CostProfitCentre;
import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement;
import com.FrameWork.ControlCout.Parametrage.dto.BanqueDTO;
import com.FrameWork.ControlCout.Parametrage.dto.CostProfitCentreDTO;
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import com.FrameWork.ControlCout.Parametrage.dto.EtatDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ModeReglementDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private EtatDTO etatFactureDTO;

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

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    private BanqueDTO banqueDTO;
    private Integer codeBanque;

    private String numPiece;

    private String numFactFrs;

    private BigDecimal montantFactFrs;

    @Basic(optional = false)
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateFactFrs;
    
    
       private DeviseDTO deviseDTO;

     private Integer codeDevise;

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

    public EtatDTO getEtatFactureDTO() {
        return etatFactureDTO;
    }

    public void setEtatFactureDTO(EtatDTO etatFactureDTO) {
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

    public ModeReglementDTO getModeReglementDTO() {
        return modeReglementDTO;
    }

    public void setModeReglementDTO(ModeReglementDTO modeReglementDTO) {
        this.modeReglementDTO = modeReglementDTO;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public String getNumFactFrs() {
        return numFactFrs;
    }

    public void setNumFactFrs(String numFactFrs) {
        this.numFactFrs = numFactFrs;
    }

    public BigDecimal getMontantFactFrs() {
        return montantFactFrs;
    }

    public void setMontantFactFrs(BigDecimal montantFactFrs) {
        this.montantFactFrs = montantFactFrs;
    }

    public LocalDate getDateFactFrs() {
        return dateFactFrs;
    }

    public void setDateFactFrs(LocalDate dateFactFrs) {
        this.dateFactFrs = dateFactFrs;
    }

    public BanqueDTO getBanqueDTO() {
        return banqueDTO;
    }

    public void setBanqueDTO(BanqueDTO banqueDTO) {
        this.banqueDTO = banqueDTO;
    }

    public DeviseDTO getDeviseDTO() {
        return deviseDTO;
    }

    public void setDeviseDTO(DeviseDTO deviseDTO) {
        this.deviseDTO = deviseDTO;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }
    
    

}
