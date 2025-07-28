/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;
 
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
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
public class DetailsFactureBonReceptionDTO {

    private Integer code;

    private FactureBonReceptionDTO factureBonReceptionDTO;

    private Integer codeFactureBonReception;

    private BigDecimal qteBesoin;

    private BigDecimal qteReceptionner;

    private DepotDTO depotDTO;

    private Integer codeDepot;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    private DetailsOrderAchatDTO detailsOrderAchatDTO;

    private Integer codeDetailsOrderAchat;
    private String userCreate;

    private Date dateCreate;

    private ArticleDTO articleDTO;

    private Integer codeArticle;

    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private BigDecimal prixUniAchat;

    private BigDecimal prixTotalAchat;

    private BigDecimal prixUniGros;
    private BigDecimal prixTotalGros;

    private BigDecimal diffPrixUni;
    private BigDecimal diffPrixTotal;

    public DetailsFactureBonReceptionDTO() {
    }

}
