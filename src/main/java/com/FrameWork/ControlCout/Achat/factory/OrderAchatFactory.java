/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Achat.Edition.OrderAchatEdition;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Parametrage.factory.CostProfitCentreFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class OrderAchatFactory {

    public static OrderAchat createOrderAchatByCode(int code) {
        OrderAchat domaine = new OrderAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static OrderAchat orderAchatDTOToOrderAchat(OrderAchatDTO dto, OrderAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeEtatFacture(dto.getCodeEtatFacture());
            if (domaine.getCodeEtatFacture() != null) {
                domaine.setEtatFacture(EtatFactory.createEtatFactureByCode(dto.getCodeEtatFacture()));
            }
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeEtatReception(dto.getCodeEtatReception());
            if (domaine.getCodeEtatReception() != null) {
                domaine.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(dto.getCodeEtatReception()));
            }

            domaine.setCodeDepot(dto.getCodeDepot());
            if (domaine.getCodeDepot() != null) {
                domaine.setDepot(DepotFactory.createDepotByCode(dto.getCodeDepot()));
            }

            domaine.setCodeEtatFacture(dto.getCodeEtatFacture());
            if (domaine.getCodeEtatFacture() != null) {
                domaine.setEtatFacture(EtatFactory.createEtatFactureByCode(dto.getCodeEtatFacture()));
            }

            domaine.setCodeConsoStandard(dto.getCodeConsoStandard());

            domaine.setUserApprove(dto.getUserApprove());

            return domaine;
        } else {
            return null;
        }
    }

    public static OrderAchatDTO orderAchatToOrderAchatDTO(OrderAchat domaine) {

        if (domaine != null) {
            OrderAchatDTO dto = new OrderAchatDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setUserApprove(domaine.getUserApprove());
            dto.setCodeConsoStandard(domaine.getCodeConsoStandard());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());
            dto.setEtatFactureDTO(EtatFactory.etatFactureToEtatFactureDTO(domaine.getEtatFacture()));
            dto.setCodeEtatFacture(domaine.getCodeEtatFacture());
            dto.setDepotDTO(DepotFactory.depotToDepotDTO(domaine.getDepot()));
            dto.setCodeDepot(domaine.getCodeDepot());

            dto.setEtatReceptionDTO(EtatReceptionFactory.etapReceptionToEtapReceptionDTO(domaine.getEtatReception()));
            dto.setCodeEtatReception(domaine.getCodeEtatReception());

            dto.setFournisseurDesignationAr(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()).getDesignationAr());
            dto.setDepotDesignationAr(DepotFactory.depotToDepotDTO(domaine.getDepot()).getDesignationAr());

            return dto;
        } else {
            return null;
        }
    }

    public static List<OrderAchatDTO> listOrderAchatToOrderAchatDTOs(List<OrderAchat> orderAchats) {
        List<OrderAchatDTO> list = new ArrayList<>();
        for (OrderAchat orderAchat : orderAchats) {
            list.add(orderAchatToOrderAchatDTO(orderAchat));
        }
        return list;
    }

    public static Collection<OrderAchatDTO> CollectionOrderAchatToOrderAchatDTOs(Collection<OrderAchat> orderAchats) {
        Collection<OrderAchatDTO> collection = new ArrayList<>();
        for (OrderAchat orderAchat : orderAchats) {
            collection.add(orderAchatToOrderAchatDTO(orderAchat));
        }
        return collection;
    }

    public static Collection<OrderAchatEdition> flattenOrderAchatForEdition(List<OrderAchat> orderAchats) {
        if (orderAchats == null) {
            return new ArrayList<>();
        }

        List<OrderAchatEdition> flattenedList = new ArrayList<>();

        for (OrderAchat br : orderAchats) {
            // Since getDetailsBonReceptions() is lazy-loaded, ensure you are in a transactional context when calling this.
            // Your service method already is, so this is safe.
            OrderAchatEdition dto = new OrderAchatEdition();
            dto.setObservationFrs(br.getFournisseur().getObservationReceving());
            if (br.getDetailsOrderAchats() != null) {
                for (DetailsOrderAchat detail : br.getDetailsOrderAchats()) {

                    // --- Populate from the parent BonReception ---
                    dto.setCodeSaisie(br.getCodeSaisie());
                    if (br.getFournisseur() != null) {
                        // Assuming you want the Arabic designation as per your SQL
                        dto.setDesignationFrs(br.getFournisseur().getDesignationAr());
                    }
                    dto.setUserCreate(br.getUserCreate());

                    // --- Populate from the child DetailsBonReception ---
                    dto.setQteBesoin(detail.getQteBesoin());
                    if (detail.getArticle() != null) {
                        dto.setCodeSaisieArticle(detail.getArticle().getCodeSaisie());
                        dto.setDesignationArticle(detail.getArticle().getDesignationAr());

                        // Assuming Article has a relationship to a Unite entity
                        if (detail.getArticle().getUnite() != null) {
                            dto.setUnite(detail.getArticle().getUnite().getDesignationAr());
                        }
                    }
                    flattenedList.add(dto);
                }
            }
        }
        return flattenedList;
    }
}
