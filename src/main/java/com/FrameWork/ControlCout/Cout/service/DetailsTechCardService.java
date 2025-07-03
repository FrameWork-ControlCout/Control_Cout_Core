/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsTechCard;
import com.FrameWork.ControlCout.Cout.dto.DetailsTechCardDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsTechCardFactory;
import com.FrameWork.ControlCout.Cout.factory.TechCardFactory;
import com.FrameWork.ControlCout.Cout.repository.DetailsTechCardRepo;
import com.FrameWork.ControlCout.Cout.repository.TechCardRepo;
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
public class DetailsTechCardService {

    private final TechCardRepo factureAchatRepo;
    private final DetailsTechCardRepo detailsTechCardRepo;

    public DetailsTechCardService(TechCardRepo factureAchatRepo, DetailsTechCardRepo detailsTechCardRepo) {
        this.factureAchatRepo = factureAchatRepo;
        this.detailsTechCardRepo = detailsTechCardRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsTechCardDTO> findAllDetailsTechCard() {
        return DetailsTechCardFactory.listDetailsTechCardToDetailsADmissionDTOs(detailsTechCardRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsTechCardDTO findOne(Integer code) {
        DetailsTechCard domaine = detailsTechCardRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsTechCardNotFound");
        return DetailsTechCardFactory.DetailsTechCardToDetailsTechCardDTONew(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsTechCardDTO> findByCodeTechCard(Integer codeFacture) {
        List<DetailsTechCard> domaine = detailsTechCardRepo.findByCodeTechCard(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsTechCardNotFound");
        return DetailsTechCardFactory.listDetailsTechCardToDetailsADmissionDTOs(domaine);
    }

//
    public DetailsTechCardDTO save(DetailsTechCardDTO dto) {
        DetailsTechCard domaine = DetailsTechCardFactory.detailsTechCardDTOToDetailsTechCard(dto, new DetailsTechCard());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsTechCardRepo.save(domaine);
        return DetailsTechCardFactory.DetailsTechCardToDetailsTechCardDTONew(domaine);
    }

    public List<DetailsTechCardDTO> saveList(List<DetailsTechCardDTO> dto) {
        List<DetailsTechCardDTO> detailsTechCardDTOs = dto;

        for (DetailsTechCardDTO detailsDto : detailsTechCardDTOs) {
            DetailsTechCard detailsDomaine = DetailsTechCardFactory.detailsTechCardDTOToDetailsTechCard(detailsDto, new DetailsTechCard()); // Assuming you have this factory method

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
            detailsTechCardRepo.save(detailsDomaine); 
        }

        return detailsTechCardDTOs;
    }

    public DetailsTechCardDTO update(DetailsTechCardDTO dto) {
        DetailsTechCard domaine = detailsTechCardRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsTechCardFactory.detailsTechCardDTOToDetailsTechCard(dto, domaine);
        domaine = detailsTechCardRepo.save(domaine);
        DetailsTechCardDTO resultDTO = DetailsTechCardFactory.DetailsTechCardToDetailsTechCardDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsTechCard(Integer code) {
        Preconditions.checkArgument(detailsTechCardRepo.existsById(code), "error.DetailsTechCardNotFound");
        detailsTechCardRepo.deleteById(code);
    }

}
