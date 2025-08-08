/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
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
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Caisse", schema = "tresorerie")
@Audited
@AuditTable("Caisse_AUD")
@Getter
@Setter
public class Caisse {

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

    @Column(name = "Actif", nullable = false)
    private boolean actif;

    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false,columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Devise", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Devise devise;

    @Column(name = "Code_Devise", updatable = false, insertable = false, nullable = false)
    private Integer codeDevise;

    @JoinColumn(name = "Code_Type_Caisse", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private TypeCaisse typeCaisse;

    @Column(name = "Code_Type_Caisse", updatable = false, insertable = false, nullable = false)
    private Integer codeTypeCaisse;
    
       @Size(max = 2)
    @Column(name = "Type", length = 2, nullable = false, columnDefinition = "nvarchar(2)")
    private String type;

    public Caisse() {
    }

    
    
}
