/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Etat;
import com.FrameWork.ControlCout.Parametrage.dto.EtatDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatFactory {
    
     public static Etat createEtatFactureByCode(int code) {
        Etat domaine = new Etat();
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
    
    
   
    public static EtatDTO etatFactureToEtatFactureDTO(Etat domaine) {

        if (domaine != null) {
            EtatDTO dto = new EtatDTO();
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
    
    
    public static List<EtatDTO> listEtatFactureToEtatFactureDTOs(List<Etat> etatFactures) {
        List<EtatDTO> list = new ArrayList<>();
        for (Etat etatFacture : etatFactures) {
            list.add(etatFactureToEtatFactureDTO(etatFacture));
        }
        return list;
    }
}
