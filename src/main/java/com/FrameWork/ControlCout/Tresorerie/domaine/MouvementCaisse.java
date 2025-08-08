/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement;
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
@Table(name = "Mouvement_Caisse", schema = "tresorerie")
@Audited
@AuditTable("Mouvement_Caisse_AUD")
public class MouvementCaisse {

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

//    @JoinColumn(name = "code_caisse_tr", referencedColumnName = "Code",insertable = false,updatable = false)
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    @JsonBackReference
//    private Caisse caisseTr;

    @Column(name = "Code_Caisse_Tr")
    private Integer codeCaisseTr;

    @JoinColumn(name = "Code_Devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "Code_Devise", updatable = false, insertable = false)
    private Integer codeDevise;

    @JoinColumn(name = "Code_Mode_Reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "Code_Mode_Reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @NotNull
    @Column(name = "Debit", columnDefinition = ("decimal(18,3) "), nullable = false)
    private BigDecimal debit;
    @NotNull
    @Column(name = "Credit", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal credit;

    @Column(name = "Montant_Devise", columnDefinition = ("decimal(18,3) "), nullable = false)
    private BigDecimal mntDevise;

    @Column(name = "Code_Tier", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String codeTier;

    public MouvementCaisse() {
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

    public Integer getCodeCaisseTr() {
        return codeCaisseTr;
    }

    public void setCodeCaisseTr(Integer codeCaisseTr) {
        this.codeCaisseTr = codeCaisseTr;
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

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getMntDevise() {
        return mntDevise;
    }

    public void setMntDevise(BigDecimal mntDevise) {
        this.mntDevise = mntDevise;
    }

    public String getCodeTier() {
        return codeTier;
    }

    public void setCodeTier(String codeTier) {
        this.codeTier = codeTier;
    }

//    public Caisse getCaisseTr() {
//        return caisseTr;
//    }
//
//    public void setCaisseTr(Caisse caisseTr) {
//        this.caisseTr = caisseTr;
//    }

    
    
}
