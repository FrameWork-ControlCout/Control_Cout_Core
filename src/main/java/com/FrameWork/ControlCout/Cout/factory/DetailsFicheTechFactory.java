/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import com.FrameWork.ControlCout.Cout.dto.DetailsFicheTechDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsFicheTechFactory {

    public static DetailsFicheTech createDetailsFicheTechniqueByCode(int code) {
        DetailsFicheTech domaine = new DetailsFicheTech();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsFicheTechDTO DetailsFicheTechniqueToDetailsFicheTechniqueDTONew(DetailsFicheTech domaine) {
        if (domaine != null) {
            DetailsFicheTechDTO dto = new DetailsFicheTechDTO();
            dto.setCode(domaine.getCode());

            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setConsTotal(domaine.getConsTotal());
            dto.setConsUni(domaine.getConsUni());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setPrixUni(domaine.getPrixUni());

            dto.setModifPrice(domaine.getModifPrice());

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setCodeFicheTechnique(domaine.getCodeFicheTechnique());
            dto.setFicheTechDTO(FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine.getFicheTechnique()));

            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));

            dto.setCodeUniteSecondaire(domaine.getCodeUniteSecondaire());
            dto.setUniteSecondaireDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSecondaire()));

            return dto;
        } else {
            return null;
        }
    }

    public static DetailsFicheTech detailsFicheTechniqueDTOToDetailsFicheTechnique(DetailsFicheTechDTO dto, DetailsFicheTech domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setConsTotal(dto.getConsTotal());
            domaine.setConsUni(dto.getConsUni());
            domaine.setPrixTotal(dto.getPrixTotal());
            domaine.setPrixUni(dto.getPrixUni());
            domaine.setModifPrice(dto.getModifPrice());

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }
            domaine.setCodeFicheTechnique(dto.getCodeFicheTechnique());
            if (domaine.getCodeFicheTechnique() != null) {
                domaine.setFicheTechnique(FicheTechFactory.createFicheTechniqueByCode(dto.getCodeFicheTechnique()));
            }
            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            domaine.setCodeUniteSecondaire(dto.getCodeUniteSecondaire());
            if (domaine.getCodeUniteSecondaire() != null) {
                domaine.setUniteSecondaire(UniteFactory.createUniteByCode(dto.getCodeUniteSecondaire()));
            }

            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsFicheTechDTO> listDetailsFicheTechniqueToDetailsADmissionDTOs(List<DetailsFicheTech> detailsFicheTechniques) {
        List<DetailsFicheTechDTO> list = new ArrayList<>();
        for (DetailsFicheTech detailsFicheTechnique : detailsFicheTechniques) {
            list.add(DetailsFicheTechniqueToDetailsFicheTechniqueDTONew(detailsFicheTechnique));
        }
        return list;
    }
}
