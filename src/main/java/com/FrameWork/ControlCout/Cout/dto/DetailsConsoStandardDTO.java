/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
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
public class DetailsConsoStandardDTO {

    private Integer code;
    private ConsoStandardDTO consoStandardDTO;

    private Integer codeConsoStandard;

    private String userCreate;

    private Date dateCreate;

    private Integer nbrePerson;

    private ArticleDTO articleDTO;

    private Integer codeArticle;

    private UniteDTO uniteSecondaireDTO;

    private Integer codeUniteSecondaire;

    private UniteDTO uniteConsoDTO;

    private Integer codeUniteConso;

 

    private SocieteDTO societeDTO;

    private Integer codeSociete;

    private BigDecimal qteDispensee;

    private boolean satisfait;
   private boolean haveOA;
   
//    private PlanRepaDTO planRepaDTO;
//
//     private Integer codePlanRepa;
    public DetailsConsoStandardDTO() {
    }
 

}
