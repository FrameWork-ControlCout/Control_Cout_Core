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
public class DepenseEdition {

    private String codeSaisie;
    private String depotSource;
    private String depotDestination;
    private String designationArArticle;
    private String codeSaisieArticle;
    private String unite;
    private BigDecimal qteDispenser;
    private String userCreate;

}
