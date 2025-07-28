/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureGros;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureGrosDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureGrosFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureGrosFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureGrosRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureGrosRepo;
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
public class DetailsFactureGrosService {

    private final DetailsFactureGrosRepo detailsFactureGrosRepo;

    public DetailsFactureGrosService(DetailsFactureGrosRepo detailsFactureGrosRepo) {
        this.detailsFactureGrosRepo = detailsFactureGrosRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsFactureGrosDTO> findAllDetailsFactureGros() {
        return DetailsFactureGrosFactory.listDetailsFactureGrosToDetailsFactureGrosDTOs(detailsFactureGrosRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsFactureGrosDTO findOne(Integer code) {
        DetailsFactureGros domaine = detailsFactureGrosRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureGrosNotFound");
        return DetailsFactureGrosFactory.detailsfactureGrosToDetailsFactureGrosDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsFactureGrosDTO> findByCodeFactureGros(Integer codeFacture) {
        List<DetailsFactureGros> domaine = detailsFactureGrosRepo.findByCodeFactureGros(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureGrosNotFound");
        return DetailsFactureGrosFactory.listDetailsFactureGrosToDetailsFactureGrosDTOs(domaine);
    }

    public DetailsFactureGrosDTO save(DetailsFactureGrosDTO dto) {
        DetailsFactureGros domaine = DetailsFactureGrosFactory.detailsfactureGrosDTOToDetailsFactureGros(dto, new DetailsFactureGros());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsFactureGrosRepo.save(domaine);
        return DetailsFactureGrosFactory.detailsfactureGrosToDetailsFactureGrosDTO(domaine);
    }

//    public List<DetailsFactureGrosDTO> saveList(List<DetailsFactureGrosDTO> dto) {
//        List<DetailsFactureGrosDTO> detailsFactureGrosDTOs = dto;
//
//        for (DetailsFactureGrosDTO detailsDto : detailsFactureGrosDTOs) {
//            DetailsFactureGros detailsDomaine = DetailsFactureGrosFactory.detailsfactureGrosDTOTodetailsFactureGros(detailsDto, new DetailsFactureGros()); // Assuming you have this factory method
//            detailsDomaine.setDateCreate(new Date());
//            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
//            detailsDomaine.setQteBesoin(detailsDto.getQteBesoin());
//            detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
//            detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
//            if (detailsDomaine.getCodeArticle() != null) {
//                detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
//            } 
//            detailsDomaine.setCodeDetailsOrderAchat(detailsDto.getCodeDetailsOrderAchat());
//            if (detailsDomaine.getCodeDetailsOrderAchat() != null) {
//                detailsDomaine.setDetailsOrderAchat(DetailsOrderAchatFactory.createOrderAchatByCode(detailsDto.getCodeDetailsOrderAchat()));
//            } 
//            detailsDomaine.setCodeFactureGros(detailsDto.getCodeFactureGros());
//            if (detailsDomaine.getCodeFactureGros() != null) {
//                detailsDomaine.setFactureGros(FactureGrosFactory.createFactureGrosByCode(detailsDto.getCodeFactureGros()));
//            }
//
//            detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
//            if (detailsDomaine.getCodeFournisseur() != null) {
//                detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
//            }
//            detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
//            if (detailsDomaine.getCodeUnite() != null) {
//                detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
//            } 
//            detailsFactureGrosRepo.save(detailsDomaine); 
//        }
//
//        return detailsFactureGrosDTOs;
//    }

    public DetailsFactureGrosDTO update(DetailsFactureGrosDTO dto) {
        DetailsFactureGros domaine = detailsFactureGrosRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsFactureGrosFactory.detailsfactureGrosDTOToDetailsFactureGros(dto, domaine);
        domaine = detailsFactureGrosRepo.save(domaine);
        DetailsFactureGrosDTO resultDTO = DetailsFactureGrosFactory.detailsfactureGrosToDetailsFactureGrosDTO(domaine);
        return resultDTO;
    }

    public void deleteDetailsFactureGros(Integer code) {
        Preconditions.checkArgument(detailsFactureGrosRepo.existsById(code), "error.DetailsFactureGrosNotFound");
        detailsFactureGrosRepo.deleteById(code);
    }

}
