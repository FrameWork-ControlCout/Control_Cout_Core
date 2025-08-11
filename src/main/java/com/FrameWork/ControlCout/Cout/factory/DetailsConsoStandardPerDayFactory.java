/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandardPerDay;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardPerDayDTO;
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
public class DetailsConsoStandardPerDayFactory {

    public static DetailsConsoStandardPerDay createDetailsConsoStandardPerDayByCode(int code) {
        DetailsConsoStandardPerDay domaine = new DetailsConsoStandardPerDay();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsConsoStandardPerDayDTO DetailsConsoStandardPerDayToDetailsConsoStandardPerDayDTONew(DetailsConsoStandardPerDay domaine) {
        if (domaine != null) {
            DetailsConsoStandardPerDayDTO dto = new DetailsConsoStandardPerDayDTO();
            dto.setCode(domaine.getCode());

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setCodeConsoStandard(domaine.getCodeConsoStandardPerDay());
            dto.setConsoStandardDTO(ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine.getConsoStandard()));

            dto.setCodeDetailsConsoConsommation(domaine.getCodeDetailsConsoConsommation());
            dto.setDetailsConsoStandardDTO(DetailsConsoStandardFactory.DetailsConsoStandardToDetailsConsoStandardDTONew(domaine.getDetailsConsoStandard()));

            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));
            dto.setCodeUniteSecondaire(domaine.getCodeUniteSecondaire());
            dto.setUniteSecondaireDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSecondaire()));

            dto.setConsUni(domaine.getConsUni());
            dto.setConsTotal(domaine.getConsTotal());
            dto.setDatePlan(domaine.getDatePlan());

            dto.setNbrePreson(domaine.getNbrePreson());
                        dto.setSatisfait(domaine.isSatisfait());


            return dto;
        } else {
            return null;
        }
    }

    public static DetailsConsoStandardPerDay detailsConsoStandardDPerDayTOToDetailsConsoStandardPerDay(DetailsConsoStandardPerDayDTO dto, DetailsConsoStandardPerDay domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }

            domaine.setCodeConsoStandardPerDay(dto.getCodeConsoStandard());
            if (domaine.getCodeConsoStandardPerDay() != null) {
                domaine.setConsoStandard(ConsoStandardFactory.createConsoStandardByCode(dto.getCodeConsoStandard()));
            }
            domaine.setCodeDetailsConsoConsommation(dto.getCodeDetailsConsoConsommation());
            if (domaine.getCodeDetailsConsoConsommation() != null) {
                domaine.setDetailsConsoStandard(DetailsConsoStandardFactory.createDetailsConsoStandardByCode(dto.getCodeDetailsConsoConsommation()));
            }

            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }
            domaine.setCodeUniteSecondaire(dto.getCodeUniteSecondaire());
            if (domaine.getCodeUniteSecondaire() != null) {
                domaine.setUniteSecondaire(UniteFactory.createUniteByCode(dto.getCodeUniteSecondaire()));
            }
            domaine.setConsUni(dto.getConsUni());
            domaine.setConsTotal(dto.getConsTotal());
            domaine.setDatePlan(dto.getDatePlan());
            domaine.setNbrePreson(dto.getNbrePreson());
                        domaine.setSatisfait(dto.isSatisfait());


            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsConsoStandardPerDayDTO> listDetailsConsoStandardPerDayToDetailsConsoStandardPerDayDTOs(List<DetailsConsoStandardPerDay> detailsConsoStandards) {
        List<DetailsConsoStandardPerDayDTO> list = new ArrayList<>();
        for (DetailsConsoStandardPerDay detailsConsoStandard : detailsConsoStandards) {
            list.add(DetailsConsoStandardPerDayToDetailsConsoStandardPerDayDTONew(detailsConsoStandard));
        }
        return list;
    }
}
