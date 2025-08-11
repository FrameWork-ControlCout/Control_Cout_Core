/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.dto;

import com.FrameWork.ControlCout.Cout.domaine.*;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
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
public class HistoriqueFicheTechniqueDTO {

    private Integer code;
    private FicheTechDTO ficheTechniqueDTO;

    private Integer codeFicheTechnique;

    private String userCreate;

    private Date dateCreate;
    private ArticleDTO articleDTO;

    private Integer codeArticle;

    private BigDecimal prixArticleOld;

    private BigDecimal prixArticleNew;

    private BigDecimal prixFicheTechOld;

    private BigDecimal prixFicheTechNew;
        private String codeSaisieBR;

}
