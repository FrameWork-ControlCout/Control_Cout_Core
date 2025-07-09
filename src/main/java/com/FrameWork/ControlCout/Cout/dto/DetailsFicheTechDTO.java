/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsFicheTechDTO {

    private Integer code;

    private FicheTechDTO techCardDTO;

    private Integer codeFicheTechnique;

    private String userCreate;

    private Date dateCreate;

    private ArticleDTO articleDTO;

    private Integer codeArticle;
    
    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private Integer consUni;
    
    private BigDecimal consTotal;

    private BigDecimal prixUni;

    private BigDecimal prixTotal;
    
        private UniteDTO uniteSecondaireDTO;

     private Integer codeUniteSecondaire;

    public DetailsFicheTechDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public FicheTechDTO getFicheTechniqueDTO() {
        return techCardDTO;
    }

    public void setFicheTechniqueDTO(FicheTechDTO techCardDTO) {
        this.techCardDTO = techCardDTO;
    }

    public Integer getCodeFicheTechnique() {
        return codeFicheTechnique;
    }

    public void setCodeFicheTechnique(Integer codeFicheTechnique) {
        this.codeFicheTechnique = codeFicheTechnique;
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

    public Integer getConsUni() {
        return consUni;
    }

    public void setConsUni(Integer consUni) {
        this.consUni = consUni;
    }

    public BigDecimal getConsTotal() {
        return consTotal;
    }

    public void setConsTotal(BigDecimal consTotal) {
        this.consTotal = consTotal;
    }

    public BigDecimal getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(BigDecimal prixUni) {
        this.prixUni = prixUni;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
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

    
}
