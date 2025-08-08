/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;

import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.AvanceFournisseur;
import com.FrameWork.ControlCout.Tresorerie.dto.AvanceFournisseurDTO;
import com.FrameWork.ControlCout.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AvanceFournisseurFactory {
    
    public static AvanceFournisseur createAvanceFournisseurByCode(int code) {
        AvanceFournisseur domaine = new AvanceFournisseur();
        domaine.setCode(code);
        return domaine;
    }
 
    
    public static AvanceFournisseur avanceFournisserDTOToAvanceFournisseur( AvanceFournisseur domaine ,AvanceFournisseurDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
 
            domaine.setCodeSaisie(dto.getCodeSaisie()); 
            domaine.setMntAvance(dto.getMntAvance());
            domaine.setMontantEnDevise(dto.getMontantEnDevise());
            domaine.setTauxChange(dto.getTauxChange()); 

            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }

            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

            }
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));

            }

            domaine.setAppurer(dto.isAppurer());
          
            
             

            return domaine;
        } else {
            return null;
        }
    }

    public static AvanceFournisseurDTO avanceFournisserToAvanceFournisseurDTO(AvanceFournisseur domaine) {

        if (domaine != null) {
            AvanceFournisseurDTO dto = new AvanceFournisseurDTO();
            dto.setCode(domaine.getCode()); 
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setMntAvance(domaine.getMntAvance());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());       
            dto.setTauxChange(domaine.getTauxChange()); 

            

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

             

            return dto;
        } else {
            return null;
        }
    }

    
     
    
    
    public static List<AvanceFournisseurDTO> listAvanceFournisseurToAvanceFournisseurDTOs(List<AvanceFournisseur> avanceFournisseurs) {
        List<AvanceFournisseurDTO> list = new ArrayList<>();
        for (AvanceFournisseur avanceFournisser : avanceFournisseurs) {
            list.add(avanceFournisserToAvanceFournisseurDTO(avanceFournisser));
        }
        return list;
    }

    public static Collection<AvanceFournisseurDTO> CollectionavanceFournissersToavanceFournissersDTOsCollection(Collection<AvanceFournisseur> avanceFournissers) {
        List<AvanceFournisseurDTO> dtos = new ArrayList<>();
        avanceFournissers.forEach(x -> {
            dtos.add(avanceFournisserToAvanceFournisseurDTO(x));
        });
        return dtos;

    }

 
}
