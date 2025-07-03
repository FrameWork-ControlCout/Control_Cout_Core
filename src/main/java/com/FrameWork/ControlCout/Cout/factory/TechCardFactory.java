/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.TechCard;
import com.FrameWork.ControlCout.Cout.dto.TechCardDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TechCardFactory {

    public static TechCard createTechCardByCode(int code) {
        TechCard domaine = new TechCard();
        domaine.setCode(code);
        return domaine;
    }

    public static TechCard techCardDTOToTechCard(TechCardDTO dto, TechCard domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setPrixTotal(dto.getPrixTotal());
            domaine.setActif(dto.isActif());
            domaine.setDesignationAr(dto.getDesignationAr()); 
            domaine.setCoutUnitaire(dto.getCoutUnitaire());


            return domaine;
        } else {
            return null;
        }
    }

    public static TechCardDTO techCardToTechCardDTO(TechCard domaine) {

        if (domaine != null) {
            TechCardDTO dto = new TechCardDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setActif(domaine.isActif());
            dto.setDesignationAr(domaine.getDesignationAr());  
            dto.setCoutUnitaire(domaine.getCoutUnitaire());


            return dto;
        } else {
            return null;
        }
    }

    public static List<TechCardDTO> listTechCardToTechCardDTOs(List<TechCard> techCards) {
        List<TechCardDTO> list = new ArrayList<>();
        for (TechCard techCard : techCards) {
            list.add(techCardToTechCardDTO(techCard));
        }
        return list;
    }

    public static Collection<TechCardDTO> CollectionTechCardToTechCardDTOs(Collection<TechCard> techCards) {
        Collection<TechCardDTO> collection = new ArrayList<>();
        for (TechCard techCard : techCards) {
            collection.add(techCardToTechCardDTO(techCard));
        }
        return collection;
    }
}
