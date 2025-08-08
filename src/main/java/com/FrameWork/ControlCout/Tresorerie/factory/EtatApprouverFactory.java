/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;
 
import com.FrameWork.ControlCout.Tresorerie.domaine.EtatApprouver;
import com.FrameWork.ControlCout.Tresorerie.dto.EtatApprouverDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatApprouverFactory {
    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static EtatApprouver createEtatApprouverByCode(int code) {
        EtatApprouver domaine = new EtatApprouver();
        domaine.setCode(code);
        return domaine;
    }

    public static EtatApprouver etatApprouverDTOToEtatApprouver(EtatApprouverDTO Dto, EtatApprouver domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
         
            domaine.setDesignation(Dto.getDesignation());
             

            return domaine;
        } else {
            return null;
        }
    }

    public static EtatApprouverDTO etatApprouverToEtatApprouverDTO(EtatApprouver domaine) {

        if (domaine != null) {
            EtatApprouverDTO dTO = new EtatApprouverDTO();
            dTO.setCode(domaine.getCode());
          
            dTO.setDesignation(domaine.getDesignation()); 
            
            return dTO;
        } else {
            return null;
        }
    }

    public static List<EtatApprouverDTO> listEtatApprouverToEtatApprouverDTOs(List<EtatApprouver> ds) {
        List<EtatApprouverDTO> list = new ArrayList<>();
        for (EtatApprouver approuver : ds) {
            list.add(etatApprouverToEtatApprouverDTO(approuver));
        }
        return list;
    }

    public static Collection<EtatApprouverDTO> listEtatApprouverToEtatApprouverDTOsCollection(Collection<EtatApprouver> collection) {
        List<EtatApprouverDTO> dTOs = new ArrayList<>();
        collection.forEach(x -> {
            dTOs.add(etatApprouverToEtatApprouverDTO(x));
        });
        return dTOs;

    }
}
