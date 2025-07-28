/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.Edition;

import java.math.BigDecimal;
import lombok.*;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class SoldeDepotEdition {

    private String codeSaisieDepot;
    private String designationArArticle;
    private String designationArDepot;
    private String designationArUnite;
    private BigDecimal qteDisponible;
    private String codeSaisieArticle;

}
