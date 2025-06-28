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
public class DetailsFactureAchatDTO {

    private Integer code;

    private FactureAchatDTO factureAchatDTO;

    private Integer codeFactureAchat;

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

    private BigDecimal prixUnitaire;  
    private BigDecimal prixUni;

    

    private BigDecimal montantHt;

    private BigDecimal montantTva;

    private BigDecimal montantTTC;

    private BigDecimal qteReceptionner;

    public DetailsFactureAchatDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCodeFactureAchat() {
        return codeFactureAchat;
    }

    public void setCodeFactureAchat(Integer codeFactureAchat) {
        this.codeFactureAchat = codeFactureAchat;
    }

    public Integer getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(Integer codeArticle) {
        this.codeArticle = codeArticle;
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

    public Integer getCodeUnite() {
        return codeUnite;
    }

    public void setCodeUnite(Integer codeUnite) {
        this.codeUnite = codeUnite;
    }

    public BigDecimal getQteReceptionner() {
        return qteReceptionner;
    }

    public void setQteReceptionner(BigDecimal qteReceptionner) {
        this.qteReceptionner = qteReceptionner;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public FactureAchatDTO getFactureAchatDTO() {
        return factureAchatDTO;
    }

    public void setFactureAchatDTO(FactureAchatDTO factureAchatDTO) {
        this.factureAchatDTO = factureAchatDTO;
    }

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    public UniteDTO getUniteDTO() {
        return uniteDTO;
    }

    public void setUniteDTO(UniteDTO uniteDTO) {
        this.uniteDTO = uniteDTO;
    }

    public FournisseurDTO getFournisseurDTO() {
        return fournisseurDTO;
    }

    public void setFournisseurDTO(FournisseurDTO fournisseurDTO) {
        this.fournisseurDTO = fournisseurDTO;
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

    public BigDecimal getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(BigDecimal prixUni) {
        this.prixUni = prixUni;
    }
    

}
