/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;
 
import java.util.Date;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class EtatPaiementDTO {

    private Integer code;

    private String codeSaisie;

    private String designationAr;

    private String designationLt;
 

    private String userCreate;

    private Date dateCreate; 

    public EtatPaiementDTO() {
    }
 
    
}
