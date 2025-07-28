/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class ArticleDTO {

    private Integer code;

    private String codeSaisie;
    private String codeSaisieArticle;

    private String designationAr;
    private String designationArArticle;

    private String designationLt;
    private String designationLtArticle;

    private boolean actif;

    private String userCreate;

    private Date dateCreate;
    private String type;

    private FamilleArticleDTO familleArticleDTO;

    private Integer codeFamille;

    private UniteDTO uniteDTO;
    private Integer codeUnite;

    private Integer packages;

    private BigDecimal lastPrixAchat;

    private UniteDTO uniteSecondaireDTO;

    private Integer codeUniteSecondaire;

    private BigDecimal conversionRate;

    private UniteDTO uniteDepenseDTO;
    private Integer codeUniteDepense;
    private BigDecimal lastPrixGros;
    public ArticleDTO() {
    }
 
}
