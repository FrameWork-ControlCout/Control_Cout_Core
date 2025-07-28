/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatFactory;
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
public class DetailsBonReceptionFactory {

    public static BonReception createBonReceptionByCode(int code) {
        BonReception domaine = new BonReception();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsBonReception detailsbonReceptionDTOTodetailsBonReception(DetailsBonReceptionDTO dto, DetailsBonReception domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setPrixUni(dto.getPrixUni());
            domaine.setPrixTotal(dto.getPrixTotal());

            domaine.setCodeBonReception(dto.getCodeBonReception());
            if (domaine.getCodeBonReception() != null) {
                domaine.setBonReception(BonReceptionFactory.createBonReceptionByCode(dto.getCodeBonReception()));
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

            domaine.setCodeDepot(dto.getCodeDepot());
            if (domaine.getCodeDepot() != null) {
                domaine.setDepot(DepotFactory.createDepotByCode(dto.getCodeDepot()));
            }

            domaine.setCodeDetailsOrderAchat(dto.getCodeDetailsOrderAchat());
            if (domaine.getCodeDetailsOrderAchat() != null) {
                domaine.setDetailsOrderAchat(DetailsOrderAchatFactory.createOrderAchatByCode(dto.getCodeDetailsOrderAchat()));
            }
            domaine.setQteBesoin(dto.getQteBesoin());
            domaine.setQteReceptionner(dto.getQteReceptionner());

            return domaine;
        } else {
            return null;
        }
    }

    public static DetailsBonReceptionDTO detailsbonReceptionToDetailsBonReceptionDTO(DetailsBonReception domaine) {

        if (domaine != null) {
            DetailsBonReceptionDTO dto = new DetailsBonReceptionDTO();
            dto.setCode(domaine.getCode());

            dto.setBonReceptionDTO(BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine.getBonReception()));
            dto.setCodeBonReception(domaine.getCodeBonReception());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));
            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setPrixUni(domaine.getPrixUni());
            dto.setPrixTotal(domaine.getPrixTotal());

            dto.setQteReceptionner(domaine.getQteReceptionner());
            dto.setQteBesoin(domaine.getQteBesoin());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());
            dto.setDetailsOrderAchatDTO(DetailsOrderAchatFactory.detailsorderAchatToDetailsOrderAchatDTO(domaine.getDetailsOrderAchat()));
            dto.setCodeDetailsOrderAchat(domaine.getCodeDetailsOrderAchat());

            dto.setDepotDTO(DepotFactory.depotToDepotDTO(domaine.getDepot()));
            dto.setCodeDepot(domaine.getCodeDepot());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsBonReceptionDTO> listBonReceptionToDetailsBonReceptionDTOs(List<DetailsBonReception> detailsBonReceptions) {
        List<DetailsBonReceptionDTO> list = new ArrayList<>();
        for (DetailsBonReception detailsBonReception : detailsBonReceptions) {
            list.add(detailsbonReceptionToDetailsBonReceptionDTO(detailsBonReception));
        }
        return list;
    }

    public static Collection<DetailsBonReceptionDTO> CollectionBonReceptionToDetailsBonReceptionDTOs(Collection<DetailsBonReception> detailsBonReceptions) {
        Collection<DetailsBonReceptionDTO> collection = new ArrayList<>();
        for (DetailsBonReception detailsBonReception : detailsBonReceptions) {
            collection.add(detailsbonReceptionToDetailsBonReceptionDTO(detailsBonReception));
        }
        return collection;
    }
}
