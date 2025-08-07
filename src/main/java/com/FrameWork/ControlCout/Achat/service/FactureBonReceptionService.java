/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.repository.BonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.DetailsOrderAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.OrderAchatRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Stock.service.GestionStockService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
public class FactureBonReceptionService {
    
    private final Logger log = LoggerFactory.getLogger(FactureBonReceptionService.class);
    
    private final FactureBonReceptionRepo factureBonReceptionRepo;
    private final CompteurService compteurService;
    
    private final BonReceptionRepo bonReceptionRepo;
    
    private final GestionStockService gestionStockService;
    private final DetailsFactureBonReceptionRepo detailsFactureBonReceptionRepo;
    
    public FactureBonReceptionService(FactureBonReceptionRepo factureBonReceptionRepo, CompteurService compteurService, BonReceptionRepo bonReceptionRepo, GestionStockService gestionStockService, DetailsFactureBonReceptionRepo detailsFactureBonReceptionRepo) {
        this.factureBonReceptionRepo = factureBonReceptionRepo;
        this.compteurService = compteurService;
        this.bonReceptionRepo = bonReceptionRepo;
        this.gestionStockService = gestionStockService;
        this.detailsFactureBonReceptionRepo = detailsFactureBonReceptionRepo;
    }

    //<editor-fold defaultstate="collapsed" desc="Read-Only Methods">
    @Transactional(readOnly = true)
    public List<FactureBonReceptionDTO> findAllFactureBonReception() {
        return FactureBonReceptionFactory.listFactureBonReceptionToFactureBonReceptionDTOs(
                factureBonReceptionRepo.findAll(Sort.by("code").descending())
        );
    }
    
    @Transactional(readOnly = true)
    public FactureBonReceptionDTO findOne(Integer code) {
        FactureBonReception domaine = factureBonReceptionRepo.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("error.FactureBonReceptionNotFound"));
        return FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine);
    }
    
    @Transactional(readOnly = true)
    public List<FactureBonReceptionDTO> findFactureBonReceptionByCodeFournisseur(Integer codeFournisseur) {
        return FactureBonReceptionFactory.listFactureBonReceptionToFactureBonReceptionDTOs(
                factureBonReceptionRepo.findByCodeFournisseur(codeFournisseur)
        );
    }
    
    @Transactional(readOnly = true)
    public Collection<FactureBonReceptionDTO> findFactureBonReceptionByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        return FactureBonReceptionFactory.CollectionFactureBonReceptionToFactureBonReceptionDTOs(
                factureBonReceptionRepo.findAllByDateCreateBetween(dateDebut, dateFin)
        );
    }
    
    @Transactional
    public FactureBonReceptionDTO save(FactureBonReceptionDTO dto) {
        FactureBonReception domaine = FactureBonReceptionFactory.factureBonReceptionDTOToFactureBonReception(dto, new FactureBonReception());
        
        Compteur CompteurCodeSaisie = compteurService.findOne("FactureBREC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        
        domaine = factureBonReceptionRepo.save(domaine);
        
        if (dto.getDetailsFactureBonReceptionDTOs() == null || dto.getDetailsFactureBonReceptionDTOs().isEmpty()) {
            return FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine);
        }

        // 2. CREATE BON RECEPTION DETAILS
        List<DetailsFactureBonReception> newDetailsList = new ArrayList<>();
        for (DetailsFactureBonReceptionDTO detailsDto : dto.getDetailsFactureBonReceptionDTOs()) {
            DetailsFactureBonReception detailsDomaine = DetailsFactureBonReceptionFactory.detailsfactureBonReceptionDTOTodetailsFactureBonReception(detailsDto, new DetailsFactureBonReception());
            detailsDomaine.setFactureBonReception(domaine);
            detailsDomaine.setDateCreate(new Date());  // Set in domaine
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            
            newDetailsList.add(detailsDomaine);
        }
        detailsFactureBonReceptionRepo.saveAll(newDetailsList);
        
        BonReception domaineToUpdate = bonReceptionRepo.findByCode(domaine.getCodeBonReception());
        domaineToUpdate.setHaveFBR(Boolean.TRUE);
        bonReceptionRepo.save(domaineToUpdate);

        // 3. Update haveOA flag - MOVED outside the details loop and OPTIMIZED
        gestionStockService.createMouvementFrsFromFactureBonReception(domaine);
        
        return FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine);
    }
    
    @Transactional
    public FactureBonReceptionDTO update(FactureBonReceptionDTO dto) {
        // 1. --- FETCH EXISTING ENTITY ---
        Preconditions.checkArgument(dto.getCode() != null, "error.FactureBonReceptionCodeRequiredForUpdate");
        FactureBonReception domaine = factureBonReceptionRepo.findById(dto.getCode())
                .orElseThrow(() -> new IllegalArgumentException("error.FactureBonReceptionNotFound"));
        
        domaine = FactureBonReceptionFactory.factureBonReceptionDTOToFactureBonReception(dto, domaine);
//        domaine.setDateCreate(new Date());  // Set in domaine
//        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = factureBonReceptionRepo.save(domaine);
        
        if (dto.getDetailsFactureBonReceptionDTOs() == null || dto.getDetailsFactureBonReceptionDTOs().isEmpty()) {
            return FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine);
        }

        // 2. CREATE BON RECEPTION DETAILS
        List<DetailsFactureBonReception> newDetailsList = new ArrayList<>();
        for (DetailsFactureBonReceptionDTO detailsDto : dto.getDetailsFactureBonReceptionDTOs()) {
            DetailsFactureBonReception detailsDomaine = DetailsFactureBonReceptionFactory.detailsfactureBonReceptionDTOTodetailsFactureBonReception(detailsDto, new DetailsFactureBonReception());
            detailsDomaine.setFactureBonReception(domaine);
            detailsDomaine.setDateCreate(new Date());  // Set in domaine
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            
            newDetailsList.add(detailsDomaine);
        }
        detailsFactureBonReceptionRepo.saveAll(newDetailsList);
        
        BonReception domaineToUpdate = bonReceptionRepo.findByCode(domaine.getCodeBonReception());
        domaineToUpdate.setHaveFBR(Boolean.TRUE);
        bonReceptionRepo.save(domaineToUpdate);

        // 3. Update haveOA flag - MOVED outside the details loop and OPTIMIZED
        gestionStockService.createMouvementFrsFromFactureBonReception(domaine);
        
        return FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine);
    }
    
    @Transactional
    public void deleteFactureBonReception(Integer code) {
        FactureBonReception domaine = factureBonReceptionRepo.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("error.FactureBonReceptionNotFound"));
        
        BonReception domaineToUpdate = bonReceptionRepo.findByCode(domaine.getCodeBonReception());
        domaineToUpdate.setHaveFBR(Boolean.FALSE);
        bonReceptionRepo.save(domaineToUpdate);
        
        detailsFactureBonReceptionRepo.deleteByCodeFactureBonReception(code);
        gestionStockService.DeleteMouvementFrsFromFactureBonReception(domaine);
        factureBonReceptionRepo.deleteById(code);
    }
    
    @Transactional(readOnly = true)
    public List<FactureBonReception> findOneByCodeEdition(Integer code) {
        List<FactureBonReception> bonReceptions = factureBonReceptionRepo.findAllByCode(code);
        return bonReceptions;
    }
    
}
