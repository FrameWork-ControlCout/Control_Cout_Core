/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

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
@Table(name = "Facture_Bon_Reception", schema = "param_achat")
@Audited
@AuditTable("Facture_Bon_Reception_AUD")
@Getter
@Setter
public class FactureBonReception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @NotNull
    @Column(name = "Code_Saisie", unique = true, length = 200)
    private String codeSaisie;

    @JoinColumn(name = "Code_Depot", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depot;

    @Column(name = "Code_Depot", updatable = false, insertable = false)
    private Integer codeDepot;
    
  
    
        @JoinColumn(name = "Code_Bon_Reception", referencedColumnName = "Code" )
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private BonReception bonReception;

    @Column(name = "Code_Bon_Reception", updatable = false, insertable = false )
    private Integer codeBonReception;
    

    @JoinColumn(name = "Fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;



  

    @Column(name = "Num_Fact_Frs", nullable = false, columnDefinition = ("varchar(200)"))
    private String numFactFrs;

    @NotNull
    @Column(name = "Montant_Diff_Prix", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal montantDiffPrix;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Date_Facture_Frs", nullable = false, columnDefinition = ("date"))
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFactFrs;

    @Column(name = "Prix_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotal;

    public FactureBonReception() {
    }

}
