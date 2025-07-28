/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.dto;

import com.FrameWork.ControlCout.Stock.domaine.*;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class DepenseDTO {

    private Integer code;

    private String codeSaisie;
    private DepotDTO depotSourceDTO;

    private Integer codeDepotSource;
    private DepotDTO depotDestinationDTO;

    private Integer codeDepotDestination;

    private ConsoStandardDTO consoStandardDTO;

    private Integer codeConsoStandard;

    private List<DetailsDepenseDTO> detailsDepensesDTOs;
     private String userCreate;

    private Date dateCreate;


}
