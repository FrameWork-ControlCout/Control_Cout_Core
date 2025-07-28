/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class DispensationRequestDTO {
      private Integer codeConsoStandard;
    private Integer codeDepotSource;
    private Integer codeDepotDestination;
    private List<DispenseDetailDTO> details;

    // Getters and Setters
    public Integer getCodeConsoStandard() {
        return codeConsoStandard;
    }

    public void setCodeConsoStandard(Integer codeConsoStandard) {
        this.codeConsoStandard = codeConsoStandard;
    }

    public Integer getCodeDepotSource() {
        return codeDepotSource;
    }

    public void setCodeDepotSource(Integer codeDepotSource) {
        this.codeDepotSource = codeDepotSource;
    }

    public Integer getCodeDepotDestination() {
        return codeDepotDestination;
    }

    public void setCodeDepotDestination(Integer codeDepotDestination) {
        this.codeDepotDestination = codeDepotDestination;
    }

    public List<DispenseDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DispenseDetailDTO> details) {
        this.details = details;
    }
}
