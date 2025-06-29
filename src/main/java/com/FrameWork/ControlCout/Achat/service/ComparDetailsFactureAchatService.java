/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.ComparDetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.dto.ComparDetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.ComparDetailsFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.ComparFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.ComparDetailsFactureAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.ComparFactureAchatRepo;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ComparDetailsFactureAchatService {

    private final ComparFactureAchatRepo comparfactureAchatRepo;
    private final ComparDetailsFactureAchatRepo compardetailsFactureAchatRepo;

    public ComparDetailsFactureAchatService(ComparFactureAchatRepo comparfactureAchatRepo, ComparDetailsFactureAchatRepo compardetailsFactureAchatRepo) {
        this.comparfactureAchatRepo = comparfactureAchatRepo;
        this.compardetailsFactureAchatRepo = compardetailsFactureAchatRepo;
    }

     
    @Transactional(readOnly = true)
    public List<ComparDetailsFactureAchatDTO> findAllComparDetailsFactureAchat() {
        return ComparDetailsFactureAchatFactory.listComparDetailsFactureAchatToDetailsADmissionDTOs(compardetailsFactureAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public ComparDetailsFactureAchatDTO findOne(Integer code) {
        ComparDetailsFactureAchat domaine = compardetailsFactureAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureAchatNotFound");
        return ComparDetailsFactureAchatFactory.ComparDetailsFactureAchatToComparDetailsFactureAchatDTONew(domaine);
    }
 

    
    
    @Transactional(readOnly = true)
    public List<ComparDetailsFactureAchatDTO> findByCodeComparFactureAchat(Integer codeComparFacture) {
        List<ComparDetailsFactureAchat> domaine = compardetailsFactureAchatRepo.findByCodeComparFactureAchat(codeComparFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureAchatNotFound");
        return ComparDetailsFactureAchatFactory.listComparDetailsFactureAchatToDetailsADmissionDTOs(domaine);
    }
 

//
    public ComparDetailsFactureAchatDTO save(ComparDetailsFactureAchatDTO dto) {
        ComparDetailsFactureAchat domaine = ComparDetailsFactureAchatFactory.detailsFactureAchatDTOToComparDetailsFactureAchat(dto, new ComparDetailsFactureAchat());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = compardetailsFactureAchatRepo.save(domaine);
        return ComparDetailsFactureAchatFactory.ComparDetailsFactureAchatToComparDetailsFactureAchatDTONew(domaine);
    }

    public List<ComparDetailsFactureAchatDTO> saveList(List<ComparDetailsFactureAchatDTO> dto) {
        List<ComparDetailsFactureAchatDTO> detailsFactureAchatDTOs = dto;

        for (ComparDetailsFactureAchatDTO detailsDto : detailsFactureAchatDTOs) {
            ComparDetailsFactureAchat detailsDomaine = ComparDetailsFactureAchatFactory.detailsFactureAchatDTOToComparDetailsFactureAchat(detailsDto, new ComparDetailsFactureAchat()); // Assuming you have this factory method

         
            detailsDomaine.setDateCreate(new Date());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setMontantTTCGros(detailsDto.getMontantTTCGros());
            detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
            detailsDomaine.setPrixUnitaireGros(detailsDto.getPrixUnitaireGros());
            detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
            detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
            detailsDomaine.setPrixUnitaire(detailsDto.getPrixUnitaire());

            detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
            if (detailsDomaine.getCodeArticle() != null) {
                detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
            }

            detailsDomaine.setCodeFactureAchat(detailsDto.getCodeFactureAchat());
            if (detailsDomaine.getCodeFactureAchat() != null) {
                detailsDomaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(detailsDto.getCodeFactureAchat()));
            }
            
            
                  detailsDomaine.setCodeComparFactureAchat(detailsDto.getCodeComparFactureAchat());
            if (detailsDomaine.getCodeComparFactureAchat() != null) {
                detailsDomaine.setComparFactureAchat(ComparFactureAchatFactory.createComparFactureAchatByCode(detailsDto.getCodeComparFactureAchat()));
            }

         
            
               detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
            if (detailsDomaine.getCodeFournisseur() != null) {
                detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
            }
            
            
               detailsDomaine.setCodeFournisseurCompar(detailsDto.getCodeFournisseurCompar());
            if (detailsDomaine.getCodeFournisseurCompar() != null) {
                detailsDomaine.setFournisseurCompar(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseurCompar()));
            }
            detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
            if (detailsDomaine.getCodeUnite() != null) {
                detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
            }

            compardetailsFactureAchatRepo.save(detailsDomaine);  

        }

        return detailsFactureAchatDTOs;
    }

    public ComparDetailsFactureAchatDTO update(ComparDetailsFactureAchatDTO dto) {
        ComparDetailsFactureAchat domaine = compardetailsFactureAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = ComparDetailsFactureAchatFactory.detailsFactureAchatDTOToComparDetailsFactureAchat(dto, domaine);
        domaine = compardetailsFactureAchatRepo.save(domaine);
        ComparDetailsFactureAchatDTO resultDTO = ComparDetailsFactureAchatFactory.ComparDetailsFactureAchatToComparDetailsFactureAchatDTONew(domaine);
        return resultDTO;
    }

    public void deleteComparDetailsFactureAchat(Integer code) {
        Preconditions.checkArgument(compardetailsFactureAchatRepo.existsById(code), "error.DetailsFactureAchatNotFound");
        compardetailsFactureAchatRepo.deleteById(code);
    }

}
