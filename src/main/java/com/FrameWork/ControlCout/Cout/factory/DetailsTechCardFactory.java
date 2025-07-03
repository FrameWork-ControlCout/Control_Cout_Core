/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.DetailsTechCard;
import com.FrameWork.ControlCout.Cout.dto.DetailsTechCardDTO;
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
public class DetailsTechCardFactory {

    public static DetailsTechCard createDetailsTechCardByCode(int code) {
        DetailsTechCard domaine = new DetailsTechCard();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsTechCardDTO DetailsTechCardToDetailsTechCardDTONew(DetailsTechCard domaine) {
        if (domaine != null) {
            DetailsTechCardDTO dto = new DetailsTechCardDTO();
            dto.setCode(domaine.getCode());

            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setConsTotal(domaine.getConsTotal());
            dto.setConsUni(domaine.getConsUni());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setPrixUni(domaine.getPrixUni());

            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setCodeTechCard(domaine.getCodeTechCard());
            dto.setTechCardDTO(TechCardFactory.techCardToTechCardDTO(domaine.getTechCard()));

            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));

            return dto;
        } else {
            return null;
        }
    }

    public static DetailsTechCard detailsTechCardDTOToDetailsTechCard(DetailsTechCardDTO dto, DetailsTechCard domaine) {
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
            domaine.setCodeTechCard(dto.getCodeTechCard());
            if (domaine.getCodeTechCard() != null) {
                domaine.setTechCard(TechCardFactory.createTechCardByCode(dto.getCodeTechCard()));
            }
            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsTechCardDTO> listDetailsTechCardToDetailsADmissionDTOs(List<DetailsTechCard> detailsTechCards) {
        List<DetailsTechCardDTO> list = new ArrayList<>();
        for (DetailsTechCard detailsTechCard : detailsTechCards) {
            list.add(DetailsTechCardToDetailsTechCardDTONew(detailsTechCard));
        }
        return list;
    }
}
