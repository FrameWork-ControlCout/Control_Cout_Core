/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DetailsTechCardDTO {

    private Integer code;

    private TechCardDTO techCardDTO;

    private Integer codeTechCard;

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

    public DetailsTechCardDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public TechCardDTO getTechCardDTO() {
        return techCardDTO;
    }

    public void setTechCardDTO(TechCardDTO techCardDTO) {
        this.techCardDTO = techCardDTO;
    }

    public Integer getCodeTechCard() {
        return codeTechCard;
    }

    public void setCodeTechCard(Integer codeTechCard) {
        this.codeTechCard = codeTechCard;
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

}
