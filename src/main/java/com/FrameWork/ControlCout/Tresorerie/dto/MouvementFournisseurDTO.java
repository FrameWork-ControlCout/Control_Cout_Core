/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;

import com.FrameWork.ControlCout.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
 
@Getter
@Setter
public class MouvementFournisseurDTO {

     private Integer code;

     private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;
     private String userCreate;

     private Date dateCreate;

     private Integer codeFactureBonReception;

     private BigDecimal prixFacture;

    private BigDecimal deductionFacture;
    
   private EtatPaiementDTO etatPaiementDTO;

     private Integer codeEtatPaiement;
    
         private Integer codeOrderPaiement;
    
     private String typeMouvement;
    
    

}
