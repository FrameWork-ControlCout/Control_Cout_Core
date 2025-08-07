/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
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
@Table(name = "Details_Fiche_Technique", schema = "cout")
@Audited
@AuditTable("Details_Fiche_Technique_AUD")
@Getter
@Setter
public class DetailsFicheTech {

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate; 

 

    @JoinColumn(name = "Code_Article", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @Column(name = "Code_Article", updatable = false, insertable = false, nullable = false)
    private Integer codeArticle;

    @JoinColumn(name = "Code_Unite_Conso", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite unite;

    @Column(name = "Code_Unite_Conso", updatable = false, insertable = false, nullable = false)
    private Integer codeUnite;
 
    
        @JoinColumn(name = "Code_Unite_Secondaire", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Unite uniteSecondaire;

    @Column(name = "Code_Unite_Secondaire", updatable = false, insertable = false, nullable = false)
    private Integer codeUniteSecondaire;
    
    
   @Column(name = "Qte_Besoin_Unitaire" )
    private BigDecimal consUni;
    @Column(name = "Qte_Besoin_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal consTotal;

    @Column(name = "Last_Prix_Achat", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixUni;

    @Column(name = "Montant_Total_TTC", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotal;
 
 @Column(name = "Modif_Price" , length = 3, columnDefinition = "nvarchar(3)")
    private String modifPrice;
    
    public DetailsFicheTech() {
    }
 
    
    
}
