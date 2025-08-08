/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.dto;
 
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ModeReglementDTO;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Nullable;

/**
 *
 * @author Administrator
 */
public class MouvementCaisseDTO {

    private Integer code;

    private CaisseDTO caisseDTO;

    private Integer codeCaisse; 
    private Integer codeCaisseTr;   
    
 
    private String designationCaisse;


    private DeviseDTO deviseDTO;

    private Integer codeDevise;

    private String userCreate;

    private Date dateCreate;

    private BigDecimal debit;

    private BigDecimal credit;

    private BigDecimal mntDevise;

//    private Integer codeCaisseTr;

    private String codeSaisie;

    private String codeTier;

    private ModeReglementDTO modeReglementDTO;

    private Integer codeModeReglement;

    public MouvementCaisseDTO() {
    }

    public MouvementCaisseDTO(Integer codeCaisse, BigDecimal debit, BigDecimal credit) {
        this.codeCaisse = codeCaisse;
        this.debit = debit;
        this.credit = credit;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CaisseDTO getCaisseDTO() {
        return caisseDTO;
    }

    public void setCaisseDTO(CaisseDTO caisseDTO) {
        this.caisseDTO = caisseDTO;
    }

    public Integer getCodeCaisse() {
        return codeCaisse;
    }

    public void setCodeCaisse(Integer codeCaisse) {
        this.codeCaisse = codeCaisse;
    }

    public DeviseDTO getDeviseDTO() {
        return deviseDTO;
    }

    public void setDeviseDTO(DeviseDTO deviseDTO) {
        this.deviseDTO = deviseDTO;
    }

    public Integer getCodeDevise() {
        return codeDevise;
    }

    public void setCodeDevise(Integer codeDevise) {
        this.codeDevise = codeDevise;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getMntDevise() {
        return mntDevise;
    }

    public void setMntDevise(BigDecimal mntDevise) {
        this.mntDevise = mntDevise;
    }

    public Integer getCodeCaisseTr() {
        return codeCaisseTr;
    }

    public void setCodeCaisseTr(Integer codeCaisseTr) {
        this.codeCaisseTr = codeCaisseTr;
    }

    public String getCodeSaisie() {
        return codeSaisie;
    }

    public void setCodeSaisie(String codeSaisie) {
        this.codeSaisie = codeSaisie;
    }

    public String getCodeTier() {
        return codeTier;
    }

    public void setCodeTier(String codeTier) {
        this.codeTier = codeTier;
    }

    public ModeReglementDTO getModeReglementDTO() {
        return modeReglementDTO;
    }

    public void setModeReglementDTO(ModeReglementDTO modeReglementDTO) {
        this.modeReglementDTO = modeReglementDTO;
    }

    public Integer getCodeModeReglement() {
        return codeModeReglement;
    }

    public void setCodeModeReglement(Integer codeModeReglement) {
        this.codeModeReglement = codeModeReglement;
    }

   
    public String getDesignationCaisse() {
        return designationCaisse;
    }

    public void setDesignationCaisse(String designationCaisse) {
        this.designationCaisse = designationCaisse;
    }

 

}
