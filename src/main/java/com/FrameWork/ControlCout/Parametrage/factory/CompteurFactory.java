/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.dto.CompteurDTO;
import java.util.ArrayList;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CompteurFactory {
     

    public static Compteur createCompteurByCode(int code) {
        Compteur domaine = new Compteur();
        domaine.setCode(code);
        return domaine;
    }

    public static Compteur compteurDTOToCompteur(CompteurDTO Dto, Compteur domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
 
            domaine.setCompteur(Dto.getCompteur());
            domaine.setPrefixe(Dto.getPrefixe());
            domaine.setSuffixe(Dto.getSuffixe());      
            domaine.setNiveau(Dto.getNiveau()); 

 
 
            return domaine;
        } else {
            return null;
        }
    }

    public static CompteurDTO compteurToCompteurDTO(Compteur domaine) {

        if (domaine != null) {
            CompteurDTO dTO = new CompteurDTO();
            dTO.setCode(domaine.getCode());
            
            dTO.setCompteur(domaine.getCompteur());
            dTO.setPrefixe(domaine.getPrefixe());
            dTO.setSuffixe(domaine.getSuffixe());        

            return dTO;
        } else {
            return null;
        }
    }

    public static List<CompteurDTO> listCompteurToCompteurDTOs(List<Compteur> compteurs) {
        List<CompteurDTO> list = new ArrayList<>();
        for (Compteur compteur : compteurs) {
            list.add(compteurToCompteurDTO(compteur));
        }
        return list;
    }
}
