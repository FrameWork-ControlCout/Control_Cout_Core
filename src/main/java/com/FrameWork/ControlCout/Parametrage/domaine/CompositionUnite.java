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
@Table(name = "Composition_Unite", schema = "param_achat")
@Audited
@AuditTable("Composition_Unite_AUD")
public class CompositionUnite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Column(name = "Nbre_Unite")
    private Integer nmbreUnite;

    @Column(name = "Code_Unite_Pricipal", updatable = false, insertable = false, nullable = false)
    private Integer codeUnitePrinc;

    @JoinColumn(name = "Code_Unite_Pricipal", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite unitePrinc;

    @Column(name = "Code_Unite_Secondaire", updatable = false, insertable = false, nullable = false)
    private Integer codeUniteSec;

    @JoinColumn(name = "Code_Unite_Secondaire", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite uniteSec;

    public CompositionUnite() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getNmbreUnite() {
        return nmbreUnite;
    }

    public void setNmbreUnite(Integer nmbreUnite) {
        this.nmbreUnite = nmbreUnite;
    }

    public Integer getCodeUnitePrinc() {
        return codeUnitePrinc;
    }

    public void setCodeUnitePrinc(Integer codeUnitePrinc) {
        this.codeUnitePrinc = codeUnitePrinc;
    }

    public Unite getUnitePrinc() {
        return unitePrinc;
    }

    public void setUnitePrinc(Unite unitePrinc) {
        this.unitePrinc = unitePrinc;
    }

    public Integer getCodeUniteSec() {
        return codeUniteSec;
    }

    public void setCodeUniteSec(Integer codeUniteSec) {
        this.codeUniteSec = codeUniteSec;
    }

    public Unite getUniteSec() {
        return uniteSec;
    }

    public void setUniteSec(Unite uniteSec) {
        this.uniteSec = uniteSec;
    }

}
