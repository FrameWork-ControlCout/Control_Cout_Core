/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Calcul_Consommation", schema = "cout")
@Audited
@AuditTable("Calcul_Consommation_AUD")
public class CalculeConsommation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Debut", nullable = false, columnDefinition = "datetime  ")
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Fin", nullable = false, columnDefinition = "datetime  ")
    private Date dateFin;

    @NotNull
    @Column(name = "Nbre_Jours", updatable = false, insertable = false, nullable = false)
    private Integer nbreJours;

    @OneToMany(mappedBy = "codeCalculeConsommation", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listCalculeConsommation") // Unique name
    private List<DetailsCalculeConsommation> detailsCalculeConsommations;

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "Montant_Total_TTC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotal;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    public CalculeConsommation() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNbreJours() {
        return nbreJours;
    }

    public void setNbreJours(Integer nbreJours) {
        this.nbreJours = nbreJours;
    }

    public List<DetailsCalculeConsommation> getDetailsCalculeConsommations() {
        return detailsCalculeConsommations;
    }

    public void setDetailsCalculeConsommations(List<DetailsCalculeConsommation> detailsCalculeConsommations) {
        this.detailsCalculeConsommations = detailsCalculeConsommations;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
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

}
