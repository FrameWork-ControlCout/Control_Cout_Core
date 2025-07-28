/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.repository.ConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.repository.PlanRepaRepo;
import com.FrameWork.ControlCout.Cout.service.ConsoStandardService;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
import com.FrameWork.ControlCout.Parametrage.dto.TraceSocieteDTO;
import com.FrameWork.ControlCout.Parametrage.factory.FamilleArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.SocieteRepo;
import com.FrameWork.ControlCout.Parametrage.repository.TraceSocieteRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
public class SocieteService {

    private final CompteurService compteurService;
    private final SocieteRepo societeRepo;
    private final TraceSocieteService traceSocieteService;
    private final TraceSocieteRepo traceSocieteRepo;
    private final PlanRepaRepo planRepaRepo;
    private final ConsoStandardService consoStandardService;

    public SocieteService(CompteurService compteurService, SocieteRepo societeRepo, TraceSocieteService traceSocieteService, TraceSocieteRepo traceSocieteRepo, PlanRepaRepo planRepaRepo, ConsoStandardService consoStandardService) {
        this.compteurService = compteurService;
        this.societeRepo = societeRepo;
        this.traceSocieteService = traceSocieteService;
        this.traceSocieteRepo = traceSocieteRepo;
        this.planRepaRepo = planRepaRepo;
        this.consoStandardService = consoStandardService;
    }

    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSociete() {
        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findAll());

    }

    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSocieteByActif(Boolean actif) {
        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findByActif(actif));

    }

    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSocieteByActifAndDepot(Boolean actif, Integer codeDepot) {
        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findByActifAndCodeDepot(actif, codeDepot));

    }

//    public List<SocieteDTO> findAllSocieteByActifAndSociete(Boolean actif, Integer codeSociete) {
//        return SocieteFactory.listSocieteToSocieteDTOs(societeRepo.findByActifAndCode(actif, codeSociete));
//
//    }
    @Transactional(readOnly = true)
    public List<SocieteDTO> findAllSocieteByActifAndSociete(Boolean actif, Integer codeSociete) {

        List<Societe> domaine = societeRepo.findByActifAndCode(actif, codeSociete);

        return SocieteFactory.listSocieteToSocieteDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public SocieteDTO findOne(Integer code) {
        Societe domaine = societeRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public SocieteDTO save(SocieteDTO dto) {
        Societe domaine = SocieteFactory.societeDTOToSociete(dto, new Societe());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieSociete");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine = societeRepo.save(domaine);

        TraceSociete traceSociete = new TraceSociete();
        traceSociete.setCodeSociete(domaine.getCode());
        if (traceSociete.getCodeSociete() != null) {
            traceSociete.setSociete(SocieteFactory.createSocieteByCode(domaine.getCode()));

        }
        traceSociete.setDateCreate(new Date());
        traceSociete.setNbrePersonNew(domaine.getNbrePerson());
        traceSociete.setNbrePersonOld(0);

        traceSociete.setUserCreate(domaine.getUserCreate());
        traceSocieteRepo.save(traceSociete);

        return SocieteFactory.societeToSocieteDTO(domaine);
    }

    public Societe update(SocieteDTO dto) {
        Societe domaine = societeRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.SocieteNotFound");
        dto.setCode(domaine.getCode());

//         LocalDateTime todayAtMidnight = LocalDate.now().atStartOfDay(); 
//       DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"); 
//        List<PlanRepa> planRepas  = planRepaRepo.findAllDateAndCodeSocieteIn(todayAtMidnight.format(isoFormatter), domaine.getCode());
//        
//        
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        consoStandardService.recalculateFutureDailyConsumption(
                dto.getCode(),
                dto.getNbrePerson(),
                today
        );
        
        consoStandardService.UpdatedNbrePerson(dto.getCode(),dto.getNbrePerson());

// 2. Convert the LocalDateTime to a legacy java.util.Date
// You must specify a time zone for an accurate conversion.
        LocalDateTime todayAtMidnight = LocalDate.now().atStartOfDay();

        Date startDateAsLegacyDate = Date.from(
                todayAtMidnight.atZone(ZoneId.systemDefault()).toInstant()
        );

        List<PlanRepa> planRepas = planRepaRepo.findAllDateAndCodeSocieteIn(
                startDateAsLegacyDate, // Passing the converted java.util.Date object
                domaine.getCode()
        );
        for (PlanRepa pl : planRepas) {
            pl.setNbrePerson(dto.getNbrePerson());
        }
        List<PlanRepa> listPL = planRepaRepo.saveAll(planRepas);

        TraceSociete traceSociete = new TraceSociete();
        traceSociete.setCodeSociete(dto.getCode());
        if (traceSociete.getCodeSociete() != null) {
            traceSociete.setSociete(SocieteFactory.createSocieteByCode(dto.getCode()));
        }

        traceSociete.setDateCreate(new Date());
        traceSociete.setNbrePersonNew(dto.getNbrePerson());
        traceSociete.setNbrePersonOld(domaine.getNbrePerson());
        traceSociete.setDateUpdate(new Date());

        traceSociete.setUserCreate(dto.getUserCreate());
        traceSocieteRepo.save(traceSociete);

        SocieteFactory.societeDTOToSociete(dto, domaine);

        return societeRepo.save(domaine);
    }

    public void deleteSociete(Integer code) {
        Preconditions.checkArgument(societeRepo.existsById(code), "error.SocieteNotFound");
        societeRepo.deleteById(code);
    }

}
