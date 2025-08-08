/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
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
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Solde_Caisse", schema = "tresorerie")
@Audited
@AuditTable("Solde_Caisse_AUD")
public class SoldeCaisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Caisse", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Caisse caisse;

    @Column(name = "Code_Caisse", updatable = false, insertable = false, nullable = false)
    private Integer codeCaisse;

    @JoinColumn(name = "Code_Devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "Code_Devise", updatable = false, insertable = false, nullable = false)
    private Integer codeDevise;

    @Column(name = "Debit", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal debit;

    @Column(name = "Credit", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal credit;

    @Column(name = "User_Updated", nullable = false, length = 255, columnDefinition = "nvarchar(max)")
    private String userUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Updated", nullable = false)
    private Date dateUpdated;

    public SoldeCaisse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
    

}
