/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.*;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
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
public class ConsoStandardDTO {

    private Integer code;
    private Date dateDebut;
    private String codeSaisie;
    private Date dateFin;

    private Integer nbrePerson;

    private List<DetailsConsoStandardDTO> detailsConsoStandardsDTOs;    
    private List<DetailsConsoStandardPerDayDTO> detailsConsoStandardPerDayDTOs;


    private boolean actif;

    private String userCreate;

    private Date dateCreate;
    private boolean satisfait;
    
        private SocieteDTO societeDTO;

     private Integer codeSociete;
    

    public ConsoStandardDTO() {
    } 
}
