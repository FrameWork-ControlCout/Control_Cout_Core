/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.DetailsCalculeConsommation;
import com.FrameWork.ControlCout.Cout.dto.DetailsCalculeConsommationDTO;
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
public class DetailsCalculeConsommationFactory {

    public static DetailsCalculeConsommation createDetailsCalculeConsommationByCode(int code) {
        DetailsCalculeConsommation domaine = new DetailsCalculeConsommation();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsCalculeConsommationDTO DetailsCalculeConsommationToDetailsCalculeConsommationDTONew(DetailsCalculeConsommation domaine) {
        if (domaine != null) {
            DetailsCalculeConsommationDTO dto = new DetailsCalculeConsommationDTO();
            dto.setCode(domaine.getCode());

            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setConsTotal(domaine.getConsTotal());
            dto.setConsUni(domaine.getConsUni());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setPrixUni(domaine.getPrixUni());

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setCodeCalculeConsommation(domaine.getCodeCalculeConsommation());
            dto.setCalculeConsommationDTO(CalculeConsommationFactory.calculeConsommationToCalculeConsommationDTO(domaine.getCalculeConsommation()));

            dto.setCodeUniteConso(domaine.getCodeUniteConso());
            dto.setUniteConsoDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteConso()));

              dto.setCodeUniteSecondaire(domaine.getCodeUniteSecondaire());
            dto.setUniteSecondaireDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSecondaire()));

            return dto;
        } else {
            return null;
        }
    }

    public static DetailsCalculeConsommation detailsCalculeConsommationDTOToDetailsCalculeConsommation(DetailsCalculeConsommationDTO dto, DetailsCalculeConsommation domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setConsTotal(dto.getConsTotal());
            domaine.setConsUni(dto.getConsUni());
            domaine.setPrixTotal(dto.getPrixTotal());
            domaine.setPrixUni(dto.getPrixUni());
            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }
            domaine.setCodeCalculeConsommation(dto.getCodeCalculeConsommation());
            if (domaine.getCodeCalculeConsommation() != null) {
                domaine.setCalculeConsommation(CalculeConsommationFactory.createCalculeConsommationByCode(dto.getCodeCalculeConsommation()));
            }
            domaine.setCodeUniteConso(dto.getCodeUniteConso());
            if (domaine.getCodeUniteConso()!= null) {
                domaine.setUniteConso(UniteFactory.createUniteByCode(dto.getCodeUniteConso()));
            }
            
             domaine.setCodeUniteSecondaire(dto.getCodeUniteSecondaire());
            if (domaine.getCodeUniteSecondaire()!= null) {
                domaine.setUniteSecondaire(UniteFactory.createUniteByCode(dto.getCodeUniteSecondaire()));
            }

            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsCalculeConsommationDTO> listDetailsCalculeConsommationToDetailsADmissionDTOs(List<DetailsCalculeConsommation> detailsCalculeConsommations) {
        List<DetailsCalculeConsommationDTO> list = new ArrayList<>();
        for (DetailsCalculeConsommation detailsCalculeConsommation : detailsCalculeConsommations) {
            list.add(DetailsCalculeConsommationToDetailsCalculeConsommationDTONew(detailsCalculeConsommation));
        }
        return list;
    }
}
