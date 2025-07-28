/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.domaine;

/**
 *
 * @author Administrator
 */
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Mouvement_Stock", schema = "stock")
@Audited
@AuditTable("Mouvement_Stock_AUD")
@Getter
@Setter
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

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

    @NotNull
    @Column(name = "Quantite")
    private BigDecimal quantite;

    @JoinColumn(name = "Code_Depot", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depot;

    @Column(name = "Code_Depot", updatable = false, insertable = false)
    private Integer codeDepotSource;

    @JoinColumn(name = "Code_depot_destination", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depotDestination;
    @Column(name = "Code_depot_destination", updatable = false, insertable = false)
    private Integer codeDepotDestination;

    @NotNull
    @Column(name = "Type_mouvement", length = 50)
    private String typeMouvement; // e.g., "DISPENSATION", "RECEPTION_ACHAT", "INVENTAIRE"

    @Column(name = "Code_origine")
    private Integer codeOrigine; // The code of the BonReception for traceability

}
