/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement;
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ModeReglementDTO;
import com.FrameWork.ControlCout.Tresorerie.domaine.Caisse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class AvanceFournisseurDTO {

    private Integer code;

    private String codeSaisie;

    private FournisseurDTO fournisseurDTO;

    private Integer codeFournisseur;

    private String userCreate;
    private Date dateCreate;

    private BigDecimal mntAvance;

    private boolean appurer;

    private CaisseDTO caisseDTO;

    private Integer codeCaisse;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;
 
    private BigDecimal montantEnDevise;

    private BigDecimal tauxChange;

}
