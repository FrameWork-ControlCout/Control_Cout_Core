/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.dto;

/**
 *
 * @author Administrator
 */
public class CompositionUniteDTO {
     private Integer code;

     private Integer nmbreUnite;

     private Integer codeUnitePrinc;

     private UniteDTO unitePrincDTO;

     private Integer codeUniteSec;

     private UniteDTO uniteSecDTO;

    public CompositionUniteDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getNmbreUnite() {
        return nmbreUnite;
    }

    public void setNmbreUnite(Integer nmbreUnite) {
        this.nmbreUnite = nmbreUnite;
    }

    public Integer getCodeUnitePrinc() {
        return codeUnitePrinc;
    }

    public void setCodeUnitePrinc(Integer codeUnitePrinc) {
        this.codeUnitePrinc = codeUnitePrinc;
    }

 
    public Integer getCodeUniteSec() {
        return codeUniteSec;
    }

    public void setCodeUniteSec(Integer codeUniteSec) {
        this.codeUniteSec = codeUniteSec;
    }

    public UniteDTO getUnitePrincDTO() {
        return unitePrincDTO;
    }

    public void setUnitePrincDTO(UniteDTO unitePrincDTO) {
        this.unitePrincDTO = unitePrincDTO;
    }

    public UniteDTO getUniteSecDTO() {
        return uniteSecDTO;
    }

    public void setUniteSecDTO(UniteDTO uniteSecDTO) {
        this.uniteSecDTO = uniteSecDTO;
    }

   
    
}
