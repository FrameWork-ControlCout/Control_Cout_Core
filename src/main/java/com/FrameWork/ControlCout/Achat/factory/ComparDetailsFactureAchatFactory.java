/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.ComparDetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.dto.ComparDetailsFactureAchatDTO;
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
public class ComparDetailsFactureAchatFactory {

    public static ComparDetailsFactureAchat createComparDetailsFactureAchatByCode(int code) {
        ComparDetailsFactureAchat domaine = new ComparDetailsFactureAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static ComparDetailsFactureAchatDTO ComparDetailsFactureAchatToComparDetailsFactureAchatDTONew(ComparDetailsFactureAchat domaine) {
        if (domaine != null) {
            ComparDetailsFactureAchatDTO dto = new ComparDetailsFactureAchatDTO();
            dto.setCode(domaine.getCode());
            dto.setMontantTTCGros(domaine.getMontantTTCGros() == null ? BigDecimal.ZERO : domaine.getMontantTTCGros()); // Handle null montant    
            dto.setMontantTTC(domaine.getMontantTTC() == null ? BigDecimal.ZERO : domaine.getMontantTTC()); // Handle null montant

            dto.setPrixUnitaireGros(domaine.getPrixUnitaireGros() == null ? BigDecimal.ZERO : domaine.getPrixUnitaireGros()); // Handle null montant
            dto.setPrixUnitaire(domaine.getPrixUnitaire() == null ? BigDecimal.ZERO : domaine.getPrixUnitaire());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setCaracterstique(domaine.getCaracterstique());

            dto.setQteReceptionner(domaine.getQteReceptionner());

            dto.setDiffPrixUni(domaine.getDiffPrixUni());
            dto.setDiffPrixTotal(domaine.getDiffPrixTotal());

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

            dto.setCodeFournisseurCompar(domaine.getCodeFournisseurCompar());
            dto.setFournisseurDTOCompar(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseurCompar()));

            return dto;
        } else {
            return null;
        }
    }

    public static ComparDetailsFactureAchat detailsFactureAchatDTOToComparDetailsFactureAchat(ComparDetailsFactureAchatDTO dto, ComparDetailsFactureAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCaracterstique(dto.getCaracterstique());

            domaine.setMontantTTC(dto.getMontantTTC());

            domaine.setMontantTTCGros(dto.getMontantTTCGros());
            domaine.setPrixUnitaireGros(dto.getPrixUnitaireGros());

            domaine.setQteReceptionner(dto.getQteReceptionner());
            domaine.setPrixUnitaire(dto.getPrixUnitaire());
            domaine.setDiffPrixTotal(dto.getDiffPrixUni());
            domaine.setDiffPrixTotal(dto.getDiffPrixTotal());

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

            domaine.setCodeFournisseurCompar(dto.getCodeFournisseurCompar());
            if (domaine.getCodeFournisseurCompar() != null) {
                domaine.setFournisseurCompar(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseurCompar()));
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

    public static List<ComparDetailsFactureAchatDTO> listComparDetailsFactureAchatToDetailsADmissionDTOs(List<ComparDetailsFactureAchat> detailsFactureAchats) {
        List<ComparDetailsFactureAchatDTO> list = new ArrayList<>();
        for (ComparDetailsFactureAchat detailsFactureAchat : detailsFactureAchats) {
            list.add(ComparDetailsFactureAchatToComparDetailsFactureAchatDTONew(detailsFactureAchat));
        }
        return list;
    }
}
