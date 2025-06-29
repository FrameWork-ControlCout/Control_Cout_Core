/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.ComparFactureAchat;
import com.FrameWork.ControlCout.Achat.dto.ComparFactureAchatDTO;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ComparFactureAchatFactory {

    public static ComparFactureAchat createComparFactureAchatByCode(int code) {
        ComparFactureAchat domaine = new ComparFactureAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static ComparFactureAchat ComparfactureAchatDTOToComparFactureAchat(ComparFactureAchatDTO dto, ComparFactureAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeFournisseurCompar(dto.getCodeFournisseurCompar());
            if (domaine.getCodeFournisseurCompar() != null) {
                domaine.setFournisseurCompar(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseurCompar()));
            }

            domaine.setCodeFactureAchat(dto.getCodeFactureAchat());
            if (domaine.getCodeFactureAchat() != null) {
                domaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(dto.getCodeFactureAchat()));
            }

            domaine.setNumFactFrs(dto.getNumFactFrs());
            domaine.setMontantFactFrs(dto.getMontantFactFrs());

            domaine.setMontantTTCGros(dto.getMontantTTCGros());
            domaine.setMontantTTC(dto.getMontantTTC());

            domaine.setDifftotalPrixUniMarche(dto.getDifftotalPrixUniMarche());
            domaine.setDifftotalMontantMarche(dto.getDifftotalMontantMarche());

            return domaine;
        } else {
            return null;
        }
    }

    public static ComparFactureAchatDTO ComparfactureAchatToComparFactureAchatDTO(ComparFactureAchat domaine) {

        if (domaine != null) {
            ComparFactureAchatDTO dto = new ComparFactureAchatDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setMontantTTCGros(domaine.getMontantTTCGros());
            dto.setMontantTTC(domaine.getMontantTTC());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setFournisseurComparDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseurCompar()));
            dto.setCodeFournisseurCompar(domaine.getCodeFournisseurCompar());

            dto.setFactureAchatDTO(FactureAchatFactory.factureAchatToFactureAchatDTO(domaine.getFactureAchat()));
            dto.setCodeFactureAchat(domaine.getCodeFactureAchat());

            dto.setMontantFactFrs(domaine.getMontantFactFrs());
            dto.setNumFactFrs(domaine.getNumFactFrs());

            dto.setDifftotalPrixUniMarche(domaine.getDifftotalPrixUniMarche());
            dto.setDifftotalMontantMarche(domaine.getDifftotalMontantMarche());

            return dto;
        } else {
            return null;
        }
    }

    public static List<ComparFactureAchatDTO> listComparFactureAchatToComparFactureAchatDTOs(List<ComparFactureAchat> ComparfactureAchats) {
        List<ComparFactureAchatDTO> list = new ArrayList<>();
        for (ComparFactureAchat ComparfactureAchat : ComparfactureAchats) {
            list.add(ComparfactureAchatToComparFactureAchatDTO(ComparfactureAchat));
        }
        return list;
    }

    public static Collection<ComparFactureAchatDTO> CollectionComparFactureAchatToComparFactureAchatDTOs(Collection<ComparFactureAchat> ComparfactureAchats) {
        Collection<ComparFactureAchatDTO> collection = new ArrayList<>();
        for (ComparFactureAchat ComparfactureAchat : ComparfactureAchats) {
            collection.add(ComparfactureAchatToComparFactureAchatDTO(ComparfactureAchat));
        }
        return collection;
    }
}
