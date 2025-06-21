/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.FamilleArticle;
import com.FrameWork.ControlCout.Parametrage.dto.FamilleArticleDTO;
import com.FrameWork.ControlCout.Parametrage.factory.FamilleArticleFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.FamilleArticleRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FamilleArticleService {

    private final FamilleArticleRepo familleArticleRepo;
    private final CompteurService compteurService;

    public FamilleArticleService(FamilleArticleRepo familleArticleRepo, CompteurService compteurService) {
        this.familleArticleRepo = familleArticleRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<FamilleArticleDTO> findAllFamilleArticle() {
        return FamilleArticleFactory.listFamilleArticleToFamilleArticleDTOs(familleArticleRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public FamilleArticleDTO findOne(Integer code) {
        FamilleArticle domaine = familleArticleRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FamilleArticleNotFound");
        return FamilleArticleFactory.familleArticleToFamilleArticleDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<FamilleArticleDTO> findAllFamilleArticleByActif(Boolean actif) {
        return FamilleArticleFactory.listFamilleArticleToFamilleArticleDTOs(familleArticleRepo.findByActif(actif));

    }
    
     @Transactional(readOnly = true)
    public List<FamilleArticleDTO> findAllFamilleArticleByActifAndType(Boolean actif,String type) {
        return FamilleArticleFactory.listFamilleArticleToFamilleArticleDTOs(familleArticleRepo.findByActifAndType(actif,type));

    }

    public FamilleArticleDTO save(FamilleArticleDTO dto) {
        FamilleArticle domaine = FamilleArticleFactory.familleArticleDTOToFamilleArticle(dto, new FamilleArticle());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("FamArticle");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = familleArticleRepo.save(domaine);

        return FamilleArticleFactory.familleArticleToFamilleArticleDTO(domaine);
    }

    public FamilleArticle update(FamilleArticleDTO dto) {
        FamilleArticle domaine = familleArticleRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FamilleArticleNotFound");
        dto.setCode(domaine.getCode());
        FamilleArticleFactory.familleArticleDTOToFamilleArticle(dto, domaine);

        return familleArticleRepo.save(domaine);
    }

    public void deleteFamilleArticle(Integer code) {
        Preconditions.checkArgument(familleArticleRepo.existsById(code), "error.FamilleArticleNotFound");
        familleArticleRepo.deleteById(code);
    }
}
