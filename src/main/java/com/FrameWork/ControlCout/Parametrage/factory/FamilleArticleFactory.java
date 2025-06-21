/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.FamilleArticle;
import com.FrameWork.ControlCout.Parametrage.dto.FamilleArticleDTO;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FamilleArticleFactory {
    
     public static FamilleArticle createFamilleArticleByCode(int code) {
        FamilleArticle domaine = new FamilleArticle();
        domaine.setCode(code);
        return domaine;
    }
      public static FamilleArticle familleArticleDTOToFamilleArticle(FamilleArticleDTO dto, FamilleArticle domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());       
            domaine.setCodeSaisie(dto.getCodeSaisie());   
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());  
            domaine.setType(dto.getType());

         

            return domaine;
        } else {
            return null;
        }
    }
    
    
   
    public static FamilleArticleDTO familleArticleToFamilleArticleDTO(FamilleArticle domaine) {

        if (domaine != null) {
            FamilleArticleDTO dto = new FamilleArticleDTO();
            dto.setCode(domaine.getCode());    
            dto.setCodeSaisie(domaine.getCodeSaisie());  
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt()); 
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());    
            dto.setType(domaine.getType());    
 


            return dto;
        } else {
            return null;
        }
    }
    
    
    public static List<FamilleArticleDTO> listFamilleArticleToFamilleArticleDTOs(List<FamilleArticle> familleArticles) {
        List<FamilleArticleDTO> list = new ArrayList<>();
        for (FamilleArticle familleArticle : familleArticles) {
            list.add(familleArticleToFamilleArticleDTO(familleArticle));
        }
        return list;
    }
}
