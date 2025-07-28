/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.domaine;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Depense", schema = "stock")
@Audited
@AuditTable("Depense_AUD")
@Getter
@Setter
public class Depense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @NotNull
    @Column(name = "Code_Saisie", unique = true, length = 200)
    private String codeSaisie;

    @JoinColumn(name = "Depot_Source", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depotSource;

    @Column(name = "Depot_Source", updatable = false, insertable = false)
    private Integer codeDepotSource;

    @JoinColumn(name = "Depot_Destination", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Depot depotDestination;

    @Column(name = "Depot_Destination", updatable = false, insertable = false)
    private Integer codeDepotDestination;

    @JoinColumn(name = "Code_Conso_Standard", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private ConsoStandard consoStandard;

    @Column(name = "Code_Conso_Standard", updatable = false, insertable = false)
    private Integer codeConsoStandard;

    @OneToMany(mappedBy = "codeDepense", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listDepense")  
    private List<DetailsDepense>  detailsDepenses;

      @NotNull
    @Column(name = "User_Create", nullable = false, length = 255, columnDefinition = "nvarchar(200)")
    private String userCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    private Date dateCreate;

}
