/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsTechCard;
import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.domaine.TechCard;
import com.FrameWork.ControlCout.Cout.dto.DetailsTechCardDTO;
import com.FrameWork.ControlCout.Cout.dto.TechCardDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsTechCardFactory;
import com.FrameWork.ControlCout.Cout.factory.TechCardFactory;
import com.FrameWork.ControlCout.Cout.repository.DetailsTechCardRepo;
import com.FrameWork.ControlCout.Cout.repository.PlanRepaRepo;
import com.FrameWork.ControlCout.Cout.repository.TechCardRepo;
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

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TechCardService {
    
    private final Logger log = LoggerFactory.getLogger(TechCardService.class);
    
    private final TechCardRepo techCardRepo;
    private final DetailsTechCardRepo detailsTechCardRepo;
    private final PlanRepaRepo planRepaRepo;
    private final CompteurService compteurService;
    
    public TechCardService(TechCardRepo techCardRepo, DetailsTechCardRepo detailsTechCardRepo, PlanRepaRepo planRepaRepo, CompteurService compteurService) {
        this.techCardRepo = techCardRepo;
        this.detailsTechCardRepo = detailsTechCardRepo;
        this.planRepaRepo = planRepaRepo;
        this.compteurService = compteurService;
    }
    
    @Transactional(readOnly = true)
    public List<TechCardDTO> findAllTechCard() {
        
        List<TechCard> domaine = techCardRepo.findAll(Sort.by("code").descending());
        
        return TechCardFactory.listTechCardToTechCardDTOs(domaine);
        
    }
    
    @Transactional(readOnly = true)
    public List<TechCardDTO> findAllTechCardByActif(Boolean actif) {
        
        List<TechCard> domaine = techCardRepo.findByActif(actif);
        
        return TechCardFactory.listTechCardToTechCardDTOs(domaine);
        
    }
    
    @Transactional(readOnly = true)
    public TechCardDTO findOne(Integer code) {
        TechCard domaine = techCardRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TechCardNotFound");
        return TechCardFactory.techCardToTechCardDTO(domaine);
        
    }
    
    public TechCardDTO save(TechCardDTO dto) {
        
        TechCard domaine = TechCardFactory.techCardDTOToTechCard(dto, new TechCard());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTechCard");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        domaine = techCardRepo.save(domaine);

        //DetailsTechCard 
        if (dto.getDetailsTechCardsCardDTOs() != null) {
            
            List<DetailsTechCardDTO> detailsTechCardDTOs = dto.getDetailsTechCardsCardDTOs();
            for (DetailsTechCardDTO detailsDto : detailsTechCardDTOs) {
                DetailsTechCard detailsDomaine = DetailsTechCardFactory.detailsTechCardDTOToDetailsTechCard(detailsDto, new DetailsTechCard());
                detailsDomaine.setTechCard(domaine);
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
                
                detailsDomaine.setCodeTechCard(detailsDto.getCodeTechCard());
                if (detailsDomaine.getCodeTechCard() != null) {
                    detailsDomaine.setTechCard(TechCardFactory.createTechCardByCode(detailsDto.getCodeTechCard()));
                }
                
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                
                detailsTechCardRepo.save(detailsDomaine); // Assuming you have a detailsTechCardRepo

            }
            
        }
        
        return TechCardFactory.techCardToTechCardDTO(domaine);
    }
    
    public void deleteTechCard(Integer code) {
        Preconditions.checkArgument(techCardRepo.existsById(code), "error.TechCardNotFound");
        List<PlanRepa> plRepa = planRepaRepo.findByCodeTechCard(code);
        
        Preconditions.checkArgument(plRepa.isEmpty(), "error.TechCardUsedInPlanRepa");
        
        detailsTechCardRepo.deleteByCodeTechCard(code);
        techCardRepo.deleteById(code);
    }
    
    public TechCardDTO update(TechCardDTO dto) {
        TechCard domaine = techCardRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = TechCardFactory.techCardDTOToTechCard(dto, domaine);
        domaine = techCardRepo.save(domaine);
        
        detailsTechCardRepo.deleteByCodeTechCard(domaine.getCode());
        
        if (dto.getDetailsTechCardsCardDTOs() != null) {
            
            List<DetailsTechCardDTO> detailsTechCardDTOs = dto.getDetailsTechCardsCardDTOs();
            for (DetailsTechCardDTO detailsDto : detailsTechCardDTOs) {
                DetailsTechCard detailsDomaine = DetailsTechCardFactory.detailsTechCardDTOToDetailsTechCard(detailsDto, new DetailsTechCard());
                detailsDomaine.setTechCard(domaine);
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
                
                detailsDomaine.setCodeTechCard(detailsDto.getCodeTechCard());
                if (detailsDomaine.getCodeTechCard() != null) {
                    detailsDomaine.setTechCard(TechCardFactory.createTechCardByCode(detailsDto.getCodeTechCard()));
                }
                
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                
                detailsTechCardRepo.save(detailsDomaine); // Assuming you have a detailsTechCardRepo

            }
            
        } else {
            throw new IllegalArgumentException("error.DetailsFactureNotFound");
        }
        
        TechCardDTO resultDTO = TechCardFactory.techCardToTechCardDTO(domaine);
        return resultDTO;
    }
    
}
