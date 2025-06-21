/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ArticleFactory {

    public static Article createArticleByCode(int code) {
        Article domaine = new Article();
        domaine.setCode(code);
        return domaine;
    }

    public static Article familleArticleDTOToArticle(ArticleDTO dto, Article domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setType(dto.getType());

            domaine.setCodeFamille(dto.getCodeFamille());
            if (domaine.getCodeFamille() != null) {
                domaine.setFamilleArticle(FamilleArticleFactory.createFamilleArticleByCode(dto.getCodeFamille()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ArticleDTO familleArticleToArticleDTO(Article domaine) {

        if (domaine != null) {
            ArticleDTO dto = new ArticleDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setFamilleArticleDTO(FamilleArticleFactory.familleArticleToFamilleArticleDTO(domaine.getFamilleArticle()));
            dto.setCodeFamille(domaine.getCodeFamille());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ArticleDTO> listArticleToArticleDTOs(List<Article> familleArticles) {
        List<ArticleDTO> list = new ArrayList<>();
        for (Article familleArticle : familleArticles) {
            list.add(familleArticleToArticleDTO(familleArticle));
        }
        return list;
    }
}
