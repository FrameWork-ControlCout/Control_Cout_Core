/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.domaine;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Facture_Gros", schema = "param_achat")
@Audited
@AuditTable("Facture_Gros_AUD")
@Getter
@Setter
public class FactureGros {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private Integer code;

    @Size(max = 200)
    @NotNull
    @Column(name = "Code_Saisie", length = 200)
    private String codeSaisie;

    @Column(name = "User_Create", nullable = false, columnDefinition = "nvarchar(200)")
    private String usercreate;

    @Basic(optional = false)
    @Column(name = "Date_Create", nullable = false, columnDefinition = "datetime default (getdate())")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @JoinColumn(name = "Code_Fournisseur", referencedColumnName = "Code", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private Fournisseur fournisseur;

    @Column(name = "Code_Fournisseur", updatable = false, insertable = false, nullable = false)
    private Integer codeFournisseur;

    @JoinColumn(name = "Code_Order_Achat", referencedColumnName = "Code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference
    private OrderAchat orderAchat;

    @Column(name = "Code_Order_Achat", updatable = false, insertable = false)
    private Integer codeOrderAchat;

    @Column(name = "Prix_Total", nullable = false, columnDefinition = ("decimal(18,3)"))
    private BigDecimal prixTotal;

    @OneToMany(mappedBy = "codeFactureGros", cascade = {jakarta.persistence.CascadeType.MERGE, jakarta.persistence.CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonBackReference("listFactureGros") // Unique name
    private List<DetailsFactureGros> detailsFactureGroses;

    public FactureGros() {
    }

}
