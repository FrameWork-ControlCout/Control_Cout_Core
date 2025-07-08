/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.*;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
 
public class DetailsCalculeConsommationDTO {

    private Integer code;

     
  private CalculeConsommationDTO calculeConsommationDTO;

     private Integer codeCalculeConsommation;
    
   
     private String userCreate;

    private Date dateCreate; 

 

     private ArticleDTO articleDTO;

     private Integer codeArticle;

    private UniteDTO uniteSecondaireDTO;

    private Integer codeUniteSecondaire;
    
   private UniteDTO uniteConsoDTO;

     private Integer codeUniteConso;
    
    
 
    private Integer consUni;
    private BigDecimal consTotal;

    private BigDecimal prixUni;

    private BigDecimal prixTotal;
 
 
    
    public DetailsCalculeConsommationDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CalculeConsommationDTO getCalculeConsommationDTO() {
        return calculeConsommationDTO;
    }

    public void setCalculeConsommationDTO(CalculeConsommationDTO calculeConsommationDTO) {
        this.calculeConsommationDTO = calculeConsommationDTO;
    }

    

    public Integer getCodeCalculeConsommation() {
        return codeCalculeConsommation;
    }

    public void setCodeCalculeConsommation(Integer codeCalculeConsommation) {
        this.codeCalculeConsommation = codeCalculeConsommation;
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

    public UniteDTO getUniteConsoDTO() {
        return uniteConsoDTO;
    }

    public void setUniteConsoDTO(UniteDTO uniteConsoDTO) {
        this.uniteConsoDTO = uniteConsoDTO;
    }

    public Integer getCodeUniteConso() {
        return codeUniteConso;
    }

    public void setCodeUniteConso(Integer codeUniteConso) {
        this.codeUniteConso = codeUniteConso;
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
 
 
    
    
}
