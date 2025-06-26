/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.TauxDeChange;
import com.FrameWork.ControlCout.Parametrage.dto.TauxDeChangeDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TauxDeChangeFactory {

    public static TauxDeChange createTauxDeChangeByCode(int code) {
        TauxDeChange domaine = new TauxDeChange();
        domaine.setCode(code);
        return domaine;
    }

    public static TauxDeChange tauxDeChangeDTOToTauxDeChange(TauxDeChangeDTO dto, TauxDeChange domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setTauxChange(dto.getTauxChange()); 
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static TauxDeChangeDTO tauxDeChangeToTauxDeChangeDTO(TauxDeChange domaine) {

        if (domaine != null) {
            TauxDeChangeDTO dto = new TauxDeChangeDTO();
            dto.setCode(domaine.getCode());   
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setTauxChange(domaine.getTauxChange()); 
            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            return dto;
        } else {
            return null;
        }
    }

    public static List<TauxDeChangeDTO> listTauxDeChangeToTauxDeChangeDTOs(List<TauxDeChange> tauxDeChanges) {
        List<TauxDeChangeDTO> list = new ArrayList<>();
        for (TauxDeChange tauxDeChange : tauxDeChanges) {
            list.add(tauxDeChangeToTauxDeChangeDTO(tauxDeChange));
        }
        return list;
    }
}
