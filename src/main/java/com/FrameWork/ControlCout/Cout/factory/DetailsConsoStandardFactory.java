/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsConsoStandardFactory {

    public static DetailsConsoStandard createDetailsConsoStandardByCode(int code) {
        DetailsConsoStandard domaine = new DetailsConsoStandard();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsConsoStandardDTO DetailsConsoStandardToDetailsConsoStandardDTONew(DetailsConsoStandard domaine) {
        if (domaine != null) {
            DetailsConsoStandardDTO dto = new DetailsConsoStandardDTO();
            dto.setCode(domaine.getCode());

            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setNbrePerson(domaine.getNbrePerson());

            dto.setSatisfait(domaine.isSatisfait());
            dto.setHaveOA(domaine.isHaveOA());

          

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setCodeConsoStandard(domaine.getCodeConsoStandard());
            dto.setConsoStandardDTO(ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine.getConsoStandard()));
 
            dto.setCodeUniteConso(domaine.getCodeUniteConso());
            dto.setUniteConsoDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteConso()));

            dto.setCodeUniteSecondaire(domaine.getCodeUniteSecondaire());
            dto.setUniteSecondaireDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSecondaire()));

            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));
            dto.setQteDispensee(domaine.getQteDispensee());
            return dto;
        } else {
            return null;
        }
    }

    public static DetailsConsoStandard detailsConsoStandardDTOToDetailsConsoStandard(DetailsConsoStandardDTO dto, DetailsConsoStandard domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
         
            domaine.setNbrePerson(dto.getNbrePerson());
            domaine.setSatisfait(dto.isSatisfait());
            domaine.setHaveOA(dto.isHaveOA());

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }
            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }
            domaine.setCodeConsoStandard(dto.getCodeConsoStandard());
            if (domaine.getCodeConsoStandard() != null) {
                domaine.setConsoStandard(ConsoStandardFactory.createConsoStandardByCode(dto.getCodeConsoStandard()));
            }
            domaine.setCodeUniteConso(dto.getCodeUniteConso());
            if (domaine.getCodeUniteConso() != null) {
                domaine.setUniteConso(UniteFactory.createUniteByCode(dto.getCodeUniteConso()));
            }

            domaine.setCodeUniteSecondaire(dto.getCodeUniteSecondaire());
            if (domaine.getCodeUniteSecondaire() != null) {
                domaine.setUniteSecondaire(UniteFactory.createUniteByCode(dto.getCodeUniteSecondaire()));
            }
            if (dto.getQteDispensee() != null) {
                domaine.setQteDispensee(dto.getQteDispensee());
            }

            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsConsoStandardDTO> listDetailsConsoStandardToDetailsConsoStandardDTOs(List<DetailsConsoStandard> detailsConsoStandards) {
        List<DetailsConsoStandardDTO> list = new ArrayList<>();
        for (DetailsConsoStandard detailsConsoStandard : detailsConsoStandards) {
            list.add(DetailsConsoStandardToDetailsConsoStandardDTONew(detailsConsoStandard));
        }
        return list;
    }
}
