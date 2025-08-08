/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Avance_Fournisseur", schema = "tresorerie")
@Audited
@AuditTable("Avance_Fournisseur_AUD")
@Getter
@Setter
public class AvanceFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

        @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

        
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


    @Column(name = "Appurer", nullable = false)
    private boolean appurer;

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

    @JoinColumn(name = "Code_Mode_Reglement", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ModeReglement modeReglement;

    @Column(name = "Code_Mode_Reglement", updatable = false, insertable = false)
    private Integer codeModeReglement;

     @Column(name = "Montant_Avance", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal mntAvance;

    @Column(name = "Montant_En_Devise_Principal", columnDefinition = ("decimal(18,3)"), nullable = false)
    private BigDecimal montantEnDevise;

    @Column(name = "Taux_De_Change", columnDefinition = ("decimal(18,3)"))
    private BigDecimal tauxChange;

}
