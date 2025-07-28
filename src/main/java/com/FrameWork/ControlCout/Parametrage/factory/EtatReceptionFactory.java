/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatReception;
import com.FrameWork.ControlCout.Parametrage.dto.EtatReceptionDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatReceptionFactory {
    
     public static EtatReception createEtapReceptionByCode(int code) {
        EtatReception domaine = new EtatReception();
        domaine.setCode(code);
        return domaine;
    } 
   
    public static EtatReceptionDTO etapReceptionToEtapReceptionDTO(EtatReception domaine) {

        if (domaine != null) {
            EtatReceptionDTO dto = new EtatReceptionDTO();
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
    
    
    public static List<EtatReceptionDTO> listEtapReceptionToEtapReceptionDTOs(List<EtatReception> etapReceptions) {
        List<EtatReceptionDTO> list = new ArrayList<>();
        for (EtatReception etapReception : etapReceptions) {
            list.add(etapReceptionToEtapReceptionDTO(etapReception));
        }
        return list;
    }
}
