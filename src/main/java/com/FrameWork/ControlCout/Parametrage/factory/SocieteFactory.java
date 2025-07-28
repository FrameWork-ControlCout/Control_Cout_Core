/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SocieteFactory {

    public static Societe createSocieteByCode(int code) {
        Societe domaine = new Societe();
        domaine.setCode(code);
        return domaine;
    }

    public static Societe societeDTOToSociete(SocieteDTO dto, Societe domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setNbrePerson(dto.getNbrePerson());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setActif(dto.isActif());

            domaine.setCodeDepot(dto.getCodeDepot());
            if (domaine.getCodeDepot() != null) {
                domaine.setDepot(DepotFactory.createDepotByCode(dto.getCodeDepot()));
            }
            return domaine;
        } else {
            return null;
        }
    }

    public static SocieteDTO societeToSocieteDTO(Societe domaine) {

        if (domaine != null) {
            SocieteDTO dto = new SocieteDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setNbrePerson(domaine.getNbrePerson());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCodeDepot(domaine.getCodeDepot());
            dto.setDepotDTO(DepotFactory.depotToDepotDTO(domaine.getDepot()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<SocieteDTO> listSocieteToSocieteDTOs(List<Societe> societes) {
        List<SocieteDTO> list = new ArrayList<>();
        for (Societe societe : societes) {
            list.add(societeToSocieteDTO(societe));
        }
        return list;
    }
}
