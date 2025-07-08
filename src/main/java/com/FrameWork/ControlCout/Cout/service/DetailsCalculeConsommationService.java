/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsCalculeConsommation;
import com.FrameWork.ControlCout.Cout.dto.DetailsCalculeConsommationDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsCalculeConsommationFactory;
import com.FrameWork.ControlCout.Cout.factory.CalculeConsommationFactory;
import com.FrameWork.ControlCout.Cout.repository.DetailsCalculeConsommationRepo;
import com.FrameWork.ControlCout.Cout.repository.CalculeConsommationRepo;
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
public class DetailsCalculeConsommationService {

    private final CalculeConsommationRepo factureAchatRepo;
    private final DetailsCalculeConsommationRepo detailsCalculeConsommationRepo;

    public DetailsCalculeConsommationService(CalculeConsommationRepo factureAchatRepo, DetailsCalculeConsommationRepo detailsCalculeConsommationRepo) {
        this.factureAchatRepo = factureAchatRepo;
        this.detailsCalculeConsommationRepo = detailsCalculeConsommationRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsCalculeConsommationDTO> findAllDetailsCalculeConsommation() {
        return DetailsCalculeConsommationFactory.listDetailsCalculeConsommationToDetailsADmissionDTOs(detailsCalculeConsommationRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsCalculeConsommationDTO findOne(Integer code) {
        DetailsCalculeConsommation domaine = detailsCalculeConsommationRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsCalculeConsommationNotFound");
        return DetailsCalculeConsommationFactory.DetailsCalculeConsommationToDetailsCalculeConsommationDTONew(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsCalculeConsommationDTO> findByCodeCalculeConsommation(Integer codeCalculeConsommation) {
        List<DetailsCalculeConsommation> domaine = detailsCalculeConsommationRepo.findByCodeCalculeConsommation(codeCalculeConsommation);
        Preconditions.checkArgument(domaine != null, "error.DetailsCalculeConsommationNotFound");
        return DetailsCalculeConsommationFactory.listDetailsCalculeConsommationToDetailsADmissionDTOs(domaine);
    }

//
    public DetailsCalculeConsommationDTO save(DetailsCalculeConsommationDTO dto) {
        DetailsCalculeConsommation domaine = DetailsCalculeConsommationFactory.detailsCalculeConsommationDTOToDetailsCalculeConsommation(dto, new DetailsCalculeConsommation());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsCalculeConsommationRepo.save(domaine);
        return DetailsCalculeConsommationFactory.DetailsCalculeConsommationToDetailsCalculeConsommationDTONew(domaine);
    }

    public List<DetailsCalculeConsommationDTO> saveList(List<DetailsCalculeConsommationDTO> dto) {
        List<DetailsCalculeConsommationDTO> detailsCalculeConsommationDTOs = dto;

        for (DetailsCalculeConsommationDTO detailsDto : detailsCalculeConsommationDTOs) {
            DetailsCalculeConsommation detailsDomaine = DetailsCalculeConsommationFactory.detailsCalculeConsommationDTOToDetailsCalculeConsommation(detailsDto, new DetailsCalculeConsommation()); // Assuming you have this factory method

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

            detailsDomaine.setCodeCalculeConsommation(detailsDto.getCodeCalculeConsommation());
            if (detailsDomaine.getCodeCalculeConsommation() != null) {
                detailsDomaine.setCalculeConsommation(CalculeConsommationFactory.createCalculeConsommationByCode(detailsDto.getCodeCalculeConsommation()));
            }

            detailsDomaine.setCodeUniteConso(detailsDto.getCodeUniteConso());
            if (detailsDomaine.getCodeUniteConso()!= null) {
                detailsDomaine.setUniteConso(UniteFactory.createUniteByCode(detailsDto.getCodeUniteConso()));
            } 
            detailsCalculeConsommationRepo.save(detailsDomaine); 
        }

        return detailsCalculeConsommationDTOs;
    }

    public DetailsCalculeConsommationDTO update(DetailsCalculeConsommationDTO dto) {
        DetailsCalculeConsommation domaine = detailsCalculeConsommationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.CalculeConsommationNotFound");
        domaine = DetailsCalculeConsommationFactory.detailsCalculeConsommationDTOToDetailsCalculeConsommation(dto, domaine);
        domaine = detailsCalculeConsommationRepo.save(domaine);
        DetailsCalculeConsommationDTO resultDTO = DetailsCalculeConsommationFactory.DetailsCalculeConsommationToDetailsCalculeConsommationDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsCalculeConsommation(Integer code) {
        Preconditions.checkArgument(detailsCalculeConsommationRepo.existsById(code), "error.DetailsCalculeConsommationNotFound");
        detailsCalculeConsommationRepo.deleteById(code);
    }

}
