/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO; 
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
public class FactureBonReceptionFactory {

    public static FactureBonReception createFactureBonReceptionByCode(int code) {
        FactureBonReception domaine = new FactureBonReception();
        domaine.setCode(code);
        return domaine;
    }

    public static FactureBonReception factureBonReceptionDTOToFactureBonReception(FactureBonReceptionDTO dto, FactureBonReception domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setPrixTotal(dto.getPrixTotal());

            domaine.setNumFactFrs(dto.getNumFactFrs());
            domaine.setMontantDiffPrix(dto.getMontantDiffPrix());
            domaine.setDateFactFrs(dto.getDateFactFrs());

            domaine.setCodeSaisie(dto.getCodeSaisie());
        
             domaine.setCodeBonReception(dto.getCodeBonReception());
            if (domaine.getCodeBonReception() != null) {
                domaine.setBonReception(BonReceptionFactory.createBonReceptionByCode(dto.getCodeBonReception()));
            }
 
            
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }
 
            

            return domaine;
        } else {
            return null;
        }
    }

    public static FactureBonReceptionDTO factureBonReceptionToFactureBonReceptionDTO(FactureBonReception domaine) {

        if (domaine != null) {
            FactureBonReceptionDTO dto = new FactureBonReceptionDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setPrixTotal(domaine.getPrixTotal());

            dto.setMontantDiffPrix(domaine.getMontantDiffPrix());
            dto.setNumFactFrs(domaine.getNumFactFrs());
            dto.setDateFactFrs(domaine.getDateFactFrs());

                  dto.setBonReceptionDTO(BonReceptionFactory.bonReceptionToBonReceptionDTO(domaine.getBonReception()));
            dto.setCodeBonReception(domaine.getCodeBonReception());
            
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

        
            return dto;
        } else {
            return null;
        }
    }

    public static List<FactureBonReceptionDTO> listFactureBonReceptionToFactureBonReceptionDTOs(List<FactureBonReception> factureBonReceptions) {
        List<FactureBonReceptionDTO> list = new ArrayList<>();
        for (FactureBonReception factureBonReception : factureBonReceptions) {
            list.add(factureBonReceptionToFactureBonReceptionDTO(factureBonReception));
        }
        return list;
    }

    public static Collection<FactureBonReceptionDTO> CollectionFactureBonReceptionToFactureBonReceptionDTOs(Collection<FactureBonReception> factureBonReceptions) {
        Collection<FactureBonReceptionDTO> collection = new ArrayList<>();
        for (FactureBonReception factureBonReception : factureBonReceptions) {
            collection.add(factureBonReceptionToFactureBonReceptionDTO(factureBonReception));
        }
        return collection;
    }

}
