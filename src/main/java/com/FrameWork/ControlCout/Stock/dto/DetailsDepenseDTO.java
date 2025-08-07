/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.dto;

import com.FrameWork.ControlCout.Stock.domaine.*;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Administrator
 */
@Getter
@Setter
public class DetailsDepenseDTO {

    private Integer code;

    private DepenseDTO depenseDTO;

    private Integer codeDepense;

    private DepotDTO depotSourceDTO;

    private Integer codeDepotSource;

    private DepotDTO depotDestinationDTO;

    private Integer codeDepotDestination;

    private BigDecimal qteDispenser;
    private UniteDTO uniteDTO;

    private Integer codeUnite;

    private ArticleDTO articleDTO;

    private Integer codeArticle;

    private String userCreate;
    private Date dateCreate;
    
        private BigDecimal qteADispenser;
        
                private BigDecimal qteDemander;

        
          private Integer codeDetailsConsoStandard;
          
  private boolean consStandard;
    public DetailsDepenseDTO() {
    }

}
