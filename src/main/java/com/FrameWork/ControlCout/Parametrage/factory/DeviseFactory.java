/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DeviseFactory {
    public static Devise createDeviseByCode(int code) {
        Devise domaine = new Devise();
        domaine.setCode(code);
        return domaine;
    }

    public static Devise deviseDTOToDevise(DeviseDTO dto, Devise domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());   
            domaine.setHasTaux(dto.isHasTaux()); 
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
         

            return domaine;
        } else {
            return null;
        }
    }
    
    
     public static Devise deviseDTOToDeviseHasTaux(DeviseDTO dto, Devise domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());        
            domaine.setHasTaux(dto.isHasTaux()); 
            domaine.setTauxChange(dto.getTauxChange());    
  
            return domaine;
        } else {
            return null;
        }
    }

    public static DeviseDTO deviseToDeviseDTO(Devise domaine) {

        if (domaine != null) {
            DeviseDTO dto = new DeviseDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie()); 
            dto.setHasTaux(domaine.isHasTaux()); 
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());   
            dto.setTauxChange(domaine.getTauxChange());


            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<DeviseDTO> listDeviseToDeviseDTOs(List<Devise> devises) {
        List<DeviseDTO> list = new ArrayList<>();
        for (Devise devise : devises) {
            list.add(deviseToDeviseDTO(devise));
        }
        return list;
    }
}
