/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DepotFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static Depot createDepotByCode(int code) {
        Depot domaine = new Depot();
        domaine.setCode(code);
        return domaine;
    }

    public static Depot depotDTOToDepot(DepotDTO dto, Depot domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
 
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setAutoriseRecep(dto.isAutoriseRecep());

            domaine.setActif(dto.isActif());

            return domaine;
        } else {
            return null;
        }
    }

    public static DepotDTO depotToDepotDTO(Depot domaine) {

        if (domaine != null) {
            DepotDTO dto = new DepotDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr()); 

         
            dto.setAutoriseRecep(domaine.isAutoriseRecep());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DepotDTO> listDepotToDepotDTOs(List<Depot> depots) {
        List<DepotDTO> list = new ArrayList<>();
        for (Depot depot : depots) {
            list.add(depotToDepotDTO(depot));
        }
        return list;
    }
}
