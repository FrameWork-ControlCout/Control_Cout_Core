/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.TypeRepa;
import com.FrameWork.ControlCout.Parametrage.dto.TypeRepaDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TypeRepaFactory {
    
     public static TypeRepa createTypeRepaByCode(int code) {
        TypeRepa domaine = new TypeRepa();
        domaine.setCode(code);
        return domaine;
    }
      public static TypeRepa typeRepaDTOToTypeRepa(TypeRepaDTO dto, TypeRepa domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());    
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());   
 


            return domaine;
        } else {
            return null;
        }
    }
    
    
   
    public static TypeRepaDTO typeRepaToTypeRepaDTO(TypeRepa domaine) {

        if (domaine != null) {
            TypeRepaDTO dto = new TypeRepaDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr());   
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());   



            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<TypeRepaDTO> listTypeRepaToTypeRepaDTOs(List<TypeRepa> typeRepas) {
        List<TypeRepaDTO> list = new ArrayList<>();
        for (TypeRepa typeRepa : typeRepas) {
            list.add(typeRepaToTypeRepaDTO(typeRepa));
        }
        return list;
    }
}
