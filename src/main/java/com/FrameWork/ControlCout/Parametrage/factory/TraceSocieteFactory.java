/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import com.FrameWork.ControlCout.Parametrage.dto.TraceSocieteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TraceSocieteFactory {

    public static TraceSociete createSocieteByCode(int code) {
        TraceSociete domaine = new TraceSociete();
        domaine.setCode(code);
        return domaine;
    }

    public static TraceSociete traceSocieteDTOToTraceSociete(TraceSocieteDTO dto, TraceSociete domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDateUpdate(dto.getDateUpdate());
            domaine.setNbrePersonNew(dto.getNbrePersonNew());
            domaine.setNbrePersonOld(dto.getNbrePersonOld());
         
            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }
            return domaine;
        } else {
            return null;
        }
    }

    public static TraceSocieteDTO traceSocieteToTraceSocieteDTO(TraceSociete domaine) {

        if (domaine != null) {
            TraceSocieteDTO dto = new TraceSocieteDTO();
            dto.setCode(domaine.getCode());
            dto.setDateUpdate(domaine.getDateUpdate());
            dto.setNbrePersonNew(domaine.getNbrePersonNew());
            dto.setNbrePersonOld(domaine.getNbrePersonOld());
       
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<TraceSocieteDTO> listTraceSocieteToTraceSocieteDTOs(List<TraceSociete> traceSocietes) {
        List<TraceSocieteDTO> list = new ArrayList<>();
        for (TraceSociete traceSociete : traceSocietes) {
            list.add(traceSocieteToTraceSocieteDTO(traceSociete));
        }
        return list;
    }
}
