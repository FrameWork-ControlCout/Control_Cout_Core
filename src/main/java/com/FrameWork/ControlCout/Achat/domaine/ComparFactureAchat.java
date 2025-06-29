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
@Table(name = "Compar_Facture_Achat", schema = "param_achat")
@Audited
@AuditTable("Compar_Facture_Achat_AUD")
public class ComparFactureAchat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

  

    @JoinColumn(name = "Fournisseur_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur_Facture", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

 
    
        @JoinColumn(name = "Code_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FactureAchat factureAchat;

    @Column(name = "Code_Facture", updatable = false, insertable = false, nullable = false)
    private Integer codeFactureAchat;
 
    
    @JoinColumn(name = "Fournisseur_Compar", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseurCompar;

    @Column(name = "Fournisseur_Compar", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseurCompar;

    
    @Column(name = "Montant_TTC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTTC;

    @OneToMany(mappedBy = "codeComparFactureAchat", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listComparFactureAchat") 
    private List<ComparDetailsFactureAchat> detailsComparFactureAchats;

    @Column(name = "Num_Fact_Frs", nullable = false, columnDefinition = ("varchar(200)"))
    private String numFactFrs;

    @Column(name = "Montant_Fact_Frs", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantFactFrs;

     @Column(name = "Montant_TTC_Gros", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTTCGros;
     
     
         @Column(name = "Diff_Total_Prix_Uni", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal difftotalPrixUniMarche;
         
             @Column(name = "Diff_Total_Motant_Marche", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal difftotalMontantMarche;
     
     
 

    public ComparFactureAchat() {
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

    
    public BigDecimal getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(BigDecimal montantTTC) {
        this.montantTTC = montantTTC;
    }

    public List<ComparDetailsFactureAchat> getDetailsComparFactureAchats() {
        return detailsComparFactureAchats;
    }

    public void setDetailsComparFactureAchats(List<ComparDetailsFactureAchat> detailsComparFactureAchats) {
        this.detailsComparFactureAchats = detailsComparFactureAchats;
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
 
    
    
    
    

}
