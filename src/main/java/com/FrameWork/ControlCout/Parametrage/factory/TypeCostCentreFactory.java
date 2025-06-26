/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.TypeCostCentre;
import com.FrameWork.ControlCout.Parametrage.dto.TypeCostCentreDTO;
import com.FrameWork.ControlCout.web.Util.Helper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrateur
 */
@Component
public class TypeCostCentreFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static TypeCostCentre TypeCostCentreDTOTOTypeCostCentre(TypeCostCentreDTO typeCostCentreDTO, TypeCostCentre typeCostCentre) {
        typeCostCentre.setCode(typeCostCentreDTO.getCode());
        typeCostCentre.setActif(typeCostCentreDTO.getActif()); 
        typeCostCentre.setCodeSaisie(typeCostCentreDTO.getCodeSaisie());


        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
            typeCostCentre.setDesignationAr(typeCostCentreDTO.getDesignationLt());
            typeCostCentre.setDesignationLt(typeCostCentreDTO.getDesignationAr());
        } else {
            typeCostCentre.setDesignationLt(typeCostCentreDTO.getDesignationLt());
            typeCostCentre.setDesignationAr(typeCostCentreDTO.getDesignationAr());
        }
 

        return typeCostCentre;
    }

    public static TypeCostCentreDTO TypeCostCentreTOTypeCostCentreDTO(TypeCostCentre typeCostCentre) {
        TypeCostCentreDTO typeCostCentreDTO = new TypeCostCentreDTO();
        typeCostCentreDTO.setCode(typeCostCentre.getCode());
        typeCostCentreDTO.setActif(typeCostCentre.isActif());
        typeCostCentreDTO.setUserCreate(typeCostCentre.getUserCreate());
        typeCostCentreDTO.setDateCreate(typeCostCentre.getDateCreate()); 
        typeCostCentreDTO.setCodeSaisie(typeCostCentre.getCodeSaisie());


        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
            typeCostCentreDTO.setDesignationAr(typeCostCentre.getDesignationLt());
            typeCostCentreDTO.setDesignationLt(typeCostCentre.getDesignationAr());
        } else {
            typeCostCentreDTO.setDesignationLt(typeCostCentre.getDesignationLt());
            typeCostCentreDTO.setDesignationAr(typeCostCentre.getDesignationAr());
        }
        return typeCostCentreDTO;
    }

    public static Collection<TypeCostCentreDTO> TypeCostCentresTOTypeCostCentreDTOs(Collection<TypeCostCentre> typeCostCentres) {
        List<TypeCostCentreDTO> typeCostCentreDTOs = new ArrayList<>();
        typeCostCentres.forEach(x -> {
            typeCostCentreDTOs.add(TypeCostCentreTOTypeCostCentreDTO(x));
        });
        return typeCostCentreDTOs;
    }

    public static TypeCostCentre createTypeCostCentreByCode(Integer code) {
        if (code == null) {
            return null;
        }
        TypeCostCentre typeCostCentre = new TypeCostCentre();
        typeCostCentre.setCode(code);

        return typeCostCentre;
    }

}
