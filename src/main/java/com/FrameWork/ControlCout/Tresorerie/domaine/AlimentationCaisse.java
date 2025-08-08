/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement; 
import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.Collection;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Alimentation_Caisse", schema = "tresorerie")
@Audited
@AuditTable("Alimentation_Caisse_AUD")
public class AlimentationCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @JoinColumn(name = "Code_Caisse", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Caisse caisse;

    @Column(name = "Code_Caisse", updatable = false, insertable = false)
    private Integer codeCaisse;

    @JoinColumn(name = "Code_Devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "Code_Devise", updatable = false, insertable = false)
    private Integer codeDevise;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @Size(max = 200)
    @Column(name = "Observation", length = 200, nullable = false, columnDefinition = "nvarchar(max)")
    private String observation;

    @JoinColumn(name = "Code_Mode_Reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "Code_Mode_Reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

    @JoinColumn(name = "Code_Etat_Approuver", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatApprouver etatApprouver;

    @Column(name = "Code_Etat_Approuver", updatable = false, insertable = false)
    private Integer codeEtatApprouver;

    @Column(name = "Code_User_Approuver", columnDefinition = "Nvarchar(200) default ''")
    private Long codeUserApprouver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Approuve", columnDefinition = "datetime")
    private Date dateApprouve;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alimentationCaisse")
    private Collection<DetailsAlimentationCaisse> detailsAlimentationCaisses;

    @Column(name = "Montant", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montant;

    @Column(name = "Montant_En_Devise_Principal", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantEnDevise;

    @Column(name = "Taux_De_Change", columnDefinition = ("decimal(18,3)"))
    private BigDecimal tauxChange;

    public AlimentationCaisse() {
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public Integer getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(Integer codeCaisse) {
        this.codeCaisse = codeCaisse;
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

    public EtatApprouver getEtatApprouver() {
        return etatApprouver;
    }

    public void setEtatApprouver(EtatApprouver etatApprouver) {
        this.etatApprouver = etatApprouver;
    }

    public Integer getCodeEtatApprouver() {
        return codeEtatApprouver;
    }

    public void setCodeEtatApprouver(Integer codeEtatApprouver) {
        this.codeEtatApprouver = codeEtatApprouver;
    }

    public Long getCodeUserApprouver() {
        return codeUserApprouver;
    }

    public void setCodeUserApprouver(Long codeUserApprouver) {
        this.codeUserApprouver = codeUserApprouver;
    }

    public Date getDateApprouve() {
        return dateApprouve;
    }

    public void setDateApprouve(Date dateApprouve) {
        this.dateApprouve = dateApprouve;
    }

    public Collection<DetailsAlimentationCaisse> getDetailsAlimentationCaisses() {
        return detailsAlimentationCaisses;
    }

    public void setDetailsAlimentationCaisses(Collection<DetailsAlimentationCaisse> detailsAlimentationCaisses) {
        this.detailsAlimentationCaisses = detailsAlimentationCaisses;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getMontantEnDevise() {
        return montantEnDevise;
    }

    public void setMontantEnDevise(BigDecimal montantEnDevise) {
        this.montantEnDevise = montantEnDevise;
    }

    public BigDecimal getTauxChange() {
        return tauxChange;
    }

    public void setTauxChange(BigDecimal tauxChange) {
        this.tauxChange = tauxChange;
    }
    

}
