/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class TypeCaisseDTO {

    private Integer code;

    private String designationAr;

    private String designationLt;
        private String type;

    public TypeCaisseDTO() {
    }

    
}
