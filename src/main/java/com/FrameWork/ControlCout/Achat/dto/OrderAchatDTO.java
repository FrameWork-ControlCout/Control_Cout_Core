/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatReception;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import com.FrameWork.ControlCout.Parametrage.dto.EtatDTO;
import com.FrameWork.ControlCout.Parametrage.dto.EtatReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import jakarta.persistence.Column;
import java.util.Date;
import java.util.List;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class OrderAchatDTO {

    private Integer code;

    private String codeSaisie;

    private String userCreate;
    private Date dateCreate;

    private EtatDTO etatFactureDTO;

    private Integer codeEtatFacture;

    private String userApprove;
    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    private List<DetailsOrderAchatDTO> detailsOrderAchatDTOs;

    private String codeConsoStandard;

    private DepotDTO depotDTO;

    private Integer codeDepot;

    private String fournisseurDesignationAr;

    private String depotDesignationAr;

    private EtatReceptionDTO etatReceptionDTO;

    private Integer codeEtatReception;

    public OrderAchatDTO() {
    }

}
