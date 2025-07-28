/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
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
public class DetailsOrderAchatFactory {

    public static DetailsOrderAchat createOrderAchatByCode(int code) {
        DetailsOrderAchat domaine = new DetailsOrderAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsOrderAchat detailsorderAchatDTOTodetailsOrderAchat(DetailsOrderAchatDTO dto, DetailsOrderAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeOrderAchat(dto.getCodeOrderAchat());
            if (domaine.getCodeOrderAchat() != null) {
                domaine.setOrderAchat(OrderAchatFactory.createOrderAchatByCode(dto.getCodeOrderAchat()));
            }
            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }

            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            domaine.setCaracterstique(dto.getCaracterstique());

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setQteBesoin(dto.getQteBesoin());
            domaine.setQteDejaReceptionner(dto.getQteDejaReceptionner());

            domaine.setConsoStandard(dto.isConsoStandard());

            domaine.setSatisfait(dto.isSatisfait());
            domaine.setCodeEtatReception(dto.getCodeEtatReception());
            if (domaine.getCodeEtatReception() != null) {
                domaine.setEtatReception(EtatReceptionFactory.createEtapReceptionByCode(dto.getCodeEtatReception()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static DetailsOrderAchatDTO detailsorderAchatToDetailsOrderAchatDTO(DetailsOrderAchat domaine) {

        if (domaine != null) {
            DetailsOrderAchatDTO dto = new DetailsOrderAchatDTO();
            dto.setCode(domaine.getCode());

            dto.setOrderAchatDTO(OrderAchatFactory.orderAchatToOrderAchatDTO(domaine.getOrderAchat()));
            dto.setCodeOrderAchat(domaine.getCodeOrderAchat());

            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));
            dto.setCodeArticle(domaine.getCodeArticle());

            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());

            dto.setCaracterstique(domaine.getCaracterstique());

            dto.setQteBesoin(domaine.getQteBesoin());
            dto.setQteDejaReceptionner(domaine.getQteDejaReceptionner());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setConsoStandard(domaine.isConsoStandard());
            dto.setSatisfait(domaine.isSatisfait());

            dto.setEtatReceptionDTO(EtatReceptionFactory.etapReceptionToEtapReceptionDTO(domaine.getEtatReception()));
            dto.setCodeEtatReception(domaine.getCodeEtatReception());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsOrderAchatDTO> listOrderAchatToDetailsOrderAchatDTOs(List<DetailsOrderAchat> detailsOrderAchats) {
        List<DetailsOrderAchatDTO> list = new ArrayList<>();
        for (DetailsOrderAchat detailsOrderAchat : detailsOrderAchats) {
            list.add(detailsorderAchatToDetailsOrderAchatDTO(detailsOrderAchat));
        }
        return list;
    }

    public static Collection<DetailsOrderAchatDTO> CollectionOrderAchatToDetailsOrderAchatDTOs(Collection<DetailsOrderAchat> detailsOrderAchats) {
        Collection<DetailsOrderAchatDTO> collection = new ArrayList<>();
        for (DetailsOrderAchat detailsOrderAchat : detailsOrderAchats) {
            collection.add(detailsorderAchatToDetailsOrderAchatDTO(detailsOrderAchat));
        }
        return collection;
    }
}
