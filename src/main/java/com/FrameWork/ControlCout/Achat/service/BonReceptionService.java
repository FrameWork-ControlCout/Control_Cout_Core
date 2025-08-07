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
import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import com.FrameWork.ControlCout.Cout.domaine.FicheTech;
import com.FrameWork.ControlCout.Cout.repository.DetailsFicheTechRepo;
import com.FrameWork.ControlCout.Cout.repository.FicheTechRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.repository.ArticleRepo;
import com.FrameWork.ControlCout.Parametrage.service.ArticleService;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Stock.service.GestionStockService;
import com.FrameWork.ControlCout.Stock.service.MouvementStockService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final ArticleRepo articleRepo;
    private final GestionStockService gestionStockService;
    private final DetailsFicheTechRepo detailsFicheTechRepo;
    private final FicheTechRepo ficheTechRepo;
    private static final BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");

    public BonReceptionService(BonReceptionRepo bonReceptionRepo, DetailsBonReceptionRepo detailsBonReceptionRepo, CompteurService compteurService, DetailsOrderAchatRepo detailsOrderAchatRepo, OrderAchatRepo orderAchatRepo, FactureBonReceptionRepo factureBonReceptionRepo, SocService cliniqueService, ArticleRepo articleRepo, GestionStockService gestionStockService, DetailsFicheTechRepo detailsFicheTechRepo, FicheTechRepo ficheTechRepo) {
        this.bonReceptionRepo = bonReceptionRepo;
        this.detailsBonReceptionRepo = detailsBonReceptionRepo;
        this.compteurService = compteurService;
        this.detailsOrderAchatRepo = detailsOrderAchatRepo;
        this.orderAchatRepo = orderAchatRepo;
        this.factureBonReceptionRepo = factureBonReceptionRepo;
        this.cliniqueService = cliniqueService;
        this.articleRepo = articleRepo;
        this.gestionStockService = gestionStockService;
        this.detailsFicheTechRepo = detailsFicheTechRepo;
        this.ficheTechRepo = ficheTechRepo;
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
        List<Article> articles = new ArrayList<>();

        for (DetailsBonReceptionDTO detailsDto : dto.getDetailsBonReceptionDTOs()) {
            DetailsBonReception detailsDomaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(detailsDto, new DetailsBonReception());
            detailsDomaine.setBonReception(domaine);
            detailsDomaine.setDateCreate(new Date());  // Set in domaine
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());

            newDetailsList.add(detailsDomaine);

            Article article = articleRepo.findByCode(detailsDomaine.getCodeArticle());
            article.setLastPrixAchat(detailsDomaine.getPrixUni());
            articles.add(article);

        }
        detailsBonReceptionRepo.saveAll(newDetailsList);
        articleRepo.saveAll(articles);

        updateFicheTechniqueCosts(articles);

        updateRelatedOrdersAndStock(domaine, dto.getDetailsBonReceptionDTOs());
        List<DetailsBonReception> savedDetails = detailsBonReceptionRepo.saveAll(newDetailsList);
        gestionStockService.createEntreesFromBonReception(savedDetails);
        return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
    }

    @Transactional
    public BonReceptionDTO update(BonReceptionDTO dto) {
        Preconditions.checkArgument(dto.getCode() != null, "error.BonReceptionCodeRequiredForUpdate");
        BonReception domaine = bonReceptionRepo.findById(dto.getCode())
                .orElseThrow(() -> new IllegalArgumentException("error.BonReceptionNotFound"));

        if (Objects.equals(domaine.getHaveFBR(), Boolean.TRUE)) {
            throw new IllegalArgumentException("error.BonReceptionHaveFBR");
        }
        reverseOrderAchatUpdates(domaine.getCode());
        gestionStockService.deleteMouvementsForBonReception(domaine.getCode());
        detailsBonReceptionRepo.deleteByCodeBonReception(domaine.getCode());
        domaine = BonReceptionFactory.bonReceptionDTOToBonReception(dto, domaine);
        domaine = bonReceptionRepo.save(domaine);

        if (dto.getDetailsBonReceptionDTOs() == null || dto.getDetailsBonReceptionDTOs().isEmpty()) {
            return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
        }
        List<DetailsBonReception> newDetailsList = new ArrayList<>();
        List<Article> articles = new ArrayList<>();
        for (DetailsBonReceptionDTO detailsDto : dto.getDetailsBonReceptionDTOs()) {
            DetailsBonReception detailsDomaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(detailsDto, new DetailsBonReception());
            detailsDomaine.setBonReception(domaine);
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            detailsDomaine.setDateCreate(new Date());
            newDetailsList.add(detailsDomaine);

            Article article = articleRepo.findByCode(detailsDomaine.getCodeArticle());
            article.setLastPrixAchat(detailsDomaine.getPrixUni());

            articles.add(article);

        }
        List<DetailsBonReception> savedDetails = detailsBonReceptionRepo.saveAll(newDetailsList);
        articleRepo.saveAll(articles);
        updateRelatedOrdersAndStock(domaine, dto.getDetailsBonReceptionDTOs());
        updateFicheTechniqueCosts(articles);
        gestionStockService.createEntreesFromBonReception(savedDetails);
        return BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine);
    }

    @Transactional
    public void deleteBonReception(Integer code) {
        String orderCodeAsString = code.toString();
        boolean isUsedInFactureBr = factureBonReceptionRepo.existsByCodeBonReceptionContaining(orderCodeAsString);
        Preconditions.checkArgument(!isUsedInFactureBr, "error.BonReceptionUsedInFactureBR");
        reverseOrderAchatUpdates(code);
        gestionStockService.deleteMouvementsForBonReception(code);
        detailsBonReceptionRepo.deleteByCodeBonReception(code);
        bonReceptionRepo.deleteById(code);
    }

    private void updateFicheTechniqueCosts(List<Article> updatedArticles) {
        if (updatedArticles == null || updatedArticles.isEmpty()) {
            return;
        }

        // Create a map of Article Code -> New Price for efficient lookup.
        Map<Integer, BigDecimal> articlePriceMap = updatedArticles.stream()
                .collect(Collectors.toMap(Article::getCode, Article::getLastPrixAchat));

        List<Integer> articleCodes = new ArrayList<>(articlePriceMap.keySet());

        // Find all Fiche Technique details that use any of the updated articles.
        // Assumes DetailsFicheTechRepo has a method findByCodeArticleIn
        List<DetailsFicheTech> detailsToUpdate = detailsFicheTechRepo.findByCodeArticleIn(articleCodes);

        if (detailsToUpdate.isEmpty()) {
            log.info("No Fiche Technique details found for the updated articles.");
            return;
        }

        log.info("Found {} Fiche Technique details to update with new costs.", detailsToUpdate.size());

        // Update price and total cost for each affected detail.
        for (DetailsFicheTech detail : detailsToUpdate) {
            BigDecimal newPrice = articlePriceMap.get(detail.getCodeArticle());

            if (detail.getPrixUni().compareTo(newPrice) > 0) {
                detail.setModifPrice("REM");
            } else {
                detail.setModifPrice("AUG");
            }
//            
            if (newPrice != null) {
                detail.setPrixUni(newPrice);
                // Recalculate the total price for the component.
                if (detail.getConsTotal() != null) {
                    detail.setPrixTotal(detail.getConsTotal().multiply(newPrice));
                }
            }
        }

        // Persist all changes to the database.
        detailsFicheTechRepo.saveAll(detailsToUpdate);
        log.info("Successfully updated costs for {} Fiche Technique details.", detailsToUpdate.size());

        Set<FicheTech> fichesToRecalculate = detailsToUpdate.stream()
                .map(DetailsFicheTech::getFicheTechnique)
                .collect(Collectors.toSet());

        // Step 4: Iterate through each unique recipe and recalculate its total cost.
        for (FicheTech fiche : fichesToRecalculate) {

            // For each recipe, sum the cost of all its ingredients.
            // Cost of one ingredient = (quantity in recipe) * (latest purchase price of article)
            // Using BigDecimal for precision in financial calculations.
            BigDecimal newTotalCost = fiche.getDetailsFicheTechniques().stream()
                    .map(detail -> {
                        // We need the article's price from its entity.
                        Article ingredientArticle = detail.getArticle();
                        BigDecimal quantity = detail.getConsTotal();
                        BigDecimal price = ingredientArticle.getLastPrixAchat();
                        return quantity.multiply(price);
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
 
            BigDecimal pourcentAutreAcharge = fiche.getPourcentAutreAcharge(); 
            if (pourcentAutreAcharge == null || pourcentAutreAcharge.compareTo(BigDecimal.ZERO) == 0) {
                fiche.setAutreCout(BigDecimal.ZERO); 
                fiche.setPrixTotal(newTotalCost);
            } else { 
                BigDecimal pourcentageFacteur = pourcentAutreAcharge.divide(BIG_DECIMAL_100, 4, RoundingMode.HALF_UP); 
                BigDecimal autreCout = newTotalCost.multiply(pourcentageFacteur);
                fiche.setAutreCout(autreCout); 
                BigDecimal mntTTC = newTotalCost.add(autreCout);
                BigDecimal coutUni = mntTTC.divide(BigDecimal.valueOf(fiche.getNbrePersRef()));

                fiche.setCoutUnitaire(coutUni);
                fiche.setPrixTotal(mntTTC);
            }

        }

        ficheTechRepo.saveAll(fichesToRecalculate);

        // Note: After updating details, you might need a mechanism to recalculate 
        // the total cost of the parent FicheTechnique entities if that is not handled automatically.
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
