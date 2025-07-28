/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.dto;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class FactureGrosDTO {

    private Integer code;
    private String codeSaisie;
 

    private String usercreate;

    private Date dateCreate;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    private BigDecimal prixTotal;
       private OrderAchatDTO orderAchatDTO;
     private Integer codeOrderAchat;

      private List<DetailsFactureGrosDTO> detailsFactureGrosesDTOs;
    public FactureGrosDTO() {
    }

}
