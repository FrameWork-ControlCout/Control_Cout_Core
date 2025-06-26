package com.FrameWork.ControlCout.Parametrage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CostProfitCentreDTO {

    private Integer code;

    @NotNull
    @Size(
            min = 1,
            max = 20
    )
    private String codeSaisie;

    @NotNull
    @Size(
            min = 1,
            max = 200
    )
    private String designationAr;

    @NotNull
    @Size(
            min = 1,
            max = 200
    )
    private String designationLt;

    @NotNull
    private boolean profitCentre;

    @NotNull
    private boolean detail;

    @NotNull
    private boolean actif;
//    @NotNull
//    private Integer niveau;
 
//    private CostProfitCentreDTO parent;

    private TypeCostCentreDTO typeCostCentreDTO;
    private Integer codeTypeCostCentre;

    private boolean base;
    private Date dateAction;

    private String userCreate;
    
//     private Integer classement;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeSaisie() {
        return codeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        this.codeSaisie = codeSaisie;
    }

    public String getDesignationAr() {
        return designationAr;
    }

    public void setDesignationAr(String designationAr) {
        this.designationAr = designationAr;
    }

    public boolean getProfitCentre() {
        return profitCentre;
    }

    public void setProfitCentre(boolean profitCentre) {
        this.profitCentre = profitCentre;
    }

    public boolean getDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public TypeCostCentreDTO getTypeCostCentreDTO() {
        return typeCostCentreDTO;
    }

    public void setTypeCostCentreDTO(TypeCostCentreDTO typeCostCentreDTO) {
        this.typeCostCentreDTO = typeCostCentreDTO;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public String getDesignationLt() {
        return designationLt;
    }

    public void setDesignationLt(String designationLt) {
        this.designationLt = designationLt;
    }

    public Integer getCodeTypeCostCentre() {
        return codeTypeCostCentre;
    }

    public void setCodeTypeCostCentre(Integer codeTypeCostCentre) {
        this.codeTypeCostCentre = codeTypeCostCentre;
    }

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean base) {
        this.base = base;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

 
//    public CostProfitCentreDTO getParent() {
//        return parent;
//    }
//
//    public void setParent(CostProfitCentreDTO parent) {
//        this.parent = parent;
//    }
//
//    public Integer getNiveau() {
//        return niveau;
//    }
//
//    public void setNiveau(Integer niveau) {
//        this.niveau = niveau;
//    }

//    public Integer getClassement() {
//        return classement;
//    }
//
//    public void setClassement(Integer classement) {
//        this.classement = classement;
//    }

    
}
