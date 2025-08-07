/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureBonReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsFactureBonReceptionFactory {

    public static FactureBonReception createFactureBonReceptionByCode(int code) {
        FactureBonReception domaine = new FactureBonReception();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsFactureBonReception detailsfactureBonReceptionDTOTodetailsFactureBonReception(DetailsFactureBonReceptionDTO dto, DetailsFactureBonReception domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setPrixUniAchat(dto.getPrixUniAchat());
            domaine.setPrixTotalAchat(dto.getPrixTotalAchat());

            domaine.setPrixUniGros(dto.getPrixUniGros());
            domaine.setPrixTotalGros(dto.getPrixTotalGros());

            domaine.setDiffPrixUni(dto.getDiffPrixUni());
            domaine.setDiffPrixTotal(dto.getDiffPrixTotal());

            domaine.setCodeFactureBonReception(dto.getCodeFactureBonReception());
            if (domaine.getCodeFactureBonReception() != null) {
                domaine.setFactureBonReception(FactureBonReceptionFactory.createFactureBonReceptionByCode(dto.getCodeFactureBonReception()));
            }
            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }

            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

//            domaine.setCodeDepot(dto.getCodeDepot());
//            if (domaine.getCodeDepot() != null) {
//                domaine.setDepot(DepotFactory.createDepotByCode(dto.getCodeDepot()));
//            }

//            domaine.setCodeDetailsOrderAchat(dto.getCodeDetailsOrderAchat());
//            if (domaine.getCodeDetailsOrderAchat() != null) {
//                domaine.setDetailsOrderAchat(DetailsOrderAchatFactory.createOrderAchatByCode(dto.getCodeDetailsOrderAchat()));
//            }
            domaine.setQteBesoin(dto.getQteBesoin());
            domaine.setQteReceptionner(dto.getQteReceptionner());

            return domaine;
        } else {
            return null;
        }
    }

    public static DetailsFactureBonReceptionDTO detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(DetailsFactureBonReception domaine) {

        if (domaine != null) {
            DetailsFactureBonReceptionDTO dto = new DetailsFactureBonReceptionDTO();
            dto.setCode(domaine.getCode());

            dto.setFactureBonReceptionDTO(FactureBonReceptionFactory.factureBonReceptionToFactureBonReceptionDTO(domaine.getFactureBonReception()));
            dto.setCodeFactureBonReception(domaine.getCodeFactureBonReception());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));
            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setPrixUniAchat(domaine.getPrixUniAchat());
            dto.setPrixTotalAchat(domaine.getPrixTotalAchat());

            dto.setPrixUniGros(domaine.getPrixUniGros());
            dto.setPrixTotalGros(domaine.getPrixTotalGros());

            dto.setDiffPrixUni(domaine.getDiffPrixUni());
            dto.setDiffPrixTotal(domaine.getDiffPrixTotal());

            dto.setQteReceptionner(domaine.getQteReceptionner());
            dto.setQteBesoin(domaine.getQteBesoin());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());
//            dto.setDetailsOrderAchatDTO(DetailsOrderAchatFactory.detailsorderAchatToDetailsOrderAchatDTO(domaine.getDetailsOrderAchat()));
//            dto.setCodeDetailsOrderAchat(domaine.getCodeDetailsOrderAchat());

//            dto.setDepotDTO(DepotFactory.depotToDepotDTO(domaine.getDepot()));
//            dto.setCodeDepot(domaine.getCodeDepot());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsFactureBonReceptionDTO> listDetailsFactureBonReceptionToDetailsFactureBonReceptionDTOs(List<DetailsFactureBonReception> detailsFactureBonReceptions) {
        List<DetailsFactureBonReceptionDTO> list = new ArrayList<>();
        for (DetailsFactureBonReception detailsFactureBonReception : detailsFactureBonReceptions) {
            list.add(detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(detailsFactureBonReception));
        }
        return list;
    }

    public static Collection<DetailsFactureBonReceptionDTO> CollectionDetailsFactureBonReceptionToDetailsFactureBonReceptionDTOs(Collection<DetailsFactureBonReception> detailsFactureBonReceptions) {
        Collection<DetailsFactureBonReceptionDTO> collection = new ArrayList<>();
        for (DetailsFactureBonReception detailsFactureBonReception : detailsFactureBonReceptions) {
            collection.add(detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(detailsFactureBonReception));
        }
        return collection;
    }
}
