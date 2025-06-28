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
@Table(name = "Facture_Achat", schema = "param_achat")
@Audited
@AuditTable("Facture_Achat_AUD")
public class FactureAchat {

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

    @JoinColumn(name = "Etat_Facture", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatFacture etatFacture;

    @Column(name = "Etat_Facture", updatable = false, insertable = false, nullable = false)
    private Integer codeEtatFacture;

    @Column(name = "User_Approuve", length = 255, columnDefinition = "nvarchar(200)")
    private String userApprove;

    @JoinColumn(name = "Fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

    @JoinColumn(name = "Cost_Centre", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private CostProfitCentre costProfitCentre;

    @Column(name = "Cost_Centre", updatable = false, insertable = false, nullable = false)
    private Integer codeCodeProfitCentre;

    @JoinColumn(name = "Mode_Reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "Mode_Reglement", updatable = false, insertable = false, nullable = false)
    private Integer codeModeReglement;

    @Column(name = "Banque", columnDefinition = ("int"))
    private Integer codeBanque;

    @Column(name = "Num_Piece", columnDefinition = ("varchar(200)"))
    private String numPiece;

    @Column(name = "Montant_Ht", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantHt;

    @Column(name = "Montant_Tva", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTva;

    @Column(name = "Montant_TTC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantTTC;

    @OneToMany(mappedBy = "codeFactureAchat", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listFactureAchat") // Unique name
    private List<DetailsFactureAchat> detailsFactureAchats;

    @Column(name = "Num_Fact_Frs", nullable = false, columnDefinition = ("varchar(200)"))
    private String numFactFrs;

    @Column(name = "Montant_Fact_Frs", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantFactFrs;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Facture_Frs", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFactFrs;

    @JoinColumn(name = "Code_Devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "Code_Devise", updatable = false, insertable = false, nullable = false)
    private Integer codeDevise;

    public FactureAchat() {
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

    public EtatFacture getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Integer getCodeEtatFacture() {
        return codeEtatFacture;
    }

    public void setCodeEtatFacture(Integer codeEtatFacture) {
        this.codeEtatFacture = codeEtatFacture;
    }

    public String getUserApprove() {
        return userApprove;
    }

    public void setUserApprove(String userApprove) {
        this.userApprove = userApprove;
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

    public List<DetailsFactureAchat> getDetailsFactureAchats() {
        return detailsFactureAchats;
    }

    public void setDetailsFactureAchats(List<DetailsFactureAchat> detailsFactureAchats) {
        this.detailsFactureAchats = detailsFactureAchats;
    }

    public CostProfitCentre getCostProfitCentre() {
        return costProfitCentre;
    }

    public void setCostProfitCentre(CostProfitCentre costProfitCentre) {
        this.costProfitCentre = costProfitCentre;
    }

    public Integer getCodeCodeProfitCentre() {
        return codeCodeProfitCentre;
    }

    public void setCodeCodeProfitCentre(Integer codeCodeProfitCentre) {
        this.codeCodeProfitCentre = codeCodeProfitCentre;
    }

    public ModeReglement getModeReglement() {
        return modeReglement;
    }

    public void setModeReglement(ModeReglement modeReglement) {
        this.modeReglement = modeReglement;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
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

    public LocalDate getDateFactFrs() {
        return dateFactFrs;
    }

    public void setDateFactFrs(LocalDate dateFactFrs) {
        this.dateFactFrs = dateFactFrs;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }

}
