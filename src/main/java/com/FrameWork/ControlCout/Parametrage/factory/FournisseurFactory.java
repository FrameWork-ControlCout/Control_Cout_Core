/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class FournisseurFactory {

    public static Fournisseur createFournisseurByCode(int code) {
        Fournisseur domaine = new Fournisseur();
        domaine.setCode(code);
        return domaine;
    }

    public static Fournisseur fournisseurDTOToFournisseur(FournisseurDTO dto, Fournisseur domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());
            domaine.setAdress(dto.getAdress());
            domaine.setNumTel(dto.getNumTel());

            return domaine;
        } else {
            return null;
        }
    }

    public static FournisseurDTO fournisseurToFournisseurDTO(Fournisseur domaine) {

        if (domaine != null) {
            FournisseurDTO dto = new FournisseurDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setAdress(domaine.getAdress());
            dto.setNumTel(domaine.getNumTel());

            return dto;
        } else {
            return null;
        }
    }

    public static List<FournisseurDTO> listFournisseurToFournisseurDTOs(List<Fournisseur> fournisseurs) {
        List<FournisseurDTO> list = new ArrayList<>();
        for (Fournisseur fournisseur : fournisseurs) {
            list.add(fournisseurToFournisseurDTO(fournisseur));
        }
        return list;
    }
}
