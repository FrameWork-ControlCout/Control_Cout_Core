/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ComparFactureAchatDTO {

    private Integer code;

    private String codeSaisie;

    private String userCreate;

    private Date dateCreate;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    
        private FactureAchatDTO factureAchatDTO;

    private Integer codeFactureAchat;
    
    
    
    private BigDecimal montantTTC;

    private List<ComparDetailsFactureAchatDTO> detailsComparFactureAchatsAchatDTOs;

    private String numFactFrs;

    private BigDecimal montantFactFrs;

    private BigDecimal montantTTCGros;

    private BigDecimal difftotalPrixUniMarche;

    private BigDecimal difftotalMontantMarche;

    private FournisseurDTO fournisseurComparDTO;

    private Integer codeFournisseurCompar;

    public ComparFactureAchatDTO() {
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

    public FournisseurDTO getFournisseurDTO() {
        return fournisseurDTO;
    }

    public void setFournisseurDTO(FournisseurDTO fournisseurDTO) {
        this.fournisseurDTO = fournisseurDTO;
    }

    public Integer getCodeFournisseur() {
        return codeFournisseur;
    }

    public void setCodeFournisseur(Integer codeFournisseur) {
        this.codeFournisseur = codeFournisseur;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    public List<ComparDetailsFactureAchatDTO> getDetailsComparFactureAchatsAchatDTOs() {
        return detailsComparFactureAchatsAchatDTOs;
    }

    public void setDetailsComparFactureAchatsAchatDTOs(List<ComparDetailsFactureAchatDTO> detailsComparFactureAchatsAchatDTOs) {
        this.detailsComparFactureAchatsAchatDTOs = detailsComparFactureAchatsAchatDTOs;
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

    public BigDecimal getMontantTTCGros() {
        return montantTTCGros;
    }

    public void setMontantTTCGros(BigDecimal montantTTCGros) {
        this.montantTTCGros = montantTTCGros;
    }

    public BigDecimal getDifftotalPrixUniMarche() {
        return difftotalPrixUniMarche;
    }

    public void setDifftotalPrixUniMarche(BigDecimal difftotalPrixUniMarche) {
        this.difftotalPrixUniMarche = difftotalPrixUniMarche;
    }

    public BigDecimal getDifftotalMontantMarche() {
        return difftotalMontantMarche;
    }

    public void setDifftotalMontantMarche(BigDecimal difftotalMontantMarche) {
        this.difftotalMontantMarche = difftotalMontantMarche;
    }

    public FournisseurDTO getFournisseurComparDTO() {
        return fournisseurComparDTO;
    }

    public void setFournisseurComparDTO(FournisseurDTO fournisseurComparDTO) {
        this.fournisseurComparDTO = fournisseurComparDTO;
    }

    public Integer getCodeFournisseurCompar() {
        return codeFournisseurCompar;
    }

    public void setCodeFournisseurCompar(Integer codeFournisseurCompar) {
        this.codeFournisseurCompar = codeFournisseurCompar;
    }

    public FactureAchatDTO getFactureAchatDTO() {
        return factureAchatDTO;
    }

    public void setFactureAchatDTO(FactureAchatDTO factureAchatDTO) {
        this.factureAchatDTO = factureAchatDTO;
    }

    public Integer getCodeFactureAchat() {
        return codeFactureAchat;
    }

    public void setCodeFactureAchat(Integer codeFactureAchat) {
        this.codeFactureAchat = codeFactureAchat;
    }

    
    
}
