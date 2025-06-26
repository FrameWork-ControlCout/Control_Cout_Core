/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement;
import com.FrameWork.ControlCout.Parametrage.dto.ModeReglementDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ModeReglementFactory {
     public static ModeReglement createModeReglementByCode(int code) {
        ModeReglement domaine = new ModeReglement();
        domaine.setCode(code);
        return domaine;
    }

    public static ModeReglement modeReglementDTOToModeReglement(ModeReglementDTO dto, ModeReglement domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());        
            domaine.setCodeSaisie(dto.getCodeSaisie()); 
            domaine.setReqBanque(dto.isReqBanque());


            domaine.setActif(dto.isActif());
       
            return domaine;
        } else {
            return null;
        }
    }

    public static ModeReglementDTO modeReglementToModeReglementDTO(ModeReglement domaine) {

        if (domaine != null) {
            ModeReglementDTO dto = new ModeReglementDTO();
            dto.setCode(domaine.getCode()); 
            dto.setReqBanque(domaine.isReqBanque()); 
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ModeReglementDTO> listModeReglementToModeReglementDTOs(List<ModeReglement> modeReglements) {
        List<ModeReglementDTO> list = new ArrayList<>();
        for (ModeReglement modeReglement : modeReglements) {
            list.add(modeReglementToModeReglementDTO(modeReglement));
        }
        return list;
    }
}
