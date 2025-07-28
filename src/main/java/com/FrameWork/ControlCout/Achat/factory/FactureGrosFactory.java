/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.FactureGros;
import com.FrameWork.ControlCout.Achat.dto.FactureGrosDTO;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
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
public class FactureGrosFactory {

    public static FactureGros createFactureGrosByCode(int code) {
        FactureGros domaine = new FactureGros();
        domaine.setCode(code);
        return domaine;
    }

    public static FactureGros factureGrosDTOToFactureGros(FactureGrosDTO dto, FactureGros domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeOrderAchat(dto.getCodeOrderAchat());
            if (domaine.getCodeOrderAchat() != null) {
                domaine.setOrderAchat(OrderAchatFactory.createOrderAchatByCode(dto.getCodeOrderAchat()));
            }

            domaine.setPrixTotal(dto.getPrixTotal());

            return domaine;
        } else {
            return null;
        }
    }

    public static FactureGrosDTO factureGrosToFactureGrosDTO(FactureGros domaine) {

        if (domaine != null) {
            FactureGrosDTO dto = new FactureGrosDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate());
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setOrderAchatDTO(OrderAchatFactory.orderAchatToOrderAchatDTO(domaine.getOrderAchat()));
            dto.setCodeOrderAchat(domaine.getCodeOrderAchat());

            dto.setPrixTotal(domaine.getPrixTotal());

            return dto;
        } else {
            return null;
        }
    }
    
    
    public static FactureGrosDTO factureGrosToFactureGrosDTOLazy(FactureGros domaine) {

        if (domaine != null) {
            FactureGrosDTO dto = new FactureGrosDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUsercreate(domaine.getUsercreate()); 
            dto.setCodeFournisseur(domaine.getCodeFournisseur()); 
            dto.setCodeOrderAchat(domaine.getCodeOrderAchat()); 
            dto.setPrixTotal(domaine.getPrixTotal());

            return dto;
        } else {
            return null;
        }
    }

    public static List<FactureGrosDTO> listFactureGrosToFactureGrosDTOs(List<FactureGros> factureGross) {
        List<FactureGrosDTO> list = new ArrayList<>();
        for (FactureGros factureGros : factureGross) {
            list.add(factureGrosToFactureGrosDTO(factureGros));
        }
        return list;
    }

    public static Collection<FactureGrosDTO> CollectionFactureGrosToFactureGrosDTOs(Collection<FactureGros> factureGross) {
        Collection<FactureGrosDTO> collection = new ArrayList<>();
        for (FactureGros factureGros : factureGross) {
            collection.add(factureGrosToFactureGrosDTO(factureGros));
        }
        return collection;
    }
}
