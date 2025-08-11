/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.HistoriqueFicheTechnique;
import com.FrameWork.ControlCout.Cout.dto.HistoriqueFicheTechniqueDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class HistoriqueFicheTechniqueFactory {

    public static HistoriqueFicheTechnique createHistoriqueFicheTechniqueniqueByCode(int code) {
        HistoriqueFicheTechnique domaine = new HistoriqueFicheTechnique();
        domaine.setCode(code);
        return domaine;
    }

    public static HistoriqueFicheTechnique historiqueficheTechniqueDTOToHistoriqueFicheTechnique(HistoriqueFicheTechniqueDTO dto, HistoriqueFicheTechnique domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeFicheTechnique(dto.getCodeFicheTechnique());
            if (domaine.getCodeFicheTechnique() != null) {
                domaine.setFicheTechnique(FicheTechFactory.createFicheTechniqueByCode(dto.getCodeFicheTechnique()));
            }

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }
            domaine.setPrixArticleOld(dto.getPrixArticleOld());
            domaine.setPrixArticleNew(dto.getPrixArticleNew());
            domaine.setPrixFicheTechOld(dto.getPrixFicheTechOld());
            domaine.setPrixFicheTechNew(dto.getPrixFicheTechNew());
            

            return domaine;
        } else {
            return null;
        }
    }

    public static HistoriqueFicheTechniqueDTO historiqueficheTechniqueToHistoriqueFicheTechniqueDTO(HistoriqueFicheTechnique domaine) {

        if (domaine != null) {
            HistoriqueFicheTechniqueDTO dto = new HistoriqueFicheTechniqueDTO();
            dto.setCode(domaine.getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setCodeSaisieBR(domaine.getCodeSaisieBR());

            dto.setPrixArticleOld(domaine.getPrixArticleOld());
            dto.setPrixArticleNew(domaine.getPrixArticleNew());
            dto.setPrixFicheTechOld(domaine.getPrixFicheTechOld());
            dto.setPrixFicheTechNew(domaine.getPrixFicheTechNew());
            dto.setCodeFicheTechnique(domaine.getCodeFicheTechnique());
            dto.setFicheTechniqueDTO(FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine.getFicheTechnique()));

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<HistoriqueFicheTechniqueDTO> listHistoriqueFicheTechniqueniqueToHistoriqueFicheTechniqueDTOs(List<HistoriqueFicheTechnique> ficheTechniques) {
        List<HistoriqueFicheTechniqueDTO> list = new ArrayList<>();
        for (HistoriqueFicheTechnique ficheTechnique : ficheTechniques) {
            list.add(historiqueficheTechniqueToHistoriqueFicheTechniqueDTO(ficheTechnique));
        }
        return list;
    }

    public static Collection<HistoriqueFicheTechniqueDTO> CollectionHistoriqueFicheTechniqueToHistoriqueFicheTechniqueDTOs(Collection<HistoriqueFicheTechnique> ficheTechniques) {
        Collection<HistoriqueFicheTechniqueDTO> collection = new ArrayList<>();
        for (HistoriqueFicheTechnique ficheTechnique : ficheTechniques) {
            collection.add(historiqueficheTechniqueToHistoriqueFicheTechniqueDTO(ficheTechnique));
        }
        return collection;
    }
}
