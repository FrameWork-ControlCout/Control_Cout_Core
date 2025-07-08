/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.CalculeConsommation;
import com.FrameWork.ControlCout.Cout.domaine.CalculeConsommation;
import com.FrameWork.ControlCout.Cout.dto.CalculeConsommationDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CalculeConsommationFactory {

    public static CalculeConsommation createCalculeConsommationByCode(int code) {
        CalculeConsommation domaine = new CalculeConsommation();
        domaine.setCode(code);
        return domaine;
    }

    public static CalculeConsommation calculeConsommationDTOToCalculeConsommation(CalculeConsommationDTO dto, CalculeConsommation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setActif(dto.isActif());
            domaine.setDateFin(dto.getDateFin());
            domaine.setDateDebut(dto.getDateDebut());
            domaine.setPrixTotal(dto.getPrixTotal());

            return domaine;
        } else {
            return null;
        }
    }

    public static CalculeConsommationDTO calculeConsommationToCalculeConsommationDTO(CalculeConsommation domaine) {

        if (domaine != null) {
            CalculeConsommationDTO dto = new CalculeConsommationDTO();
            dto.setCode(domaine.getCode());
            dto.setActif(domaine.isActif());
            dto.setActif(domaine.isActif());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<CalculeConsommationDTO> listCalculeConsommationToCalculeConsommationDTOs(List<CalculeConsommation> calculeConsommations) {
        List<CalculeConsommationDTO> list = new ArrayList<>();
        for (CalculeConsommation calculeConsommation : calculeConsommations) {
            list.add(calculeConsommationToCalculeConsommationDTO(calculeConsommation));
        }
        return list;
    }

    public static Collection<CalculeConsommationDTO> CollectionCalculeConsommationToCalculeConsommationDTOs(Collection<CalculeConsommation> calculeConsommations) {
        Collection<CalculeConsommationDTO> collection = new ArrayList<>();
        for (CalculeConsommation calculeConsommation : calculeConsommations) {
            collection.add(calculeConsommationToCalculeConsommationDTO(calculeConsommation));
        }
        return collection;
    }
}
