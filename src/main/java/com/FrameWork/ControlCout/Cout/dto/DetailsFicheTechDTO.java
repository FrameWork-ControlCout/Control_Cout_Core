/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
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
public class DetailsFicheTechDTO {

    private Integer code;

    private FicheTechDTO ficheTechDTO;

    private Integer codeFicheTechnique;

    private String userCreate;

    private Date dateCreate;

    private ArticleDTO articleDTO;

    private Integer codeArticle;
    
    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private BigDecimal consUni;
    
    private BigDecimal consTotal;

    private BigDecimal prixUni;

    private BigDecimal prixTotal;
    
        private UniteDTO uniteSecondaireDTO;

     private Integer codeUniteSecondaire;

         private String modifPrice;
         
    public DetailsFicheTechDTO() {
    }
 
    
}
