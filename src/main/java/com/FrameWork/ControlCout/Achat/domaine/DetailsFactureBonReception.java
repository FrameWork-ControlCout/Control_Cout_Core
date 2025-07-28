/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
@Table(name = "Details_Facture_Bon_Reception", schema = "param_achat")
@Audited
@AuditTable("Details_Facture_Bon_Reception_AUD")
@Getter
@Setter
public class DetailsFactureBonReception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Facture_BR", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FactureBonReception factureBonReception;

    @Column(name = "Code_Facture_BR", insertable = false, updatable = false)
    private Integer codeFactureBonReception;

    @NotNull
    @Column(name = "Qte_Besoin", precision = 18, scale = 3)
    private BigDecimal qteBesoin;

    @NotNull
    @Column(name = "Qte_Receptionner", precision = 18, scale = 3)
    private BigDecimal qteReceptionner;

    @JoinColumn(name = "Code_Depot", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depot;

    @Column(name = "Code_Depot", updatable = false, insertable = false)
    private Integer codeDepot;

    @JoinColumn(name = "Fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Fournisseur", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

    @JoinColumn(name = "Code_Details_Order", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private DetailsOrderAchat detailsOrderAchat;

    @Column(name = "Code_Details_Order", updatable = false, insertable = false)
    private Integer codeDetailsOrderAchat;
    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

    @JoinColumn(name = "Code_Article", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @Column(name = "Code_Article", insertable = false, updatable = false)
    private Integer codeArticle;

    @JoinColumn(name = "Code_Unite", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite unite;

    @Column(name = "Code_Unite", insertable = false, updatable = false)
    private Integer codeUnite;

    @Column(name = "Prix_Unitaire_Achat", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixUniAchat;

    @Column(name = "Prix_Total_Achat", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotalAchat;

    @Column(name = "Prix_Unitaire_Gros", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixUniGros;

    @Column(name = "Prix_Total_Gros", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotalGros;
    
       @Column(name = "Diff_Prix_Uni", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal diffPrixUni;

    @Column(name = "Diff_Prix_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal diffPrixTotal;

    public DetailsFactureBonReception() {
    }

}
