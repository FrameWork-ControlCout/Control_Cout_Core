/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsCalculeConsommation;
import com.FrameWork.ControlCout.Cout.domaine.CalculeConsommation;
import com.FrameWork.ControlCout.Cout.dto.DetailsCalculeConsommationDTO;
import com.FrameWork.ControlCout.Cout.dto.CalculeConsommationDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsCalculeConsommationFactory;
import com.FrameWork.ControlCout.Cout.factory.CalculeConsommationFactory;
import com.FrameWork.ControlCout.Cout.repository.DetailsCalculeConsommationRepo;
import com.FrameWork.ControlCout.Cout.repository.CalculeConsommationRepo;
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
public class CalculeConsommationService {

    private final Logger log = LoggerFactory.getLogger(CalculeConsommationService.class);

    private final CalculeConsommationRepo calculeConsommationRepo;
    private final DetailsCalculeConsommationRepo detailsCalculeConsommationRepo;

    public CalculeConsommationService(CalculeConsommationRepo calculeConsommationRepo, DetailsCalculeConsommationRepo detailsCalculeConsommationRepo) {
        this.calculeConsommationRepo = calculeConsommationRepo;
        this.detailsCalculeConsommationRepo = detailsCalculeConsommationRepo;
    }
  

   

    @Transactional(readOnly = true)
    public List<CalculeConsommationDTO> findAllCalculeConsommation() {

        List<CalculeConsommation> domaine = calculeConsommationRepo.findAll(Sort.by("code").descending());

        return CalculeConsommationFactory.listCalculeConsommationToCalculeConsommationDTOs(domaine);

    }
    
    
      @Transactional(readOnly = true)
    public List<CalculeConsommationDTO> findAllCalculeConsommationByActif(Boolean actif) {

        List<CalculeConsommation> domaine = calculeConsommationRepo.findByActif(actif);

        return CalculeConsommationFactory.listCalculeConsommationToCalculeConsommationDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public CalculeConsommationDTO findOne(Integer code) {
        CalculeConsommation domaine = calculeConsommationRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.CalculeConsommationNotFound");
        return CalculeConsommationFactory.calculeConsommationToCalculeConsommationDTO(domaine);

    }

    public CalculeConsommationDTO save(CalculeConsommationDTO dto) {

        CalculeConsommation domaine = CalculeConsommationFactory.calculeConsommationDTOToCalculeConsommation(dto, new CalculeConsommation());

  
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = calculeConsommationRepo.save(domaine);

        //DetailsCalculeConsommation 
        if (dto.getDetailsCalculeConsommationsCardDTOs()!= null) {

            List<DetailsCalculeConsommationDTO> detailsCalculeConsommationDTOs = dto.getDetailsCalculeConsommationsCardDTOs();
            for (DetailsCalculeConsommationDTO detailsDto : detailsCalculeConsommationDTOs) {
                DetailsCalculeConsommation detailsDomaine = DetailsCalculeConsommationFactory.detailsCalculeConsommationDTOToDetailsCalculeConsommation(detailsDto, new DetailsCalculeConsommation());
                detailsDomaine.setCalculeConsommation(domaine);
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

                detailsDomaine.setCodeCalculeConsommation(detailsDto.getCodeCalculeConsommation());
                if (detailsDomaine.getCodeCalculeConsommation() != null) {
                    detailsDomaine.setCalculeConsommation(CalculeConsommationFactory.createCalculeConsommationByCode(detailsDto.getCodeCalculeConsommation()));
                }

                detailsDomaine.setCodeUniteConso(detailsDto.getCodeUniteConso());
                if (detailsDomaine.getCodeUniteConso()!= null) {
                    detailsDomaine.setUniteConso(UniteFactory.createUniteByCode(detailsDto.getCodeUniteConso()));
                }

                detailsCalculeConsommationRepo.save(detailsDomaine); // Assuming you have a detailsCalculeConsommationRepo

            }

        }

        return CalculeConsommationFactory.calculeConsommationToCalculeConsommationDTO(domaine);
    }

    public void deleteCalculeConsommation(Integer code) {
        Preconditions.checkArgument(calculeConsommationRepo.existsById(code), "error.CalculeConsommationNotFound");
        calculeConsommationRepo.deleteById(code);
    }

    public CalculeConsommationDTO update(CalculeConsommationDTO dto) {
        CalculeConsommation domaine = calculeConsommationRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = CalculeConsommationFactory.calculeConsommationDTOToCalculeConsommation(dto, domaine);
        domaine = calculeConsommationRepo.save(domaine);

        detailsCalculeConsommationRepo.deleteByCodeCalculeConsommation(domaine.getCode());

        if (dto.getDetailsCalculeConsommationsCardDTOs() != null) {

            List<DetailsCalculeConsommationDTO> detailsCalculeConsommationDTOs = dto.getDetailsCalculeConsommationsCardDTOs();
            for (DetailsCalculeConsommationDTO detailsDto : detailsCalculeConsommationDTOs) {
                DetailsCalculeConsommation detailsDomaine = DetailsCalculeConsommationFactory.detailsCalculeConsommationDTOToDetailsCalculeConsommation(detailsDto, new DetailsCalculeConsommation());
                detailsDomaine.setCalculeConsommation(domaine);
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

                detailsCalculeConsommationRepo.save(detailsDomaine); // Assuming you have a detailsCalculeConsommationRepo

            }

        } else {
            throw new IllegalArgumentException("error.DetailsConsommationNotFound");
        }

        CalculeConsommationDTO resultDTO = CalculeConsommationFactory.calculeConsommationToCalculeConsommationDTO(domaine);
        return resultDTO;
    }

}
