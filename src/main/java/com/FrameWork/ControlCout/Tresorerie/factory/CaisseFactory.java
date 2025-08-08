/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;
 
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.Caisse;
import com.FrameWork.ControlCout.Tresorerie.dto.CaisseDTO;
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
public class CaisseFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static Caisse createCaisseByCode(int code) {
        Caisse domaine = new Caisse();
        domaine.setCode(code);
        return domaine;
    }

    public static Caisse caisseDTOToCaisse(CaisseDTO dto, Caisse domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());
            
                        domaine.setType(dto.getType());


            domaine.setActif(dto.isActif());
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));
            }

            domaine.setCodeTypeCaisse(dto.getCodeTypeCaisse());
            if (domaine.getCodeTypeCaisse() != null) {
                domaine.setTypeCaisse(TypeCaisseFactory.createTypeCaisseByCode(dto.getCodeTypeCaisse()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static CaisseDTO caisseToCaisseDTO(Caisse domaine) {

        if (domaine != null) {
            CaisseDTO dto = new CaisseDTO();
            dto.setCode(domaine.getCode());

            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setType(domaine.getType());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            dto.setTypeCaisseDTO(TypeCaisseFactory.typeCaisseToTypeCaisseDTO(domaine.getTypeCaisse()));
            dto.setCodeTypeCaisse(domaine.getCodeTypeCaisse());

            return dto;
        } else {
            return null;
        }
    }

    public static List<CaisseDTO> listCaisseToCaisseDTOs(List<Caisse> caisses) {
        List<CaisseDTO> list = new ArrayList<>();
        for (Caisse caisse : caisses) {
            list.add(caisseToCaisseDTO(caisse));
        }
        return list;
    }
}
