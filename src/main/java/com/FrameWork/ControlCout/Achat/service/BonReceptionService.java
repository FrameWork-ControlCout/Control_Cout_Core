/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Parametrage.service.SocService;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.BonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.EtatReceptionCode;
import com.FrameWork.ControlCout.Achat.repository.DetailsBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.BonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.DetailsOrderAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.OrderAchatRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Stock.service.GestionStockService;
import com.FrameWork.ControlCout.Stock.service.MouvementStockService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
public class BonReceptionService {

    private final Logger log = LoggerFactory.getLogger(BonReceptionService.class);

    private final BonReceptionRepo bonReceptionRepo;
    private final DetailsBonReceptionRepo detailsBonReceptionRepo;
    private final CompteurService compteurService;
    private final DetailsOrderAchatRepo detailsOrderAchatRepo;
    private final OrderAchatRepo orderAchatRepo;
    private final FactureBonReceptionRepo factureBonReceptionRepo;
    private final SocService cliniqueService;
    private final GestionStockService gestionStockService;

    public BonReceptionService(BonReceptionRepo bonReceptionRepo, DetailsBonReceptionRepo detailsBonReceptionRepo, CompteurService compteurService, DetailsOrderAchatRepo detailsOrderAchatRepo, OrderAchatRepo orderAchatRepo, FactureBonReceptionRepo factureBonReceptionRepo, SocService cliniqueService, GestionStockService gestionStockService) {
        this.bonReceptionRepo = bonReceptionRepo;
        this.detailsBonReceptionRepo = detailsBonReceptionRepo;
        this.compteurService = compteurService;
        this.detailsOrderAchatRepo = detailsOrderAchatRepo;
        this.orderAchatRepo = orderAchatRepo;
        this.factureBonReceptionRepo = factureBonReceptionRepo;
        this.cliniqueService = cliniqueService;
        this.gestionStockService = gestionStockService;
    }

    //<editor-fold defaultstate="collapsed" desc="Read-Only Methods">
    @Transactional(readOnly = true)
    public List<BonReceptionDTO> findAllBonReception() {
        return BonReceptionFactory.listBonReceptionToBonReceptionDTOs(
                bonReceptionRepo.findAll(Sort.by("code").descending())
        );
    }

    @Transactional(readOnly = true)
    public List<BonReceptionDTO> findAllBonReceptionByHaveFRB(Boolean haveFRB) {
        return BonReceptionFactory.listBonReceptionToBonReceptionDTOs(
                bonReceptionRepo.findByHaveFBR(haveFRB)
        );
    }

    @Transactional(readOnly = true)
    public List<BonReceptionDTO> findAllBonReceptionByHaveFRBAndCodeFournisseur(Boolean haveFRB, Integer codeFournisseur) {
        return BonReceptionFactory.listBonReceptionToBonReceptionDTOs(
                bonReceptionRepo.findByHaveFBRAndCodeFournisseur(haveFRB, codeFournisseur)
        );
    }

    @Transactional(readOnly = true)
    public BonReceptionDTO findOne(Integer code) {
        BonReception domaine = bonReceptionRepo.findByCode(code);
        return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<BonReception> findOneByCodeEdition(Integer code) {
        List<BonReception> bonReceptions = bonReceptionRepo.findAllBonReceptionByCode(code);
        return bonReceptions;
    }

    //</editor-fold>
    @Transactional
    public BonReceptionDTO save(BonReceptionDTO dto) {
        // 1. SETUP BON RECEPTION HEADER
        BonReception domaine = BonReceptionFactory.bonReceptionDTOToBonReception(dto, new BonReception());

        Compteur compteurCodeSaisie = compteurService.findOne("BonReceptionEC");
        String codeSaisieAC = compteurCodeSaisie.getPrefixe() + compteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(compteurCodeSaisie);

        domaine.setDateCreate(new Date());
        domaine.setUserCreate(Helper.getUserAuthenticated());

        // Save header to get its ID
        domaine = bonReceptionRepo.save(domaine);

        if (dto.getDetailsBonReceptionDTOs() == null || dto.getDetailsBonReceptionDTOs().isEmpty()) {
            return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
        }

        // 2. CREATE BON RECEPTION DETAILS
        List<DetailsBonReception> newDetailsList = new ArrayList<>();
        for (DetailsBonReceptionDTO detailsDto : dto.getDetailsBonReceptionDTOs()) {
            DetailsBonReception detailsDomaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(detailsDto, new DetailsBonReception());
            detailsDomaine.setBonReception(domaine);
            detailsDomaine.setDateCreate(new Date());  // Set in domaine
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());

            newDetailsList.add(detailsDomaine);
        }
        detailsBonReceptionRepo.saveAll(newDetailsList);

        // 3. UPDATE ORIGINAL ORDERS & STOCK
        updateRelatedOrdersAndStock(domaine, dto.getDetailsBonReceptionDTOs());

        List<DetailsBonReception> savedDetails = detailsBonReceptionRepo.saveAll(newDetailsList);

        gestionStockService.createEntreesFromBonReception(savedDetails);
//            gestionStockService.createMouvementFrsFromFactureBonReception(domaine);

        return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
    }

    @Transactional
    public BonReceptionDTO update(BonReceptionDTO dto) {
        // 1. --- FETCH EXISTING ENTITY ---
        Preconditions.checkArgument(dto.getCode() != null, "error.BonReceptionCodeRequiredForUpdate");
        BonReception domaine = bonReceptionRepo.findById(dto.getCode())
                .orElseThrow(() -> new IllegalArgumentException("error.BonReceptionNotFound"));

        
        
        if( Objects.equals(domaine.getHaveFBR(), Boolean.TRUE)){
              throw new IllegalArgumentException("error.BonReceptionHaveFBR");
        }
        
        
        // Store the original list of affected order codes BEFORE we overwrite the domain object
        List<Integer> originalOrderAchatCodes = parseCodeStringToList(domaine.getCodeOrderAchat());

        // 2. --- REVERSE OLD STATE ---
        // A. Reverse the effects on the original OrderAchat records
        reverseOrderAchatUpdates(domaine.getCode());

        // B. Delete the old stock movements
//        mouvementStockService.deleteMouvementsForBonReception(domaine.getCode());
        gestionStockService.deleteMouvementsForBonReception(domaine.getCode());
        // C. Delete the old reception detail lines
        detailsBonReceptionRepo.deleteByCodeBonReception(domaine.getCode());

        // 3. --- APPLY NEW STATE TO HEADER ---
        // Map updated fields from DTO to the existing 'domaine' entity
        domaine = BonReceptionFactory.bonReceptionDTOToBonReception(dto, domaine);
        // It's good practice to track updates
        // domaine.setUserUpdate(Helper.getUserAuthenticated());
        // domaine.setDateUpdate(new Date());
        domaine = bonReceptionRepo.save(domaine);

        // If the update resulted in no details, our work is done.
        // The original orders have already been reversed to their previous state.
        if (dto.getDetailsBonReceptionDTOs() == null || dto.getDetailsBonReceptionDTOs().isEmpty()) {
            return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
        }

        // 4. --- RE-CREATE DETAILS WITH NEW DATA ---
        List<DetailsBonReception> newDetailsList = new ArrayList<>();
        for (DetailsBonReceptionDTO detailsDto : dto.getDetailsBonReceptionDTOs()) {
            DetailsBonReception detailsDomaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(detailsDto, new DetailsBonReception());
            detailsDomaine.setBonReception(domaine);
            // Set user/date create for the new detail records
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            detailsDomaine.setDateCreate(new Date());
            newDetailsList.add(detailsDomaine);
        }
        List<DetailsBonReception> savedDetails = detailsBonReceptionRepo.saveAll(newDetailsList);

        // 5. --- RE-APPLY UPDATES TO ORDERS AFFECTED BY THE NEW STATE ---
        updateRelatedOrdersAndStock(domaine, dto.getDetailsBonReceptionDTOs());

        // 6. --- CREATE NEW STOCK MOVEMENTS ---
//        mouvementStockService.createEntreesFromBonReception(savedDetails);
        gestionStockService.createEntreesFromBonReception(savedDetails);
        return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
    }

    @Transactional
    public void deleteBonReception(Integer code) {
        String orderCodeAsString = code.toString();
        boolean isUsedInFactureBr = factureBonReceptionRepo.existsByCodeBonReceptionContaining(orderCodeAsString);
        Preconditions.checkArgument(!isUsedInFactureBr, "error.BonReceptionUsedInFactureBR");

        // 1. REVERSE ALL RELATED UPDATES
        reverseOrderAchatUpdates(code);
//        mouvementStockService.deleteMouvementsForBonReception(code);
        gestionStockService.deleteMouvementsForBonReception(code);
        // 2. DELETE THE BON RECEPTION ITSELF
        // Cascade should handle details, but being explicit is safer
        detailsBonReceptionRepo.deleteByCodeBonReception(code);
        bonReceptionRepo.deleteById(code);
    }

    /**
     * A private helper method to encapsulate the logic of updating original
     * orders. This can be used by both save() and update().
     */
    private void updateRelatedOrdersAndStock(BonReception bonReception, List<DetailsBonReceptionDTO> receptionDetails) {
        String rawCodeString = bonReception.getCodeOrderAchat();
        if (rawCodeString == null || rawCodeString.trim().isEmpty()) {
            return;
        }

        List<Integer> orderAchatCodes = parseCodeStringToList(rawCodeString);
        Set<Integer> articleCodes = receptionDetails.stream()
                .map(DetailsBonReceptionDTO::getCodeArticle)
                .collect(Collectors.toSet());

        List<DetailsOrderAchat> detailsToUpdate = detailsOrderAchatRepo.findByCodeOrderAchatInAndCodeArticleIn(orderAchatCodes, new ArrayList<>(articleCodes));

        Map<Integer, BigDecimal> newReceptionQtyMap = receptionDetails.stream()
                .collect(Collectors.toMap(
                        DetailsBonReceptionDTO::getCodeDetailsOrderAchat,
                        DetailsBonReceptionDTO::getQteReceptionner,
                        BigDecimal::add // Handle duplicate keys by summing quantities
                ));

        for (DetailsOrderAchat detailOrder : detailsToUpdate) {
            BigDecimal newQty = newReceptionQtyMap.get(detailOrder.getCode());
            if (newQty == null) {
                continue;
            }

            BigDecimal oldQteReceived = detailOrder.getQteDejaReceptionner() != null ? detailOrder.getQteDejaReceptionner() : BigDecimal.ZERO;
            BigDecimal newTotalQteReceived = oldQteReceived.add(newQty);
            detailOrder.setQteDejaReceptionner(newTotalQteReceived);

            Integer newStatus = calculateDetailStatus(newTotalQteReceived, detailOrder.getQteBesoin());
            detailOrder.setCodeEtatReception(newStatus);
            if (newStatus != null) {
                detailOrder.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(newStatus));
            }
        }
        detailsOrderAchatRepo.saveAll(detailsToUpdate);

        List<OrderAchat> parentOrdersToUpdate = orderAchatRepo.findByCodeIn(orderAchatCodes);
        for (OrderAchat order : parentOrdersToUpdate) {
            List<DetailsOrderAchat> allItsDetails = detailsOrderAchatRepo.findByCodeOrderAchat(order.getCode());
            Integer newOrderStatus = calculateOrderStatus(allItsDetails);
            order.setCodeEtatReception(newOrderStatus);
            if (newOrderStatus != null) {
                order.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(newOrderStatus));
            }
        }
        orderAchatRepo.saveAll(parentOrdersToUpdate);
    }

    /**
     * A private helper method to reverse the changes made to original orders
     * when a BonReception is deleted/updated.
     */
    private void reverseOrderAchatUpdates(Integer bonReceptionCode) {
        List<DetailsBonReception> detailsBeingReversed = detailsBonReceptionRepo.findByCodeBonReception(bonReceptionCode);
        if (detailsBeingReversed.isEmpty()) {
            return;
        }

        Set<Integer> affectedDetailsOrderAchatCodes = detailsBeingReversed.stream()
                .map(DetailsBonReception::getCodeDetailsOrderAchat)
                .collect(Collectors.toSet());

        List<DetailsOrderAchat> affectedDetailsOrderAchat = detailsOrderAchatRepo.findAllById(affectedDetailsOrderAchatCodes);

        Map<Integer, BigDecimal> qtyToSubtractMap = detailsBeingReversed.stream()
                .collect(Collectors.toMap(
                        DetailsBonReception::getCodeDetailsOrderAchat,
                        DetailsBonReception::getQteReceptionner,
                        BigDecimal::add
                ));

        for (DetailsOrderAchat detailOrder : affectedDetailsOrderAchat) {
            BigDecimal qtyToSubtract = qtyToSubtractMap.get(detailOrder.getCode());
            if (qtyToSubtract == null) {
                continue;
            }

            BigDecimal oldQteReceived = detailOrder.getQteDejaReceptionner() != null ? detailOrder.getQteDejaReceptionner() : BigDecimal.ZERO;
            BigDecimal newTotalQteReceived = oldQteReceived.subtract(qtyToSubtract);
            detailOrder.setQteDejaReceptionner(newTotalQteReceived.max(BigDecimal.ZERO)); // Ensure it doesn't go below zero

            Integer newStatus = calculateDetailStatus(newTotalQteReceived, detailOrder.getQteBesoin());
            detailOrder.setCodeEtatReception(newStatus);
            if (newStatus != null) {
                detailOrder.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(newStatus));
            }
        }
        detailsOrderAchatRepo.saveAll(affectedDetailsOrderAchat);

        Set<Integer> affectedOrderAchatCodes = affectedDetailsOrderAchat.stream()
                .map(DetailsOrderAchat::getCodeOrderAchat)
                .collect(Collectors.toSet());
        List<OrderAchat> affectedOrders = orderAchatRepo.findAllById(affectedOrderAchatCodes);

        for (OrderAchat order : affectedOrders) {
            List<DetailsOrderAchat> allOrderDetails = detailsOrderAchatRepo.findByCodeOrderAchat(order.getCode());
            Integer newOrderStatus = calculateOrderStatus(allOrderDetails);
            order.setCodeEtatReception(newOrderStatus);
            if (newOrderStatus != null) {
                order.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(newOrderStatus));
            }
        }
        orderAchatRepo.saveAll(affectedOrders);
    }

    //<editor-fold defaultstate="collapsed" desc="Private Helper Methods">
    private Integer calculateDetailStatus(BigDecimal totalReceived, BigDecimal qteBesoin) {
        if (totalReceived.compareTo(BigDecimal.ZERO) <= 0) {
            return EtatReceptionCode.EN_COURS;
        } else if (totalReceived.compareTo(qteBesoin) < 0) {
            return EtatReceptionCode.PARTIELLEMENT_RECU;
        } else {
            return EtatReceptionCode.COMPLETEMENT_RECU;
        }
    }

    private Integer calculateOrderStatus(List<DetailsOrderAchat> details) {
        if (details == null || details.isEmpty()) {
            return EtatReceptionCode.EN_COURS;
        }
        Set<Integer> statuses = details.stream()
                .map(DetailsOrderAchat::getCodeEtatReception)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (statuses.stream().allMatch(s -> s.equals(EtatReceptionCode.COMPLETEMENT_RECU))) {
            return EtatReceptionCode.COMPLETEMENT_RECU;
        }
        if (statuses.contains(EtatReceptionCode.PARTIELLEMENT_RECU)) {
            return EtatReceptionCode.PARTIELLEMENT_RECU;
        }
        return EtatReceptionCode.EN_COURS;
    }

    public List<Integer> parseCodeStringToList(String codeString) {
        if (codeString == null || codeString.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String[] codes = codeString.replace("(", "").replace(")", "").split(",");
        return Arrays.stream(codes)
                .map(String::trim)
                .filter(s -> !s.isEmpty() && s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    //</editor-fold>

}
