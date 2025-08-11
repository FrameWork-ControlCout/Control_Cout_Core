/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.HistoriqueFicheTechnique;
import com.FrameWork.ControlCout.Cout.dto.HistoriqueFicheTechniqueDTO;
import com.FrameWork.ControlCout.Cout.factory.HistoriqueFicheTechniqueFactory;
import com.FrameWork.ControlCout.Cout.repository.ConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.repository.HistoriqueFicheTechniqueRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import com.FrameWork.ControlCout.Parametrage.dto.TraceSocieteDTO;
import com.FrameWork.ControlCout.Parametrage.factory.TraceSocieteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.SocieteRepo;
import com.FrameWork.ControlCout.Parametrage.repository.TraceSocieteRepo;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
public class HistoriqueFicheTechniqueService {

    private final Logger log = LoggerFactory.getLogger(HistoriqueFicheTechniqueService.class);

    private final HistoriqueFicheTechniqueRepo historiqueFicheTechniqueRepo; 

    public HistoriqueFicheTechniqueService(HistoriqueFicheTechniqueRepo historiqueFicheTechniqueRepo) {
        this.historiqueFicheTechniqueRepo = historiqueFicheTechniqueRepo;
    }

  
    
    @Transactional(readOnly = true)
    public List<HistoriqueFicheTechniqueDTO> findAllHistoriqueFicheTechnique() {
        List<HistoriqueFicheTechnique> domaine = historiqueFicheTechniqueRepo.findAll(Sort.by("code").descending());
        return HistoriqueFicheTechniqueFactory.listHistoriqueFicheTechniqueniqueToHistoriqueFicheTechniqueDTOs(domaine);
    }

  

    @Transactional(readOnly = true)
    public List<HistoriqueFicheTechniqueDTO> findAllHistoriqueFicheTechniqueByCodeArticleAndCodeFicheTechnique(Integer codeArticle, Integer codeFicheTechnique) {
        

        // Call the new repository method
        List<HistoriqueFicheTechnique> domaine = historiqueFicheTechniqueRepo.findByCodeArticleAndCodeFicheTechnique(codeArticle, codeFicheTechnique);

        return HistoriqueFicheTechniqueFactory.listHistoriqueFicheTechniqueniqueToHistoriqueFicheTechniqueDTOs(domaine);
    }
    
       public HistoriqueFicheTechniqueDTO save(HistoriqueFicheTechniqueDTO dto) {
        HistoriqueFicheTechnique domaine = HistoriqueFicheTechniqueFactory.historiqueficheTechniqueDTOToHistoriqueFicheTechnique(dto, new HistoriqueFicheTechnique());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());  
        domaine = historiqueFicheTechniqueRepo.save(domaine); 
        return HistoriqueFicheTechniqueFactory.historiqueficheTechniqueToHistoriqueFicheTechniqueDTO(domaine);
    }
 
}
