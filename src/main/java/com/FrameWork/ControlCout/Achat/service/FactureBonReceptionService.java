/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO;
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

    public FactureBonReceptionService(FactureBonReceptionRepo factureBonReceptionRepo, CompteurService compteurService, BonReceptionRepo bonReceptionRepo, GestionStockService gestionStockService) {
        this.factureBonReceptionRepo = factureBonReceptionRepo;
        this.compteurService = compteurService;
        this.bonReceptionRepo = bonReceptionRepo;
        this.gestionStockService = gestionStockService;
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

//        String rawCodeString = domaine.getCodeBonReception(); // e.g., "45,46"
//        if (rawCodeString != null && !rawCodeString.trim().isEmpty()) {
//            List<Integer> codeConsoStandardList = parseCodeStringToList(rawCodeString);
//            if (!codeConsoStandardList.isEmpty()) {
//                // ONE database query to get all relevant entities
//                List<BonReception> domaineToUpdate = bonReceptionRepo.findByCodeIn(codeConsoStandardList);
//
//                // Update all entities in memory
//                for (BonReception bonReception : domaineToUpdate) {
//                    bonReception.setHaveFBR(Boolean.TRUE);
//                }
//
//                // Save all updated entities in a single batch operation for better performance
//                if (!domaineToUpdate.isEmpty()) {
//                    bonReceptionRepo.saveAll(domaineToUpdate);
//                }
//            }
//        }
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


        factureBonReceptionRepo.deleteById(code);
    }

    private List<Integer> parseCodeStringToList(String codeString) {
        if (codeString == null || codeString.trim().isEmpty()) {
            return Collections.emptyList();
        }
        // Remove parentheses and split by comma
        String[] codes = codeString.replace("(", "").replace(")", "").split(",");

        return Arrays.stream(codes)
                .map(String::trim) // Remove whitespace around numbers
                .filter(s -> !s.isEmpty() && s.matches("\\d+")) // Ensure it's a non-empty string of digits
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
