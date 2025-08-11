/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
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
public class DetailsConsoStandardPerDayDTO {

      private Integer code;
      
     private DetailsConsoStandardDTO detailsConsoStandardDTO;

     private Integer codeDetailsConsoConsommation;

     private Date datePlan;

     private ArticleDTO articleDTO;

     private Integer codeArticle;
     private BigDecimal consUni;
     private BigDecimal consTotal;
     
       private ConsoStandardDTO consoStandardDTO;

     private Integer codeConsoStandard;
     
     
       private SocieteDTO societeDTO;

     private Integer codeSociete;
    
     private Integer nbrePreson;
     
       private UniteDTO uniteSecondaireDTO;
    private Integer codeUniteSecondaire;
      @Column(name = "Satisfait", nullable = false)
    private boolean satisfait;

}
