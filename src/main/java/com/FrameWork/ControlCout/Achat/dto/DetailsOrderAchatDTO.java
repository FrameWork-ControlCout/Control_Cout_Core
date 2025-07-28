/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.EtatReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class DetailsOrderAchatDTO {

    private Integer code;

    private OrderAchatDTO orderAchatDTO;

    private Integer codeOrderAchat;

    private ArticleDTO articleDTO;

    private Integer codeArticle;

    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private String usercreate;

    private String caracterstique;

    private Date dateCreate;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;
    private BigDecimal qteBesoin;
    private boolean consoStandard;
    private boolean satisfait;
    private EtatReceptionDTO etatReceptionDTO;

    private Integer codeEtatReception;

     private BigDecimal qteDejaReceptionner;
     
    public DetailsOrderAchatDTO() {
    }
 
}
