/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import com.FrameWork.ControlCout.Cout.dto.DetailsFicheTechDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsFicheTechFactory;
import com.FrameWork.ControlCout.Cout.factory.FicheTechFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
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
public class DetailsFicheTechService {

    private final FicheTechRepo factureAchatRepo;
    private final DetailsFicheTechRepo detailsFicheTechniqueRepo;

    public DetailsFicheTechService(FicheTechRepo factureAchatRepo, DetailsFicheTechRepo detailsFicheTechniqueRepo) {
        this.factureAchatRepo = factureAchatRepo;
        this.detailsFicheTechniqueRepo = detailsFicheTechniqueRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsFicheTechDTO> findAllDetailsFicheTechnique() {
        return DetailsFicheTechFactory.listDetailsFicheTechniqueToDetailsADmissionDTOs(detailsFicheTechniqueRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsFicheTechDTO findOne(Integer code) {
        DetailsFicheTech domaine = detailsFicheTechniqueRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsFicheTechniqueNotFound");
        return DetailsFicheTechFactory.DetailsFicheTechniqueToDetailsFicheTechniqueDTONew(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsFicheTechDTO> findByCodeFicheTechnique(Integer codeFacture) {
        List<DetailsFicheTech> domaine = detailsFicheTechniqueRepo.findByCodeFicheTechnique(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsFicheTechniqueNotFound");
        return DetailsFicheTechFactory.listDetailsFicheTechniqueToDetailsADmissionDTOs(domaine);
    }

//
    public DetailsFicheTechDTO save(DetailsFicheTechDTO dto) {
        DetailsFicheTech domaine = DetailsFicheTechFactory.detailsFicheTechniqueDTOToDetailsFicheTechnique(dto, new DetailsFicheTech());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsFicheTechniqueRepo.save(domaine);
        return DetailsFicheTechFactory.DetailsFicheTechniqueToDetailsFicheTechniqueDTONew(domaine);
    }

    public List<DetailsFicheTechDTO> saveList(List<DetailsFicheTechDTO> dto) {
        List<DetailsFicheTechDTO> detailsFicheTechniqueDTOs = dto;

        for (DetailsFicheTechDTO detailsDto : detailsFicheTechniqueDTOs) {
            DetailsFicheTech detailsDomaine = DetailsFicheTechFactory.detailsFicheTechniqueDTOToDetailsFicheTechnique(detailsDto, new DetailsFicheTech()); // Assuming you have this factory method

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
            detailsFicheTechniqueRepo.save(detailsDomaine); 
        }

        return detailsFicheTechniqueDTOs;
    }

    public DetailsFicheTechDTO update(DetailsFicheTechDTO dto) {
        DetailsFicheTech domaine = detailsFicheTechniqueRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsFicheTechniqueNotFound");
        domaine = DetailsFicheTechFactory.detailsFicheTechniqueDTOToDetailsFicheTechnique(dto, domaine);
        domaine = detailsFicheTechniqueRepo.save(domaine);
        DetailsFicheTechDTO resultDTO = DetailsFicheTechFactory.DetailsFicheTechniqueToDetailsFicheTechniqueDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsFicheTechnique(Integer code) {
        Preconditions.checkArgument(detailsFicheTechniqueRepo.existsById(code), "error.DetailsFicheTechniqueNotFound");
        detailsFicheTechniqueRepo.deleteById(code);
    }

}
