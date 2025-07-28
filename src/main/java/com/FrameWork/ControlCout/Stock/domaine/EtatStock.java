/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.domaine;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
 
@Entity
@Table(name = "Etat_Stock", schema = "stock")
@Audited
@AuditTable("Etat_Stock_AUD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtatStock {
      @EmbeddedId
    private EtatStockPK id;

    @Column(name = "quantite_disponible", nullable = false)
    private BigDecimal quantiteDisponible;
}
