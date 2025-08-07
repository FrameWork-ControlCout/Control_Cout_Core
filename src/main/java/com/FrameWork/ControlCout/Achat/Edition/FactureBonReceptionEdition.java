/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.Edition;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class FactureBonReceptionEdition {
   private String codeSaisie;
     private String  designationAr;
     private String  designationArUnite;
     private BigDecimal  qteBesoin;
    private BigDecimal   qteReception;
     private BigDecimal     prixUniAchat;
     private BigDecimal  prixTotalAchat;
    
     private BigDecimal  prixUniGros;
     private BigDecimal  prixTotalGros;
    
    private BigDecimal   diffPrixUni;
    private BigDecimal   diffPrixTotal;
    private String designationArFournisseur;
}
