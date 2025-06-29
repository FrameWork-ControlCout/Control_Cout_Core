/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Details_Compar_Facture_Achat", schema = "param_achat")
@Audited
@AuditTable("Details_Compar_Facture_Achat_AUD")
public class ComparDetailsFactureAchat {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    
        
        @JoinColumn(name = "Code_Compar_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ComparFactureAchat comparFactureAchat;

    @Column(name = "Code_Compar_Facture", insertable = false, updatable = false)
    private Integer codeComparFactureAchat;
    
    
    
    
    @JoinColumn(name = "Code_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FactureAchat factureAchat;

    @Column(name = "Code_Facture", insertable = false, updatable = false)
    private Integer codeFactureAchat;

    
    

    
    
    
    @JoinColumn(name = "Code_Article", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @Column(name = "Code_Article", updatable = false, insertable = false, nullable = false)
    private Integer codeArticle;

    @JoinColumn(name = "Code_Unite", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite unite;

    @Column(name = "Code_Unite", insertable = false, updatable = false)
    private Integer codeUnite;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @Column(name = "Caracterstique", nullable = false, columnDefinition = "nvarchar(200)")
    private String caracterstique;

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @JoinColumn(name = "Fournisseur_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur_Facture", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

        @JoinColumn(name = "Fournisseur_Compar", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseurCompar;

    @Column(name = "Fournisseur_Compar", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseurCompar;
    
    @Column(name = "Prix_Unitaire", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixUnitaire;

    @Column(name = "Montant_TTC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTTC;

    @Column(name = "Qte_Receptionnner", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal qteReceptionner;

    @Column(name = "Prix_Unitaire_Gros", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixUnitaireGros;

    @Column(name = "Montant_TTC_Gros", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTTCGros;

    @Column(name = "Diff_Prix_Unitaire", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal diffPrixUni;

    @Column(name = "Diff_Prix_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal diffPrixTotal;

    public ComparDetailsFactureAchat() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public FactureAchat getFactureAchat() {
        return factureAchat;
    }

    public void setFactureAchat(FactureAchat factureAchat) {
        this.factureAchat = factureAchat;
    }

    public Integer getCodeFactureAchat() {
        return codeFactureAchat;
    }

    public void setCodeFactureAchat(Integer codeFactureAchat) {
        this.codeFactureAchat = codeFactureAchat;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(Integer codeArticle) {
        this.codeArticle = codeArticle;
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

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
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

    public Fournisseur getFournisseurCompar() {
        return fournisseurCompar;
    }

    public void setFournisseurCompar(Fournisseur fournisseurCompar) {
        this.fournisseurCompar = fournisseurCompar;
    }

    public Integer getCodeFournisseurCompar() {
        return codeFournisseurCompar;
    }

    public void setCodeFournisseurCompar(Integer codeFournisseurCompar) {
        this.codeFournisseurCompar = codeFournisseurCompar;
    }

    public ComparFactureAchat getComparFactureAchat() {
        return comparFactureAchat;
    }

    public void setComparFactureAchat(ComparFactureAchat comparFactureAchat) {
        this.comparFactureAchat = comparFactureAchat;
    }

    public Integer getCodeComparFactureAchat() {
        return codeComparFactureAchat;
    }

    public void setCodeComparFactureAchat(Integer codeComparFactureAchat) {
        this.codeComparFactureAchat = codeComparFactureAchat;
    }
    
    

}
