/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.FicheTech;
import com.FrameWork.ControlCout.Cout.dto.FicheTechDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FicheTechFactory {
    
    public static FicheTech createFicheTechniqueByCode(int code) {
        FicheTech domaine = new FicheTech();
        domaine.setCode(code);
        return domaine;
    }
    
    public static FicheTech ficheTechniqueDTOToFicheTechnique(FicheTechDTO dto, FicheTech domaine) {
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
    
    public static FicheTechDTO ficheTechniqueToFicheTechniqueDTO(FicheTech domaine) {
        
        if (domaine != null) {
            FicheTechDTO dto = new FicheTechDTO();
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
    
    public static List<FicheTechDTO> listFicheTechniqueToFicheTechniqueDTOs(List<FicheTech> ficheTechniques) {
        List<FicheTechDTO> list = new ArrayList<>();
        for (FicheTech ficheTechnique : ficheTechniques) {
            list.add(ficheTechniqueToFicheTechniqueDTO(ficheTechnique));
        }
        return list;
    }
    
    public static Collection<FicheTechDTO> CollectionFicheTechniqueToFicheTechniqueDTOs(Collection<FicheTech> ficheTechniques) {
        Collection<FicheTechDTO> collection = new ArrayList<>();
        for (FicheTech ficheTechnique : ficheTechniques) {
            collection.add(ficheTechniqueToFicheTechniqueDTO(ficheTechnique));
        }
        return collection;
    }
}
