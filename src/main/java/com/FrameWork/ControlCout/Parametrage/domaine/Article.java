/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Article", schema = "param_achat")
@Audited
@AuditTable("Article_AUD")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Size(max = 200)
    @Column(name = "Designation_Ar", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationAr;

    @Size(max = 200)
    @Column(name = "Designation_Lt", length = 200, nullable = false, columnDefinition = "nvarchar(200)")
    private String designationLt;

    @Size(max = 3)
    @Column(name = "Type", length = 3, nullable = false, columnDefinition = "nvarchar(3)")
    private String type;

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Famille", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FamilleArticle familleArticle;

    @Column(name = "Code_Famille", updatable = false, insertable = false, nullable = false)
    private Integer codeFamille;

    @JoinColumn(name = "Code_Unite", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite unite;

    @Column(name = "Code_Unite", updatable = false, insertable = false, nullable = false)
    private Integer codeUnite;

    @Column(name = "Package", nullable = false, length = 255, columnDefinition = "int")
    private Integer packages;

    
    
    
        @JoinColumn(name = "Code_Unite_Secondaire", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite uniteSecondaire;

    @Column(name = "Code_Unite_Secondaire", updatable = false, insertable = false, nullable = false)
    private Integer codeUniteSecondaire;

    
    
       
        @JoinColumn(name = "Code_Unite_Depense", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite uniteDepense;

    @Column(name = "Code_Unite_Depense", updatable = false, insertable = false, nullable = false)
    private Integer codeUniteDepense;
    @Column(name = "Conversion_Rate", nullable = false, length = 255, columnDefinition = "int")
    private Integer conversionRate;
    
        @Column(name = "Last_Prix_Achat" , columnDefinition = ("decimal(18,3)"))
    private BigDecimal lastPrixAchat;

        
        
        
    public Article() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public FamilleArticle getFamilleArticle() {
        return familleArticle;
    }

    public void setFamilleArticle(FamilleArticle familleArticle) {
        this.familleArticle = familleArticle;
    }

    public Integer getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(Integer codeFamille) {
        this.codeFamille = codeFamille;
    }

    public Unite getUnite() {
        return unite;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
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

    public BigDecimal getLastPrixAchat() {
        return lastPrixAchat;
    }

    public void setLastPrixAchat(BigDecimal lastPrixAchat) {
        this.lastPrixAchat = lastPrixAchat;
    }

    public Unite getUniteSecondaire() {
        return uniteSecondaire;
    }

    public void setUniteSecondaire(Unite uniteSecondaire) {
        this.uniteSecondaire = uniteSecondaire;
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

    public Unite getUniteDepense() {
        return uniteDepense;
    }

    public void setUniteDepense(Unite uniteDepense) {
        this.uniteDepense = uniteDepense;
    }

    public Integer getCodeUniteDepense() {
        return codeUniteDepense;
    }

    public void setCodeUniteDepense(Integer codeUniteDepense) {
        this.codeUniteDepense = codeUniteDepense;
    }

    
}
