/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;

import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementFournisseur;
import com.FrameWork.ControlCout.Tresorerie.dto.MouvementFournisseurDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class MouvementFournisseurFactory {

    public static MouvementFournisseur createMouvementFournisseurByCode(int code) {
        MouvementFournisseur domaine = new MouvementFournisseur();
        domaine.setCode(code);
        return domaine;
    }

    public static MouvementFournisseur mouvementFournisseurDTOToMouvementFournisseur(MouvementFournisseurDTO dto, MouvementFournisseur domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeFactureBonReception(dto.getCodeFactureBonReception());
            domaine.setPrixFacture(dto.getPrixFacture());
            domaine.setDeductionFacture(dto.getDeductionFacture());
            domaine.setCodeOrderPaiement(dto.getCodeOrderPaiement());
            domaine.setTypeMouvement(dto.getTypeMouvement());

            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeEtatPaiement(dto.getCodeEtatPaiement());
            if (domaine.getCodeEtatPaiement() != null) {
                domaine.setEtatPaiement(EtatPaiementFactory.createEtatPaiementFactureByCode(dto.getCodeEtatPaiement()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static MouvementFournisseurDTO mouvementFournisseurToMouvementFournisseurDTO(MouvementFournisseur domaine) {

        if (domaine != null) {
            MouvementFournisseurDTO dto = new MouvementFournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeFactureBonReception(domaine.getCodeFactureBonReception());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setPrixFacture(domaine.getPrixFacture());
            dto.setDeductionFacture(domaine.getDeductionFacture());

            dto.setCodeOrderPaiement(domaine.getCodeOrderPaiement());
            dto.setTypeMouvement(domaine.getTypeMouvement());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setEtatPaiementDTO(EtatPaiementFactory.etatPaiementToEtatPaiementDTO(domaine.getEtatPaiement()));
            dto.setCodeEtatPaiement(domaine.getCodeEtatPaiement());

            return dto;
        } else {
            return null;
        }
    }

    public static List<MouvementFournisseurDTO> listMouvementFournisseurToMouvementFournisseurDTOs(List<MouvementFournisseur> mouvementFournisseurs) {
        List<MouvementFournisseurDTO> list = new ArrayList<>();
        for (MouvementFournisseur mouvementFournisseur : mouvementFournisseurs) {
            list.add(mouvementFournisseurToMouvementFournisseurDTO(mouvementFournisseur));
        }
        return list;
    }

    public static Collection<MouvementFournisseurDTO> CollectionMouvementFournisseurToMouvementFournisseurDTOs(Collection<MouvementFournisseur> mouvementFournisseurs) {
        Collection<MouvementFournisseurDTO> collection = new ArrayList<>();
        for (MouvementFournisseur mouvementFournisseur : mouvementFournisseurs) {
            collection.add(mouvementFournisseurToMouvementFournisseurDTO(mouvementFournisseur));
        }
        return collection;
    }

}
