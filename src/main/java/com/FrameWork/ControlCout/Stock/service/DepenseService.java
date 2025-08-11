/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.service;

import jakarta.persistence.EntityNotFoundException;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.BonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.OrderAchatFactory;
import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.dto.DispenseDetailDTO;
import com.FrameWork.ControlCout.Cout.repository.ConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.service.ConsoStandardService;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Stock.domaine.Depense;
import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import com.FrameWork.ControlCout.Stock.domaine.MouvementStock;
import com.FrameWork.ControlCout.Stock.dto.DepenseDTO;
import com.FrameWork.ControlCout.Stock.dto.DetailsDepenseDTO;
import com.FrameWork.ControlCout.Stock.factory.DepenseFactory;
import com.FrameWork.ControlCout.Stock.factory.DetailsDepenseFactory;
import com.FrameWork.ControlCout.Stock.repository.DepenseRepo;
import com.FrameWork.ControlCout.Stock.repository.DetailsDepenseRepo;
import com.FrameWork.ControlCout.constants.AchatConstants;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
public class DepenseService {

    private final Logger log = LoggerFactory.getLogger(DepenseService.class);
    private final DepenseRepo depenseRepo;
    private final DetailsDepenseRepo detailsDepenseRepo;
    private final GestionStockService gestionStockService;
    private final CompteurService compteurService;
    private final ConsoStandardRepo consoStandardRepo;

    public DepenseService(DepenseRepo depenseRepo, DetailsDepenseRepo detailsDepenseRepo, GestionStockService gestionStockService, CompteurService compteurService, ConsoStandardRepo consoStandardRepo) {
        this.depenseRepo = depenseRepo;
        this.detailsDepenseRepo = detailsDepenseRepo;
        this.gestionStockService = gestionStockService;
        this.compteurService = compteurService;
        this.consoStandardRepo = consoStandardRepo;
    }

    @Transactional(readOnly = true)
    public List<DepenseDTO> findAllDepense() {
        return DepenseFactory.listDepenseToDepenseDTOs(depenseRepo.findAll(Sort.by("code").descending()));
    }

    @Transactional(readOnly = true)
    public Collection<DepenseDTO> findDepenseByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        return DepenseFactory.CollectionDepenseToDepenseDTOs(
                depenseRepo.findAllByDateCreateBetween(dateDebut, dateFin)
        );
    }

    @Transactional(readOnly = true)
    public DepenseDTO findOne(Integer code) {
        Depense domaine = depenseRepo.findByCode(code);
        return DepenseFactory.depenseToDepenseDTO(domaine);
    }

    @Transactional
    public DepenseDTO save(DepenseDTO dto) {
        // 1. SETUP BON RECEPTION HEADER
        Depense domaine = DepenseFactory.depenseDTOToDepense(dto, new Depense());

        Compteur compteurCodeSaisie = compteurService.findOne("Dispensation");
        String codeSaisieAC = compteurCodeSaisie.getPrefixe() + compteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(compteurCodeSaisie);

        domaine.setDateCreate(new Date());
        domaine.setUserCreate(Helper.getUserAuthenticated());

        // Save header to get its ID
        domaine = depenseRepo.save(domaine);

        if (dto.getDetailsDepensesDTOs() == null || dto.getDetailsDepensesDTOs().isEmpty()) {
            return DepenseFactory.depenseToDepenseDTO(domaine);
        }

        // 2. CREATE DETAILS DEPENSE
        List<DetailsDepense> newDetailsList = new ArrayList<>();
        for (DetailsDepenseDTO detailsDto : dto.getDetailsDepensesDTOs()) {
            DetailsDepense detailsDomaine = DetailsDepenseFactory.detailsDepenseDTOToDetailsDepense(detailsDto, new DetailsDepense());
            detailsDomaine.setDepense(domaine);
            detailsDomaine.setDateCreate(new Date());  // Set in domaine
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            detailsDomaine.setConsStandard(detailsDto.isConsStandard());

            newDetailsList.add(detailsDomaine);
        }
        detailsDepenseRepo.saveAll(newDetailsList);
 
        List<DetailsDepense> savedDetails = detailsDepenseRepo.saveAll(newDetailsList);

        gestionStockService.createSortieFromDepenseSource(savedDetails);
        gestionStockService.createEntreeFromDepenseDestination(savedDetails);

        /// Update QteDispenser DetailsConsoStandard
        
        
        if(dto.getCodeConsoStandard() != null){
             ConsoStandard consoStandard = consoStandardRepo.findByCode(dto.getCodeConsoStandard());

        for (DetailsDepenseDTO detailDto : dto.getDetailsDepensesDTOs()) {

            if (detailDto.getQteDispenser() == null || detailDto.getQteDispenser().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            if (!consoStandard.getDetailsConsoStandards().isEmpty()) {
                DetailsConsoStandard detailToUpdate = consoStandard.getDetailsConsoStandards().stream()
                        .filter(d -> d.getCode().equals(detailDto.getCodeDetailsConsoStandard()))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("DetailsConsoStandard line not found with code: " + detailDto.getCodeDetailsConsoStandard()));

                BigDecimal qteDejaDispensee = detailToUpdate.getQteDispensee() != null ? detailToUpdate.getQteDispensee() : BigDecimal.ZERO;

                detailToUpdate.setQteDispensee(qteDejaDispensee.add(detailDto.getQteDispenser()));
            }

        }

        consoStandardRepo.save(consoStandard);
        log.info("Dispensation process completed successfully for ConsoStandard code: {}", dto.getCodeConsoStandard());
            
        }
        
        
       
        return DepenseFactory.depenseToDepenseDTO(domaine);
    }

    @Transactional
    public void deleteDepense(Integer code) {
        // Step 1: Fetch the Depense entity directly from the database.
        Depense depenseToDelete = depenseRepo.findByCode(code);

        // Step 2: Handle the case where the entity does not exist.
        if (depenseToDelete == null) {
            throw new EntityNotFoundException("Depense not found with code: " + code);
        }

        // Step 3: Delete associated stock movements first.
        gestionStockService.deleteMouvementsForDepenseSource(code, depenseToDelete.getCodeDepotSource());
        gestionStockService.deleteMouvementsForDepenseDestination(code, depenseToDelete.getCodeDepotDestination());

        // Step 4: Reverse the quantity update on the related ConsoStandard.
        // This assumes Depense has a @ManyToOne relationship to ConsoStandard.
        if (depenseToDelete.getConsoStandard() != null) {
            ConsoStandard consoStandard = consoStandardRepo.findById(depenseToDelete.getConsoStandard().getCode())
                    .orElseThrow(() -> new EntityNotFoundException("ConsoStandard not found with code: " + depenseToDelete.getConsoStandard().getCode()));

            // The detailsDepenses collection is loaded because we are in an active transaction.
            // Check if the collection is not null or empty.
            if (depenseToDelete.getDetailsDepenses() != null && !depenseToDelete.getDetailsDepenses().isEmpty()) {
                for (DetailsDepense detail : depenseToDelete.getDetailsDepenses()) {
                    if (detail.getQteDispenser() == null || detail.getQteDispenser().compareTo(BigDecimal.ZERO) <= 0) {
                        continue;
                    }

                    DetailsConsoStandard detailToUpdate = consoStandard.getDetailsConsoStandards().stream()
                            .filter(d -> Objects.equals(d.getCode(), depenseToDelete.getConsoStandard().getDetailsConsoStandards().iterator().next().getCode()))
                            .findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("DetailsConsoStandard line not found with code: " + depenseToDelete.getConsoStandard().getDetailsConsoStandards().iterator().next().getCode()));

                    BigDecimal qteDejaDispensee = detailToUpdate.getQteDispensee() != null ? detailToUpdate.getQteDispensee() : BigDecimal.ZERO;

                    // Reverse the operation: subtract the previously added dispensed quantity.
                    detailToUpdate.setQteDispensee(qteDejaDispensee.subtract(detail.getQteDispenser()));
                }
                consoStandardRepo.save(consoStandard);
            }
        }

        // Step 5: Delete the depense details and then the depense itself.
        // This order respects foreign key constraints if cascading remove is not used.
        detailsDepenseRepo.deleteByCodeDepense(code);
        depenseRepo.deleteById(code);
    }

    @Transactional(readOnly = true)
    public List<Depense> findOneByCodeEdition(Integer code) {
        List<Depense> depenses = depenseRepo.findAllDepenseByCode(code);
        return depenses;
    }

}
