/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
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
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Details_Consommation_Standard_PerDay", schema = "cout")
@Audited
@AuditTable("Details_Consommation_Standard_PerDay_AUD")
@Getter
@Setter
public class DetailsConsoStandardPerDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Details_Conso_Consommation", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private DetailsConsoStandard detailsConsoStandard;

    @Column(name = "Code_Details_Conso_Consommation", updatable = false, insertable = false, nullable = false)
    private Integer codeDetailsConsoConsommation;

    @JoinColumn(name = "Code_Conso_Standard", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ConsoStandard consoStandard;

    @Column(name = "Code_Conso_Standard", updatable = false, insertable = false, nullable = false)
    private Integer codeConsoStandardPerDay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Plan", nullable = false, columnDefinition = "datetime  ")
    private Date datePlan;

    @JoinColumn(name = "Code_Article", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @Column(name = "Code_Article", updatable = false, insertable = false, nullable = false)
    private Integer codeArticle;

    @JoinColumn(name = "Code_Socite", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Societe societe;

    @Column(name = "Code_Socite", updatable = false, insertable = false, nullable = false)
    private Integer codeSociete;

    @Column(name = "Nbre_Person", nullable = false)
    private Integer nbrePreson;

    @Column(name = "Qte_Besoin_Unitaire", columnDefinition = ("decimal(18,3)"))
    private BigDecimal consUni;
    @Column(name = "Qte_Besoin_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal consTotal;

    @JoinColumn(name = "Code_Unite_Secondaire", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite uniteSecondaire;

    @Column(name = "Code_Unite_Secondaire", updatable = false, insertable = false, nullable = false)
    private Integer codeUniteSecondaire;
    
      @Column(name = "Satisfait", nullable = false)
    private boolean satisfait;
      

}
