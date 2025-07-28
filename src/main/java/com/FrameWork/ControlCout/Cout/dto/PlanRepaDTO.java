/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
import com.FrameWork.ControlCout.Parametrage.dto.TypeRepaDTO;
import jakarta.persistence.Column;
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class PlanRepaDTO {

    private Integer code;

    private Date datePlan;

    private String userCreate;

    private Date dateCreate;
    private TypeRepaDTO typeRepaDTO;

    private Integer codeTypeRepa;

    private FicheTechDTO ficheTechDTO;

    private Integer codeFicheTechnique;

    private SocieteDTO societeDTO;

    private Integer codeSociete;
    private boolean traiter;
    private Integer nbrePerson;

    public PlanRepaDTO() {
    }

}
