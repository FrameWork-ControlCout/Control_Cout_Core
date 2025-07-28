/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatPaiement;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
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
import java.math.BigDecimal;
import java.util.Date;
import lombok.*; 
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Mouvement_Fournisseur", schema = "tresorerie")
@Audited
@AuditTable("Mouvement_Fournisseur_AUD")
@Getter
@Setter
public class MouvementFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur", updatable = false, insertable = false)
    private Integer codeFournisseur;
    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @Column(name = "Code_Facture_BR", nullable = false)
    private Integer codeFactureBonReception;

    @Column(name = "Code_Order_Paiement")
    private Integer codeOrderPaiement;
    
      @Column(name = "Type_Mouvement")
    private String typeMouvement;
    
    @Column(name = "Prix_Facture", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixFacture;

    @Column(name = "Deduction_Facture", columnDefinition = ("decimal(18,3)"))
    private BigDecimal deductionFacture;
    
       @JoinColumn(name = "Etat_Paiement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private EtatPaiement etatPaiement;

    @Column(name = "Etat_Paiement", updatable = false, insertable = false)
    private Integer codeEtatPaiement;

    public MouvementFournisseur() {
    }
    
    
    

}
