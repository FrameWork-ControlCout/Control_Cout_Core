/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;

import com.FrameWork.ControlCout.Tresorerie.domaine.TypeRecette;
import com.FrameWork.ControlCout.Tresorerie.dto.TypeRecetteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeRecetteFactory {
    public static TypeRecette createTypeRecetteByCode(int code) {
        TypeRecette domaine = new TypeRecette();
        domaine.setCode(code);
        return domaine;
    }

    public static TypeRecette typeRecetteDTOToTypeRecette(TypeRecetteDTO dto, TypeRecette domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());        
            domaine.setCodeSaisie(dto.getCodeSaisie());   


            domaine.setActif(dto.isActif());
         
            return domaine;
        } else {
            return null;
        }
    }

    public static TypeRecetteDTO typeRecetteToTypeRecetteDTO(TypeRecette domaine) {

        if (domaine != null) {
            TypeRecetteDTO dto = new TypeRecetteDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());

            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setCodeSaisieTypeRecette(domaine.getCodeSaisie());  
            dto.setDesignationArTypeRecette(domaine.getDesignationAr());      
            dto.setDesignationLtTypeRecette(domaine.getDesignationLt());


            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<TypeRecetteDTO> listTypeRecetteToTypeRecetteDTOs(List<TypeRecette> typeRecettes) {
        List<TypeRecetteDTO> list = new ArrayList<>();
        for (TypeRecette typeRecette : typeRecettes) {
            list.add(typeRecetteToTypeRecetteDTO(typeRecette));
        }
        return list;
    }
}
