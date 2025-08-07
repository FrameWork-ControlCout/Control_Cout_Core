/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class FicheTechDTO {

    private Integer code;

    private String codeSaisie;
    private String designationAr;

    private String userCreate;

    private Date dateCreate;

    private BigDecimal prixTotal;

    private List<DetailsFicheTechDTO> detailsFicheTechniquesCardDTOs;
    private BigDecimal autreCout;
    private boolean actif;
    private BigDecimal coutUnitaire;

        private BigDecimal pourcentAutreAcharge;
          private Integer nbrePersRef;
        
    public FicheTechDTO() {
    }

}
