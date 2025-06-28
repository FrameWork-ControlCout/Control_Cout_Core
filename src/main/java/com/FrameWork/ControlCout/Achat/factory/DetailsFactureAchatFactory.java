/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsFactureAchatFactory {

    public static DetailsFactureAchat createDetailsFactureAchatByCode(int code) {
        DetailsFactureAchat domaine = new DetailsFactureAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsFactureAchatDTO DetailsFactureAchatToDetailsFactureAchatDTONew(DetailsFactureAchat domaine) {
        if (domaine != null) {
            DetailsFactureAchatDTO dto = new DetailsFactureAchatDTO();
            dto.setCode(domaine.getCode());
            dto.setMontantHt(domaine.getMontantHt() == null ? BigDecimal.ZERO : domaine.getMontantHt()); // Handle null montant    
            dto.setMontantTTC(domaine.getMontantTTC() == null ? BigDecimal.ZERO : domaine.getMontantTTC()); // Handle null montant

            dto.setMontantTva(domaine.getMontantTva() == null ? BigDecimal.ZERO : domaine.getMontantTva()); // Handle null montant

            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCaracterstique(domaine.getCaracterstique());

            dto.setQteReceptionner(domaine.getQteReceptionner());

            dto.setPrixUnitaire(domaine.getPrixUnitaire());   
            dto.setPrixUni(domaine.getPrixUnitaire());


            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));

            dto.setPackages(domaine.getArticle().getPackages());
            dto.setCodeSaisieArticle(domaine.getArticle().getCodeSaisie());
            dto.setDesignationArArticle(domaine.getArticle().getDesignationAr());
            dto.setDesignationLtArticle(domaine.getArticle().getDesignationLt());

            dto.setCodeFactureAchat(domaine.getCodeFactureAchat());
            dto.setFactureAchatDTO(FactureAchatFactory.factureAchatToFactureAchatDTO(domaine.getFactureAchat()));

            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));

            dto.setCodeFournisseur(domaine.getCodeFournisseur());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));

            return dto;
        } else {
            return null;
        }
    }

    public static DetailsFactureAchat detailsFactureAchatDTOToDetailsFactureAchat(DetailsFactureAchatDTO dto, DetailsFactureAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCaracterstique(dto.getCaracterstique());

            domaine.setMontantHt(dto.getMontantHt());
            domaine.setMontantTTC(dto.getMontantTTC());
            domaine.setMontantTva(dto.getMontantTva());
            domaine.setQteReceptionner(dto.getQteReceptionner());
            domaine.setPrixUnitaire(dto.getPrixUnitaire());

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }

            domaine.setCodeFactureAchat(dto.getCodeFactureAchat());
            if (domaine.getCodeFactureAchat() != null) {
                domaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(dto.getCodeFactureAchat()));
            }

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }
            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            return domaine;
        } else {
            return null;
        }

    }

    public static List<DetailsFactureAchatDTO> listDetailsFactureAchatToDetailsADmissionDTOs(List<DetailsFactureAchat> detailsFactureAchats) {
        List<DetailsFactureAchatDTO> list = new ArrayList<>();
        for (DetailsFactureAchat detailsFactureAchat : detailsFactureAchats) {
            list.add(DetailsFactureAchatToDetailsFactureAchatDTONew(detailsFactureAchat));
        }
        return list;
    }
}
