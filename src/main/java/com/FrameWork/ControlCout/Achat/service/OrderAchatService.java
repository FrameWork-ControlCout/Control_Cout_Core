/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.OrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.BonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.DetailsOrderAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureGrosRepo;
import com.FrameWork.ControlCout.Achat.repository.OrderAchatRepo;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.repository.DetailsConsoStandardRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.constants.AchatConstants;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
public class OrderAchatService {

    private final Logger log = LoggerFactory.getLogger(OrderAchatService.class);

    private final OrderAchatRepo orderAchatRepo;
    private final DetailsOrderAchatRepo detailsOrderAchatRepo;
    private final CompteurService compteurService;
    private final DetailsConsoStandardRepo detailsConsoStandardRepo;
    private final BonReceptionRepo bonReceptionRepo;
    private final FactureGrosRepo factureGrosRepo;

    public OrderAchatService(OrderAchatRepo orderAchatRepo, DetailsOrderAchatRepo detailsOrderAchatRepo, CompteurService compteurService, DetailsConsoStandardRepo detailsConsoStandardRepo, BonReceptionRepo bonReceptionRepo, FactureGrosRepo factureGrosRepo) {
        this.orderAchatRepo = orderAchatRepo;
        this.detailsOrderAchatRepo = detailsOrderAchatRepo;
        this.compteurService = compteurService;
        this.detailsConsoStandardRepo = detailsConsoStandardRepo;
        this.bonReceptionRepo = bonReceptionRepo;
        this.factureGrosRepo = factureGrosRepo;
    }

    
    

    @Transactional(readOnly = true)
    public List<OrderAchatDTO> findAllOrderAchat() {
        return OrderAchatFactory.listOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findAll(Sort.by("code").descending())
        );
    }

    @Transactional(readOnly = true)
    public List<OrderAchatDTO> findOrderAchatByEtatFacture(Integer codeEtatFacture) {
        return OrderAchatFactory.listOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findByCodeEtatFacture(codeEtatFacture)
        );
    }
    
      @Transactional(readOnly = true)
    public List<OrderAchatDTO> findOrderAchatByEtatFactureAndCodeEtatRepecet(Integer codeEtatFacture,List<Integer> codeEtatReception) {
        return OrderAchatFactory.listOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findByCodeEtatFactureAndCodeEtatReceptionIn(codeEtatFacture,codeEtatReception)
        );
    }

    @Transactional(readOnly = true)
    public List<OrderAchatDTO> findOrderAchatByFournisseur(Integer codeFournisseur) {
        return OrderAchatFactory.listOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findByCodeFournisseur(codeFournisseur)
        );
    }

    @Transactional(readOnly = true)
    public List<OrderAchatDTO> findOrderAchatByFournisseurAndCodeEtatFacture(Integer codeFournisseur, Integer codeEtatFacture) {

        return OrderAchatFactory.listOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findByCodeFournisseurAndCodeEtatFacture(codeFournisseur, codeEtatFacture)
        );

    }

    @Transactional(readOnly = true)
    public Collection<OrderAchatDTO> findFactureByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        return OrderAchatFactory.CollectionOrderAchatToOrderAchatDTOs(
                orderAchatRepo.findAllByDateCreateBetween(dateDebut, dateFin)
        );
    }

    @Transactional(readOnly = true)
    public OrderAchatDTO findOne(Integer code) {
        OrderAchat domaine = orderAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.OrderAchatNotFound");
        return OrderAchatFactory.orderAchatToOrderAchatDTO(domaine);

    }

    public OrderAchatDTO save(OrderAchatDTO dto) {

        OrderAchat domaine = OrderAchatFactory.orderAchatDTOToOrderAchat(dto, new OrderAchat());

        Compteur CompteurCodeSaisie = compteurService.findOne("OrderAchatEC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = orderAchatRepo.save(domaine);

        //DetailsOrderAchat 
        if (dto.getDetailsOrderAchatDTOs() != null) {

            List<DetailsOrderAchatDTO> detailsOrderAchatDTOs = dto.getDetailsOrderAchatDTOs();
            for (DetailsOrderAchatDTO detailsDto : detailsOrderAchatDTOs) {
                DetailsOrderAchat detailsDomaine = DetailsOrderAchatFactory.detailsorderAchatDTOTodetailsOrderAchat(detailsDto, new DetailsOrderAchat());
                detailsDomaine.setOrderAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setQteBesoin(detailsDto.getQteBesoin());
                detailsDomaine.setQteDejaReceptionner(BigDecimal.ZERO);

                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setSatisfait(detailsDto.isSatisfait());
                detailsDomaine.setConsoStandard(detailsDto.isConsoStandard());
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeEtatReception(detailsDto.getCodeEtatReception());
                if (detailsDomaine.getCodeEtatReception() != null) {
                    detailsDomaine.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(detailsDto.getCodeEtatReception()));
                }

                detailsDomaine.setCodeOrderAchat(detailsDto.getCodeOrderAchat());
                if (detailsDomaine.getCodeOrderAchat() != null) {
                    detailsDomaine.setOrderAchat(OrderAchatFactory.createOrderAchatByCode(detailsDto.getCodeOrderAchat()));
                }
                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
                }
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                detailsOrderAchatRepo.save(detailsDomaine);

                String rawCodeString = domaine.getCodeConsoStandard(); // e.g., "45,46"
                if (rawCodeString != null && !rawCodeString.trim().isEmpty()) {
                    List<Integer> codeConsoStandardList = parseCodeStringToList(rawCodeString);
                    if (!codeConsoStandardList.isEmpty()) {
                        // ONE database query to get all relevant entities
                        List<DetailsConsoStandard> detailsToUpdate = detailsConsoStandardRepo.findByCodeConsoStandardInAndCodeArticle(codeConsoStandardList, detailsDto.getCodeArticle());

                        // Update all entities in memory
                        for (DetailsConsoStandard detail : detailsToUpdate) {
                            detail.setHaveOA(Boolean.TRUE);
                        }

                        // Save all updated entities in a single batch operation for better performance
                        if (!detailsToUpdate.isEmpty()) {
                            detailsConsoStandardRepo.saveAll(detailsToUpdate);
                        }
                    }
                }
            }
        }

        // 3. Update haveOA flag - MOVED outside the details loop and OPTIMIZED
        return OrderAchatFactory.orderAchatToOrderAchatDTO(domaine);
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

    public void deleteOrderAchat(Integer code) {
        Preconditions.checkArgument(orderAchatRepo.existsById(code), "error.OrderAchatNotFound");
        OrderAchat orderToDelete = orderAchatRepo.findByCode(code);
        String orderCodeAsString = code.toString();
        boolean isUsedInBonReception = bonReceptionRepo.existsByCodeOrderAchatContaining(orderCodeAsString);  
        boolean isUsedInFactureGros = factureGrosRepo.existsByCodeOrderAchatContaining(orderCodeAsString);


        Preconditions.checkArgument(!isUsedInBonReception, "error.OrderAchatUsedInBonReception");    
        Preconditions.checkArgument(!isUsedInFactureGros, "error.OrderAchatUsedInFactureGros");


        Preconditions.checkArgument(orderToDelete != null, "error.OrderAchatNotFound");
        String rawCodeString = orderToDelete.getCodeConsoStandard();
        if (rawCodeString != null && !rawCodeString.trim().isEmpty()) {
            List<Integer> codeConsoStandardList = parseCodeStringToList(rawCodeString);
            List<DetailsOrderAchat> orderDetails = detailsOrderAchatRepo.findByCodeOrderAchat(code);
            Set<Integer> articleCodes = orderDetails.stream()
                    .map(DetailsOrderAchat::getCodeArticle)
                    .collect(Collectors.toSet());
            if (!codeConsoStandardList.isEmpty() && !articleCodes.isEmpty()) {
                List<DetailsConsoStandard> detailsToUpdate = detailsConsoStandardRepo.findByCodeConsoStandardInAndCodeArticleIn(codeConsoStandardList, new ArrayList<>(articleCodes));
                for (DetailsConsoStandard detail : detailsToUpdate) {
                    detail.setHaveOA(Boolean.FALSE);
                }
                if (!detailsToUpdate.isEmpty()) {
                    detailsConsoStandardRepo.saveAll(detailsToUpdate);
                }
            }
        }
        detailsOrderAchatRepo.deleteByCodeOrderAchat(code);
        orderAchatRepo.deleteById(code);
    }

    public OrderAchatDTO update(OrderAchatDTO dto) {
        OrderAchat domaine = orderAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.OrderAchatNotFound");
        domaine = OrderAchatFactory.orderAchatDTOToOrderAchat(dto, domaine);
        
        if(Integer.parseInt(AchatConstants.CODE_ETAT_RECEPTION_NON_RECEPTIONNER.toString()) != domaine.getEtatReception().getCode())
        {
             throw new IllegalArgumentException("error.OrderAchatReceptionner");
        }
        
        domaine = orderAchatRepo.save(domaine);

        detailsOrderAchatRepo.deleteByCodeOrderAchat(domaine.getCode());

        if (dto.getDetailsOrderAchatDTOs() != null) {

            List<DetailsOrderAchatDTO> detailsOrderAchatDTOs = dto.getDetailsOrderAchatDTOs();
            for (DetailsOrderAchatDTO detailsDto : detailsOrderAchatDTOs) {
                DetailsOrderAchat detailsDomaine = DetailsOrderAchatFactory.detailsorderAchatDTOTodetailsOrderAchat(detailsDto, new DetailsOrderAchat());
                detailsDomaine.setOrderAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setQteBesoin(detailsDto.getQteBesoin());
                detailsDomaine.setQteDejaReceptionner(detailsDto.getQteDejaReceptionner());

                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setConsoStandard(detailsDto.isConsoStandard());
                detailsDomaine.setSatisfait(detailsDto.isSatisfait());
                detailsDomaine.setCodeEtatReception(detailsDto.getCodeEtatReception());
                if (detailsDomaine.getCodeEtatReception() != null) {
                    detailsDomaine.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(detailsDto.getCodeEtatReception()));
                }
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeOrderAchat(detailsDto.getCodeOrderAchat());
                if (detailsDomaine.getCodeOrderAchat() != null) {
                    detailsDomaine.setOrderAchat(OrderAchatFactory.createOrderAchatByCode(detailsDto.getCodeOrderAchat()));
                }

                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
                }
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }

                detailsOrderAchatRepo.save(detailsDomaine); // Assuming you have a detailsOrderAchatRepo

            }

        } else {
            throw new IllegalArgumentException("error.DetailsFactureNotFound");
        }

        OrderAchatDTO resultDTO = OrderAchatFactory.orderAchatToOrderAchatDTO(domaine);
        return resultDTO;
    }
    
    @Transactional(readOnly = true)
    public List<OrderAchat> findOneByCodeEdition(Integer code) {
        List<OrderAchat> orderAchats = orderAchatRepo.findAllOrderAchatByCode(code);
        return orderAchats;
    }


}
