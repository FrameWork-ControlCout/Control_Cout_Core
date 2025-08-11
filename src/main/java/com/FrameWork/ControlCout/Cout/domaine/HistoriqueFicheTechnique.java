/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
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
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Historique_Fiche_Technique", schema = "cout")
@Audited
@AuditTable("Historique_Fiche_Technique_AUD")
@Getter
@Setter
public class HistoriqueFicheTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @JoinColumn(name = "Code_Fiche_technique", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private FicheTech ficheTechnique;

    @Column(name = "Code_Fiche_technique", updatable = false, insertable = false, nullable = false)
    private Integer codeFicheTechnique;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;
    
    @NotNull
    @Column(name = "Code_Saisie_BR", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String codeSaisieBR;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;
    @JoinColumn(name = "Code_Article", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @Column(name = "Code_Article", updatable = false, insertable = false, nullable = false)
    private Integer codeArticle;

    @Column(name = "Prix_Article_OLD", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixArticleOld;

    @Column(name = "Prix_Article_New", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixArticleNew;

    @Column(name = "Prix_Fiche_Tech_Old", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixFicheTechOld;

    @Column(name = "Prix_Fiche_Tech_New", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixFicheTechNew;

}
