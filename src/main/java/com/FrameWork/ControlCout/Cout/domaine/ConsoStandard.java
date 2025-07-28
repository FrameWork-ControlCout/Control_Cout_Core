/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.domaine;

import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Consommation_Standard", schema = "cout")
@Audited
@AuditTable("Consommation_Standard_AUD")
@Getter
@Setter
public class ConsoStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Debut", nullable = false, columnDefinition = "datetime  ")
    private Date dateDebut;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Fin", nullable = false, columnDefinition = "datetime  ")
    private Date dateFin;

    @NotNull
    @Column(name = "Nbre_Person", nullable = false)
    private Integer nbrePerson;

    @OneToMany(mappedBy = "codeConsoStandard", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listConsoStandard") // Unique name
    private List<DetailsConsoStandard> detailsConsoStandards;

    
      @OneToMany(mappedBy = "codeConsoStandardPerDay", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listConsoStandardPerDay")  
    private List<DetailsConsoStandardPerDay> detailsConsoStandardPerDays;
      
      
    @Column(name = "Actif", nullable = false)
    private boolean actif;
    
      @Column(name = "Satisfait", nullable = false)
    private boolean satisfait;

    @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;
    
        @JoinColumn(name = "Code_Societe", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Societe societe;

    @Column(name = "Code_Societe", updatable = false, insertable = false, nullable = false)
    private Integer codeSociete;
    

    public ConsoStandard() {
    }
 

    
}
