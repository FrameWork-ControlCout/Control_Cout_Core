/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.ArticleRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ArticleService {

    private final ArticleRepo articleRepo;
    private final CompteurService compteurService;

    public ArticleService(ArticleRepo articleRepo, CompteurService compteurService) {
        this.articleRepo = articleRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<ArticleDTO> findAllArticle() {
        return ArticleFactory.listArticleToArticleDTOs(articleRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public ArticleDTO findOne(Integer code) {
        Article domaine = articleRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.ArticleNotFound");
        return ArticleFactory.familleArticleToArticleDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<ArticleDTO> findAllArticleByActif(Boolean actif) {
        return ArticleFactory.listArticleToArticleDTOs(articleRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public List<ArticleDTO> findAllArticleByTypeAndActif(String type, Boolean actif) {
        return ArticleFactory.listArticleToArticleDTOs(articleRepo.findByTypeAndActif(type, actif));

    }

    @Transactional(readOnly = true)
    public List<ArticleDTO> findAllArticleByType(String type) {
        return ArticleFactory.listArticleToArticleDTOs(articleRepo.findByType(type));
    }

    public ArticleDTO save(ArticleDTO dto) {
        Article domaine = ArticleFactory.familleArticleDTOToArticle(dto, new Article());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("Article");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = articleRepo.save(domaine);

        return ArticleFactory.familleArticleToArticleDTO(domaine);
    }

    public Article update(ArticleDTO dto) {
        Article domaine = articleRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.ArticleNotFound");
        dto.setCode(domaine.getCode());
        ArticleFactory.familleArticleDTOToArticle(dto, domaine);

        return articleRepo.save(domaine);
    }

    public void deleteArticle(Integer code) {
        Preconditions.checkArgument(articleRepo.existsById(code), "error.ArticleNotFound");
        articleRepo.deleteById(code);
    }
}
