/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureAchatRepo;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
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
public class DetailsFactureAchatService {

    private final FactureAchatRepo factureAchatRepo;
    private final DetailsFactureAchatRepo detailsFactureAchatRepo;

    public DetailsFactureAchatService(FactureAchatRepo factureAchatRepo, DetailsFactureAchatRepo detailsFactureAchatRepo) {
        this.factureAchatRepo = factureAchatRepo;
        this.detailsFactureAchatRepo = detailsFactureAchatRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsFactureAchatDTO> findAllDetailsFactureAchat() {
        return DetailsFactureAchatFactory.listDetailsFactureAchatToDetailsADmissionDTOs(detailsFactureAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsFactureAchatDTO findOne(Integer code) {
        DetailsFactureAchat domaine = detailsFactureAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureAchatNotFound");
        return DetailsFactureAchatFactory.DetailsFactureAchatToDetailsFactureAchatDTONew(domaine);
    }

//
    public DetailsFactureAchatDTO save(DetailsFactureAchatDTO dto) {
        DetailsFactureAchat domaine = DetailsFactureAchatFactory.detailsFactureAchatDTOToDetailsFactureAchat(dto, new DetailsFactureAchat());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsFactureAchatRepo.save(domaine);
        return DetailsFactureAchatFactory.DetailsFactureAchatToDetailsFactureAchatDTONew(domaine);
    }

    public List<DetailsFactureAchatDTO> saveList(List<DetailsFactureAchatDTO> dto) {
        List<DetailsFactureAchatDTO> detailsFactureAchatDTOs = dto;

        for (DetailsFactureAchatDTO detailsDto : detailsFactureAchatDTOs) {
            DetailsFactureAchat detailsDomaine = DetailsFactureAchatFactory.detailsFactureAchatDTOToDetailsFactureAchat(detailsDto, new DetailsFactureAchat()); // Assuming you have this factory method

         
            detailsDomaine.setDateCreate(new Date());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setMontantHt(detailsDto.getMontantHt());
            detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
            detailsDomaine.setMontantTva(detailsDto.getMontantTva());
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

            detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
            if (detailsDomaine.getCodeFournisseur() != null) {
                detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
            }
            detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
            if (detailsDomaine.getCodeUnite() != null) {
                detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
            }

            detailsFactureAchatRepo.save(detailsDomaine);  

        }

        return detailsFactureAchatDTOs;
    }

    public DetailsFactureAchatDTO update(DetailsFactureAchatDTO dto) {
        DetailsFactureAchat domaine = detailsFactureAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsFactureAchatFactory.detailsFactureAchatDTOToDetailsFactureAchat(dto, domaine);
        domaine = detailsFactureAchatRepo.save(domaine);
        DetailsFactureAchatDTO resultDTO = DetailsFactureAchatFactory.DetailsFactureAchatToDetailsFactureAchatDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsFactureAchat(Integer code) {
        Preconditions.checkArgument(detailsFactureAchatRepo.existsById(code), "error.DetailsFactureAchatNotFound");
        detailsFactureAchatRepo.deleteById(code);
    }

}
