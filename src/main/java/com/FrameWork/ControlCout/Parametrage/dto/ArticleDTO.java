/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.FamilleArticle;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ArticleDTO {

    private Integer code;

    private String codeSaisie;
    private String codeSaisieArticle;

    private String designationAr;
    private String designationArArticle;

    private String designationLt;
    private String designationLtArticle;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;
    private String type;

    private FamilleArticleDTO familleArticleDTO;

    private Integer codeFamille;

    private UniteDTO uniteDTO;
    private Integer codeUnite;

    private Integer packages;

    private BigDecimal lastPrixAchat;

    private UniteDTO uniteSecondaireDTO;

    private Integer codeUniteSecondaire;

    private Integer conversionRate;

    private UniteDTO uniteDepenseDTO;
    private Integer codeUniteDepense;

    public ArticleDTO() {
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

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FamilleArticleDTO getFamilleArticleDTO() {
        return familleArticleDTO;
    }

    public void setFamilleArticleDTO(FamilleArticleDTO familleArticleDTO) {
        this.familleArticleDTO = familleArticleDTO;
    }

    public Integer getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(Integer codeFamille) {
        this.codeFamille = codeFamille;
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

    public Integer getPackages() {
        return packages;
    }

    public void setPackages(Integer packages) {
        this.packages = packages;
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

    public BigDecimal getLastPrixAchat() {
        return lastPrixAchat;
    }

    public void setLastPrixAchat(BigDecimal lastPrixAchat) {
        this.lastPrixAchat = lastPrixAchat;
    }

    public UniteDTO getUniteSecondaireDTO() {
        return uniteSecondaireDTO;
    }

    public void setUniteSecondaireDTO(UniteDTO uniteSecondaireDTO) {
        this.uniteSecondaireDTO = uniteSecondaireDTO;
    }

    public Integer getCodeUniteSecondaire() {
        return codeUniteSecondaire;
    }

    public void setCodeUniteSecondaire(Integer codeUniteSecondaire) {
        this.codeUniteSecondaire = codeUniteSecondaire;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public UniteDTO getUniteDepenseDTO() {
        return uniteDepenseDTO;
    }

    public void setUniteDepenseDTO(UniteDTO uniteDepenseDTO) {
        this.uniteDepenseDTO = uniteDepenseDTO;
    }

    public Integer getCodeUniteDepense() {
        return codeUniteDepense;
    }

    public void setCodeUniteDepense(Integer codeUniteDepense) {
        this.codeUniteDepense = codeUniteDepense;
    }

}
