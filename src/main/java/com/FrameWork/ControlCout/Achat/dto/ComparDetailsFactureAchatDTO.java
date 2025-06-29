/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ComparDetailsFactureAchatDTO {

    private Integer code;

    private FactureAchatDTO factureAchatDTO;

    private Integer codeFactureAchat;

    private ComparFactureAchatDTO comparFactureAchatDTO;

    private Integer codeComparFactureAchat;

    private ArticleDTO articleDTO;

    private Integer codeArticle;
    private String codeSaisieArticle;
    private String designationArArticle;
    private String designationLtArticle;
    private Integer packages;

    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private String usercreate;

    private String caracterstique;

    private Date dateCreate;

    private FournisseurDTO fournisseurDTO;
    private Integer codeFournisseur;

    private FournisseurDTO fournisseurDTOCompar;
    private Integer codeFournisseurCompar;

    private BigDecimal prixUnitaire;

    private BigDecimal montantTTC;
    private BigDecimal qteReceptionner;

    private BigDecimal prixUnitaireGros;

    private BigDecimal montantTTCGros;

    private BigDecimal diffPrixUni;

    private BigDecimal diffPrixTotal;

    public ComparDetailsFactureAchatDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    public Integer getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(Integer codeArticle) {
        this.codeArticle = codeArticle;
    }

    public UniteDTO getUniteDTO() {
        return uniteDTO;
    }

    public void setUniteDTO(UniteDTO uniteDTO) {
        this.uniteDTO = uniteDTO;
    }

    public Integer getCodeUnite() {
        return codeUnite;
    }

    public void setCodeUnite(Integer codeUnite) {
        this.codeUnite = codeUnite;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getCaracterstique() {
        return caracterstique;
    }

    public void setCaracterstique(String caracterstique) {
        this.caracterstique = caracterstique;
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

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    public BigDecimal getQteReceptionner() {
        return qteReceptionner;
    }

    public void setQteReceptionner(BigDecimal qteReceptionner) {
        this.qteReceptionner = qteReceptionner;
    }

    public BigDecimal getPrixUnitaireGros() {
        return prixUnitaireGros;
    }

    public void setPrixUnitaireGros(BigDecimal prixUnitaireGros) {
        this.prixUnitaireGros = prixUnitaireGros;
    }

    public BigDecimal getMontantTTCGros() {
        return montantTTCGros;
    }

    public void setMontantTTCGros(BigDecimal montantTTCGros) {
        this.montantTTCGros = montantTTCGros;
    }

    public BigDecimal getDiffPrixUni() {
        return diffPrixUni;
    }

    public void setDiffPrixUni(BigDecimal diffPrixUni) {
        this.diffPrixUni = diffPrixUni;
    }

    public BigDecimal getDiffPrixTotal() {
        return diffPrixTotal;
    }

    public void setDiffPrixTotal(BigDecimal diffPrixTotal) {
        this.diffPrixTotal = diffPrixTotal;
    }

    public String getCodeSaisieArticle() {
        return codeSaisieArticle;
    }

    public void setCodeSaisieArticle(String codeSaisieArticle) {
        this.codeSaisieArticle = codeSaisieArticle;
    }

    public String getDesignationArArticle() {
        return designationArArticle;
    }

    public void setDesignationArArticle(String designationArArticle) {
        this.designationArArticle = designationArArticle;
    }

    public String getDesignationLtArticle() {
        return designationLtArticle;
    }

    public void setDesignationLtArticle(String designationLtArticle) {
        this.designationLtArticle = designationLtArticle;
    }

    public Integer getPackages() {
        return packages;
    }

    public void setPackages(Integer packages) {
        this.packages = packages;
    }

    public FournisseurDTO getFournisseurDTOCompar() {
        return fournisseurDTOCompar;
    }

    public void setFournisseurDTOCompar(FournisseurDTO fournisseurDTOCompar) {
        this.fournisseurDTOCompar = fournisseurDTOCompar;
    }

    public Integer getCodeFournisseurCompar() {
        return codeFournisseurCompar;
    }

    public void setCodeFournisseurCompar(Integer codeFournisseurCompar) {
        this.codeFournisseurCompar = codeFournisseurCompar;
    }

    public ComparFactureAchatDTO getComparFactureAchatDTO() {
        return comparFactureAchatDTO;
    }

    public void setComparFactureAchatDTO(ComparFactureAchatDTO comparFactureAchatDTO) {
        this.comparFactureAchatDTO = comparFactureAchatDTO;
    }

    public Integer getCodeComparFactureAchat() {
        return codeComparFactureAchat;
    }

    public void setCodeComparFactureAchat(Integer codeComparFactureAchat) {
        this.codeComparFactureAchat = codeComparFactureAchat;
    }

}
