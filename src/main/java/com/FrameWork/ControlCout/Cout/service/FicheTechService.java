/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.domaine.FicheTech;
import com.FrameWork.ControlCout.Cout.dto.DetailsFicheTechDTO;
import com.FrameWork.ControlCout.Cout.dto.FicheTechDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsFicheTechFactory;
import com.FrameWork.ControlCout.Cout.factory.FicheTechFactory;
import com.FrameWork.ControlCout.Cout.repository.PlanRepaRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.BanqueRepo;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Cout.repository.DetailsFicheTechRepo;
import com.FrameWork.ControlCout.Cout.repository.FicheTechRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FicheTechService {
    
    private final Logger log = LoggerFactory.getLogger(FicheTechService.class);
    
    private final FicheTechRepo ficheTechniqueRepo;
    private final DetailsFicheTechRepo detailsFicheTechniqueRepo;
    private final PlanRepaRepo planRepaRepo;
    private final CompteurService compteurService;
    
    public FicheTechService(FicheTechRepo ficheTechniqueRepo, DetailsFicheTechRepo detailsFicheTechniqueRepo, PlanRepaRepo planRepaRepo, CompteurService compteurService) {
        this.ficheTechniqueRepo = ficheTechniqueRepo;
        this.detailsFicheTechniqueRepo = detailsFicheTechniqueRepo;
        this.planRepaRepo = planRepaRepo;
        this.compteurService = compteurService;
    }
    
    @Transactional(readOnly = true)
    public List<FicheTechDTO> findAllFicheTechnique() {
        
        List<FicheTech> domaine = ficheTechniqueRepo.findAll(Sort.by("code").descending());
        
        return FicheTechFactory.listFicheTechniqueToFicheTechniqueDTOs(domaine);
        
    }
    
    @Transactional(readOnly = true)
    public List<FicheTechDTO> findAllFicheTechniqueByActif(Boolean actif) {
        
        List<FicheTech> domaine = ficheTechniqueRepo.findByActif(actif);
        
        return FicheTechFactory.listFicheTechniqueToFicheTechniqueDTOs(domaine);
        
    }
    
    @Transactional(readOnly = true)
    public FicheTechDTO findOne(Integer code) {
        FicheTech domaine = ficheTechniqueRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FicheTechniqueNotFound");
        return FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine);
        
    }
    
    public FicheTechDTO save(FicheTechDTO dto) {
        
        FicheTech domaine = FicheTechFactory.ficheTechniqueDTOToFicheTechnique(dto, new FicheTech());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieFicheTechnique");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        domaine = ficheTechniqueRepo.save(domaine);

        //DetailsFicheTechnique 
        if (dto.getDetailsFicheTechniquesCardDTOs() != null) {
            
            List<DetailsFicheTechDTO> detailsFicheTechniqueDTOs = dto.getDetailsFicheTechniquesCardDTOs();
            for (DetailsFicheTechDTO detailsDto : detailsFicheTechniqueDTOs) {
                DetailsFicheTech detailsDomaine = DetailsFicheTechFactory.detailsFicheTechniqueDTOToDetailsFicheTechnique(detailsDto, new DetailsFicheTech());
                detailsDomaine.setFicheTechnique(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                
                detailsDomaine.setConsUni(detailsDto.getConsUni());
                detailsDomaine.setConsTotal(detailsDto.getConsTotal());
                
                detailsDomaine.setPrixUni(detailsDto.getPrixUni());
                detailsDomaine.setPrixTotal(detailsDto.getPrixTotal());
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }
                
                detailsDomaine.setCodeFicheTechnique(detailsDto.getCodeFicheTechnique());
                if (detailsDomaine.getCodeFicheTechnique() != null) {
                    detailsDomaine.setFicheTechnique(FicheTechFactory.createFicheTechniqueByCode(detailsDto.getCodeFicheTechnique()));
                }
                
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                
                detailsFicheTechniqueRepo.save(detailsDomaine); // Assuming you have a detailsFicheTechniqueRepo

            }
            
        }
        
        return FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine);
    }
    
    public void deleteFicheTechnique(Integer code) {
        Preconditions.checkArgument(ficheTechniqueRepo.existsById(code), "error.FicheTechniqueNotFound");
        List<PlanRepa> plRepa = planRepaRepo.findByCodeFicheTechnique(code);
        
        Preconditions.checkArgument(plRepa.isEmpty(), "error.FicheTechniqueUsedInPlanRepa");
        
        detailsFicheTechniqueRepo.deleteByCodeFicheTechnique(code);
        ficheTechniqueRepo.deleteById(code);
    }
    
    public FicheTechDTO update(FicheTechDTO dto) {
        FicheTech domaine = ficheTechniqueRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = FicheTechFactory.ficheTechniqueDTOToFicheTechnique(dto, domaine);
        domaine = ficheTechniqueRepo.save(domaine);
        
        detailsFicheTechniqueRepo.deleteByCodeFicheTechnique(domaine.getCode());
        
        if (dto.getDetailsFicheTechniquesCardDTOs() != null) {
            
            List<DetailsFicheTechDTO> detailsFicheTechniqueDTOs = dto.getDetailsFicheTechniquesCardDTOs();
            for (DetailsFicheTechDTO detailsDto : detailsFicheTechniqueDTOs) {
                DetailsFicheTech detailsDomaine = DetailsFicheTechFactory.detailsFicheTechniqueDTOToDetailsFicheTechnique(detailsDto, new DetailsFicheTech());
                detailsDomaine.setFicheTechnique(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                detailsDomaine.setPrixUni(detailsDto.getPrixUni());
                detailsDomaine.setPrixTotal(detailsDto.getPrixTotal());
                detailsDomaine.setConsUni(detailsDto.getConsUni());
                detailsDomaine.setConsTotal(detailsDto.getConsTotal());
                
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }
                
                detailsDomaine.setCodeFicheTechnique(detailsDto.getCodeFicheTechnique());
                if (detailsDomaine.getCodeFicheTechnique() != null) {
                    detailsDomaine.setFicheTechnique(FicheTechFactory.createFicheTechniqueByCode(detailsDto.getCodeFicheTechnique()));
                }
                
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                
                detailsFicheTechniqueRepo.save(detailsDomaine); // Assuming you have a detailsFicheTechniqueRepo

            }
            
        } else {
            throw new IllegalArgumentException("error.DetailsFactureNotFound");
        }
        
        FicheTechDTO resultDTO = FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine);
        return resultDTO;
    }
    
}
