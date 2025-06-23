/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import com.FrameWork.ControlCout.Achat.dto.FactureAchatDTO;
import com.FrameWork.ControlCout.Parametrage.factory.EtatFactureFactory;
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
public class FactureAchatFactory {

    public static FactureAchat createFactureAchatByCode(int code) {
        FactureAchat domaine = new FactureAchat();
        domaine.setCode(code);
        return domaine;
    }

    public static FactureAchat factureAchatDTOToFactureAchat(FactureAchatDTO dto, FactureAchat domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeEtatFacture(dto.getCodeEtatFacture());
            if (domaine.getCodeEtatFacture() != null) {
                domaine.setEtatFacture(EtatFactureFactory.createEtatFactureByCode(dto.getCodeEtatFacture()));
            }
             domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            

            domaine.setUserApprove(dto.getUserApprove());
            domaine.setMontantHt(dto.getMontantHt());    
            domaine.setMontantTTC(dto.getMontantTTC());
            domaine.setMontantTva(dto.getMontantTva()); 

            return domaine;
        } else {
            return null;
        }
    }

    public static FactureAchatDTO factureAchatToFactureAchatDTO(FactureAchat domaine) {

        if (domaine != null) {
            FactureAchatDTO dto = new FactureAchatDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());  
            dto.setUserApprove(domaine.getUserApprove()); 
 
            
            dto.setMontantHt(domaine.getMontantHt());
            dto.setMontantTTC(domaine.getMontantTTC());
            dto.setMontantTva(domaine.getMontantTva());


            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

             dto.setEtatFactureDTO(EtatFactureFactory.etatFactureToEtatFactureDTO(domaine.getEtatFacture()));
            dto.setCodeEtatFacture(domaine.getCodeEtatFacture());
            
            return dto;
        } else {
            return null;
        }
    }

    public static List<FactureAchatDTO> listFactureAchatToFactureAchatDTOs(List<FactureAchat> factureAchats) {
        List<FactureAchatDTO> list = new ArrayList<>();
        for (FactureAchat factureAchat : factureAchats) {
            list.add(factureAchatToFactureAchatDTO(factureAchat));
        }
        return list;
    }
    
     public static Collection<FactureAchatDTO> CollectionFactureAchatToFactureAchatDTOs(Collection<FactureAchat> factureAchats) {
        Collection<FactureAchatDTO> collection = new ArrayList<>();
        for (FactureAchat factureAchat : factureAchats) {
            collection.add(factureAchatToFactureAchatDTO(factureAchat));
        }
        return collection;
    }
}
