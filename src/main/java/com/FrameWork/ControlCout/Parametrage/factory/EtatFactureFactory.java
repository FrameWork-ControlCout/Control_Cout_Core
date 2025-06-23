/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatFacture;
import com.FrameWork.ControlCout.Parametrage.dto.EtatFactureDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatFactureFactory {
    
     public static EtatFacture createEtatFactureByCode(int code) {
        EtatFacture domaine = new EtatFacture();
        domaine.setCode(code);
        return domaine;
    }
//      public static EtatFacture etatFactureDTOToEtatFacture(EtatFactureDTO dto, EtatFacture domaine) {
//        if (dto != null) {
//            domaine.setCode(dto.getCode());       
//            domaine.setCodeSaisie(dto.getCodeSaisie());   
//            domaine.setDesignationLt(dto.getDesignationLt());
//            domaine.setDesignationAr(dto.getDesignationAr());   
//            return domaine;
//        } else {
//            return null;
//        }
//    }
    
    
   
    public static EtatFactureDTO etatFactureToEtatFactureDTO(EtatFacture domaine) {

        if (domaine != null) {
            EtatFactureDTO dto = new EtatFactureDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());  
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());  
 


            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<EtatFactureDTO> listEtatFactureToEtatFactureDTOs(List<EtatFacture> etatFactures) {
        List<EtatFactureDTO> list = new ArrayList<>();
        for (EtatFacture etatFacture : etatFactures) {
            list.add(etatFactureToEtatFactureDTO(etatFacture));
        }
        return list;
    }
}
