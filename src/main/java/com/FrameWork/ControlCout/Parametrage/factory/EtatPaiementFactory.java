/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatPaiement;
import com.FrameWork.ControlCout.Parametrage.dto.EtatPaiementDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatPaiementFactory {
    
     public static EtatPaiement createEtatPaiementFactureByCode(int code) {
        EtatPaiement domaine = new EtatPaiement();
        domaine.setCode(code);
        return domaine;
    } 
   
    public static EtatPaiementDTO etatPaiementToEtatPaiementDTO(EtatPaiement domaine) {

        if (domaine != null) {
            EtatPaiementDTO dto = new EtatPaiementDTO();
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
    
    
    public static List<EtatPaiementDTO> listEtatPaiementToEtatPaiementDTOs(List<EtatPaiement> etatPaiements) {
        List<EtatPaiementDTO> list = new ArrayList<>();
        for (EtatPaiement etatPaiement : etatPaiements) {
            list.add(etatPaiementToEtatPaiementDTO(etatPaiement));
        }
        return list;
    }
}
