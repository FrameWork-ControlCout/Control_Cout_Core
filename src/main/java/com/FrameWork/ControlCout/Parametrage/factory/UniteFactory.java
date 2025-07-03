/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class UniteFactory {
    
     public static Unite createUniteByCode(int code) {
        Unite domaine = new Unite();
        domaine.setCode(code);
        return domaine;
    }
      public static Unite uniteDTOToUnite(UniteDTO dto, Unite domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());   
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());   

                     domaine.setSecondaire(dto.isSecondaire());   


            return domaine;
        } else {
            return null;
        }
    }
    
    
   
    public static UniteDTO uniteToUniteDTO(Unite domaine) {

        if (domaine != null) {
            UniteDTO dto = new UniteDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr()); 
            dto.setDesignationArUnite(domaine.getDesignationAr());

            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());  
             dto.setSecondaire(domaine.isSecondaire());  



            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<UniteDTO> listUniteToUniteDTOs(List<Unite> unites) {
        List<UniteDTO> list = new ArrayList<>();
        for (Unite unite : unites) {
            list.add(uniteToUniteDTO(unite));
        }
        return list;
    }
}
