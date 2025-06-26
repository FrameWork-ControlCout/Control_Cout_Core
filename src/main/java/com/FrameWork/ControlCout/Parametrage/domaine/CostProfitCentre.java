/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.domaine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Collection;
import java.util.Date;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Cost_Profit_Centre", schema = "param")
@Audited
@AuditTable("Cost_Profit_Centre_AUD")
public class CostProfitCentre {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

//    @Basic(optional = false) 
////    @Size(min = 1, max = 20)
//    @Column(name = "classement" , length = 20)
//    private Integer classement;
    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "Code_Saisie", nullable = false, length = 20)
    private String codeSaisie;
    @Basic(optional = false)

    @Size(min = 1, max = 200)
    @Column(name = "Designation_Ar", nullable = false, length = 200, columnDefinition = "nvarchar(200)")
    private String designationAr;
    @Basic(optional = false)

    @Size(min = 1, max = 200)
    @Column(name = "Designation", nullable = false, length = 200, columnDefinition = "nvarchar(200)")
    private String designation;

    @Basic(optional = false)
    @Column(name = "Profit_Centre", nullable = false)
    private boolean profitCentre;

    @Basic(optional = false)
    @Column(name = "Detail", nullable = false)
    private boolean detail;

    @Basic(optional = false)
    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Basic(optional = false)
    @Size(min = 1, max = 20)
    @Column(name = "User_Create", nullable = false, length = 20, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;
 
    @JoinColumn(name = "Code_Type_profit_centre", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeCostCentre typeCostCentre;

    @Column(name = "Code_Type_profit_centre", updatable = false, insertable = false, nullable = false)
    private Integer codeTypeCostCentre;

    @Column(name = "Base", nullable = false, columnDefinition = ("bit default 0"))
    private boolean base;
//
//    @Column(name = "niveau", nullable = false)
//    private Integer niveau;

//    @Join 
    public CostProfitCentre() {
    }

    public CostProfitCentre(Integer code) {
        this.code = code;
    }

    public CostProfitCentre(Integer code, String codeSaisie, String designationAr, String designation, boolean profitCentre, boolean detail, boolean actif, String userCreate) {
        this.code = code;
        this.codeSaisie = codeSaisie;
        this.designationAr = designationAr;
        this.designation = designation;
        this.profitCentre = profitCentre;
        this.detail = detail;
        this.actif = actif;
        this.userCreate = userCreate;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean getProfitCentre() {
        return profitCentre;
    }

    public void setProfitCentre(boolean profitCentre) {
        this.profitCentre = profitCentre;
    }

    public boolean getDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean getActif() {
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

    public TypeCostCentre getTypeCostCentre() {
        return typeCostCentre;
    }

    public void setTypeCostCentre(TypeCostCentre typeCostCentre) {
        this.typeCostCentre = typeCostCentre;
    }

    public Integer getCodeTypeCostCentre() {
        return codeTypeCostCentre;
    }

    public void setCodeTypeCostCentre(Integer codeTypeCostCentre) {
        this.codeTypeCostCentre = codeTypeCostCentre;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostProfitCentre)) {
            return false;
        }
        CostProfitCentre other = (CostProfitCentre) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

//    public Integer getNiveau() {
//        return niveau;
//    }
//
//    public void setNiveau(Integer niveau) {
//        this.niveau = niveau;
//    }
//    public Collection<CostProfitCentre> getCostProfitCentreCollection() {
//        return costProfitCentreCollection;
//    }
//
//    public void setCostProfitCentreCollection(Collection<CostProfitCentre> costProfitCentreCollection) {
//        this.costProfitCentreCollection = costProfitCentreCollection;
//    }
//    public Integer getClassement() {
//        return classement;
//    }
//
//    public void setClassement(Integer classement) {
//        this.classement = classement;
//    }
}
