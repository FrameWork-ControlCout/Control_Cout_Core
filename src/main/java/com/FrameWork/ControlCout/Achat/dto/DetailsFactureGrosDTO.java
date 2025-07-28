/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Administrator
 */
 
@Getter
@Setter
public class DetailsFactureGrosDTO {

   private Integer code;

    private FactureGrosDTO factureGrosDTO;

     private Integer codeFactureGros;

    private String caracterstique;
      
    private FournisseurDTO fournisseurDTO;

     private Integer codeFournisseur;

     private String userCreate;

     private Date dateCreate;

    private ArticleDTO articleDTO;

     private Integer codeArticle;

     private UniteDTO uniteDTO;

     private Integer codeUnite;

     private BigDecimal prixUni;

    public DetailsFactureGrosDTO() {
    }

}
