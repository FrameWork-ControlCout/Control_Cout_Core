/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Banque;
import com.FrameWork.ControlCout.Parametrage.dto.BanqueDTO;
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
public class BanqueFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static Banque createBanqueByCode(int code) {
        Banque domaine = new Banque();
        domaine.setCode(code);
        return domaine;
    }

    public static Banque banqueDTOToBanque(BanqueDTO dto, Banque domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setDesignationLt(dto.getDesignationLt());
            domaine.setDesignationAr(dto.getDesignationAr());
            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setRib(dto.getRib());

            domaine.setActif(dto.isActif());

            return domaine;
        } else {
            return null;
        }
    }

    public static BanqueDTO banqueToBanqueDTO(Banque domaine) {

        if (domaine != null) {
            BanqueDTO dto = new BanqueDTO();
            dto.setCode(domaine.getCode());

            dto.setDesignationAr(domaine.getDesignationAr());
            dto.setDesignationLt(domaine.getDesignationLt());

            if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
                dto.setDesignationAr(domaine.getDesignationAr());
                dto.setDesignationLt(domaine.getDesignationLt());
            } else {
                dto.setDesignationAr(domaine.getDesignationLt());
                dto.setDesignationLt(domaine.getDesignationAr());
            }

            dto.setRib(domaine.getRib());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setActif(domaine.isActif());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            return dto;
        } else {
            return null;
        }
    }

    public static List<BanqueDTO> listBanqueToBanqueDTOs(List<Banque> banques) {
        List<BanqueDTO> list = new ArrayList<>();
        for (Banque banque : banques) {
            list.add(banqueToBanqueDTO(banque));
        }
        return list;
    }
}
