/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class DispenseDetailDTO {
    private Integer codeDetailsConsoStandard; // The primary key of the detail line
    private BigDecimal qteADispenser;       // The quantity to dispense in this transaction
private Boolean isSatisfait;
    // Getters and Setters
    public Integer getCodeDetailsConsoStandard() {
        return codeDetailsConsoStandard;
    }

    public void setCodeDetailsConsoStandard(Integer codeDetailsConsoStandard) {
        this.codeDetailsConsoStandard = codeDetailsConsoStandard;
    }

    public BigDecimal getQteADispenser() {
        return qteADispenser;
    }

    public void setQteADispenser(BigDecimal qteADispenser) {
        this.qteADispenser = qteADispenser;
    }

    public Boolean getIsSatisfait() {
        return isSatisfait;
    }

    public void setIsSatisfait(Boolean isSatisfait) {
        this.isSatisfait = isSatisfait;
    }

}

