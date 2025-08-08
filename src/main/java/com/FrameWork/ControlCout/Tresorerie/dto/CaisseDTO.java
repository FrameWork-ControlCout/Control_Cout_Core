/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;
 
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO; 
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class CaisseDTO {

    private Integer code;

    private String codeSaisie;
    private String designationAr;

    private String designationLt;
    private boolean actif;

    private String userCreate;

    private Date dateCreate;

    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private TypeCaisseDTO typeCaisseDTO;

    private Integer codeTypeCaisse;

        private String type;
        
    public CaisseDTO() {
    }

    
}
