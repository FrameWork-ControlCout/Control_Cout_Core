/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleEdition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.springframework.context.i18n.LocaleContextHolder;
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

    public static Article articleDTOToArticle(ArticleDTO dto, Article domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setType(dto.getType());
            domaine.setPackages(dto.getPackages());
            domaine.setLastPrixAchat(dto.getLastPrixAchat());
            domaine.setConversionRate(dto.getConversionRate());
            domaine.setLastPrixGros(dto.getLastPrixGros());

            domaine.setCodeFamille(dto.getCodeFamille());
            if (domaine.getCodeFamille() != null) {
                domaine.setFamilleArticle(FamilleArticleFactory.createFamilleArticleByCode(dto.getCodeFamille()));

            }

            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));

            }

            domaine.setCodeUniteSecondaire(dto.getCodeUniteSecondaire());
            if (domaine.getCodeUniteSecondaire() != null) {
                domaine.setUniteSecondaire(UniteFactory.createUniteByCode(dto.getCodeUniteSecondaire()));

            }

            domaine.setCodeUniteDepense(dto.getCodeUniteDepense());
            if (domaine.getCodeUniteDepense() != null) {
                domaine.setUniteDepense(UniteFactory.createUniteByCode(dto.getCodeUniteDepense()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ArticleDTO articleToArticleDTO(Article domaine) {

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

            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());

            dto.setUniteSecondaireDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSecondaire()));
            dto.setCodeUniteSecondaire(domaine.getCodeUniteSecondaire());

            dto.setConversionRate(domaine.getConversionRate());

            dto.setUniteDepenseDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteDepense()));
            dto.setCodeUniteDepense(domaine.getCodeUniteDepense());

            dto.setType(domaine.getType());
            dto.setLastPrixGros(domaine.getLastPrixGros());

            dto.setPackages(domaine.getPackages());
            dto.setLastPrixAchat(domaine.getLastPrixAchat());

            dto.setCodeSaisieArticle(domaine.getCodeSaisie());
            dto.setDesignationArArticle(domaine.getDesignationAr());
            dto.setDesignationLtArticle(domaine.getDesignationLt());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ArticleDTO> listArticleToArticleDTOs(List<Article> articles) {
        List<ArticleDTO> list = new ArrayList<>();
        for (Article article : articles) {
            list.add(articleToArticleDTO(article));
        }
        return list;
    }

    public static ArticleEdition articleTOArticleIntervenant(Article domaine) {
        if (domaine == null) {
            return null;
        }

        ArticleEdition domaineEdition = new ArticleEdition();
  
        domaineEdition.setCodeSaisie(domaine.getCodeSaisie());
        domaineEdition.setDesignationAr(domaine.getDesignationAr());
        domaineEdition.setDesignationArUnite(domaine.getUnite().getDesignationAr());
        domaineEdition.setPackages(domaine.getPackages());

        return domaineEdition;
    }

    public static Collection<ArticleEdition> listArticleTOEditionArticle(List<Article> articles) {
        if (articles == null) {
            return null;
        }
        List<ArticleEdition> list = new ArrayList<>();
        for (Article article : articles) {
            list.add(articleTOArticleIntervenant(article));
        }
        return list;
    }
}
