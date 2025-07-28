/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.Edition.ConsoStandardEdition;
import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandardPerDay;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class ConsoStandardFactory {

    public static ConsoStandard createConsoStandardByCode(int code) {
        ConsoStandard domaine = new ConsoStandard();
        domaine.setCode(code);
        return domaine;
    }

    public static ConsoStandard consoStandardDTOToConsoStandard(ConsoStandardDTO dto, ConsoStandard domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setActif(dto.isActif());
            domaine.setSatisfait(dto.isSatisfait());
            domaine.setNbrePerson(dto.getNbrePerson());
            domaine.setDateDebut(dto.getDateDebut());
            domaine.setDateFin(dto.getDateFin());

            domaine.setCodeSociete(dto.getCodeSociete());
            if (domaine.getCodeSociete() != null) {
                domaine.setSociete(SocieteFactory.createSocieteByCode(dto.getCodeSociete()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static ConsoStandardDTO consoStandardToConsoStandardDTO(ConsoStandard domaine) {

        if (domaine != null) {
            ConsoStandardDTO dto = new ConsoStandardDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setDateDebut(domaine.getDateDebut());
            dto.setDateFin(domaine.getDateFin());
            dto.setActif(domaine.isActif());
            dto.setSatisfait(domaine.isSatisfait());
            dto.setNbrePerson(domaine.getNbrePerson());
            dto.setCodeSociete(domaine.getCodeSociete());
            dto.setSocieteDTO(SocieteFactory.societeToSocieteDTO(domaine.getSociete()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<ConsoStandardDTO> listConsoStandardToConsoStandardDTOs(List<ConsoStandard> consoStandards) {
        List<ConsoStandardDTO> list = new ArrayList<>();
        for (ConsoStandard consoStandard : consoStandards) {
            list.add(consoStandardToConsoStandardDTO(consoStandard));
        }
        return list;
    }

    public static Collection<ConsoStandardDTO> CollectionConsoStandardToConsoStandardDTOs(Collection<ConsoStandard> consoStandards) {
        Collection<ConsoStandardDTO> collection = new ArrayList<>();
        for (ConsoStandard consoStandard : consoStandards) {
            collection.add(consoStandardToConsoStandardDTO(consoStandard));
        }
        return collection;
    }

//    public static Collection<ConsoStandardEdition> flattenConsoStandardForEdition(List<ConsoStandard> consoStandards) {
//    if (consoStandards == null) {
//        return new ArrayList<>();
//    }
//
//    List<ConsoStandardEdition> flattenedList = new ArrayList<>();
//
//    for (ConsoStandard cs : consoStandards) {
//        // Since getDetailsBonReceptions() is lazy-loaded, ensure you are in a transactional context when calling this.
//        // Your service method already is, so this is safe.
//        if (cs.getDetailsConsoStandards()!= null) {
//            for (DetailsConsoStandard detail : cs.getDetailsConsoStandards()) {
//                ConsoStandardEdition  dto = new ConsoStandardEdition();
// 
//                dto.setQteBesoinTotal(detail .getConsTotal());
//                if (detail.getArticle() != null) {
//                    dto.setCodeSaisieArticle(detail.getArticle().getCodeSaisie());
//                    dto.setDesignationArArticle(detail.getArticle().getDesignationAr());
//                    // Assuming Article has a relationship to a Unite entity
//                    if (detail.getArticle().getUnite() != null) {
//                        dto.setDesignationArUnite(detail.getArticle().getUniteSecondaire().getDesignationAr());
//                    }
//                }
//                flattenedList.add(dto);
//            }
//        }
//    }
//    return flattenedList;
//}
    public static Collection<ConsoStandardEdition> flattenConsoStandardForEdition(List<ConsoStandard> consoStandards) {
        if (consoStandards == null) {
            return new ArrayList<>();
        }

        List<ConsoStandardEdition> flattenedList = new ArrayList<>();
        for (ConsoStandard cs : consoStandards) {
            Map<Integer, ConsoStandardEdition> articleAggregationMap = new HashMap<>();

            if (cs.getDetailsConsoStandardPerDays() != null) {
                for (DetailsConsoStandardPerDay perDayDetail : cs.getDetailsConsoStandardPerDays()) {
                    if (perDayDetail.getArticle() == null || perDayDetail.getConsTotal() == null) {
                        continue; // Skip records with incomplete data
                    }
                    Integer articleCode = perDayDetail.getArticle().getCode();
                    if (articleAggregationMap.containsKey(articleCode)) {
                        ConsoStandardEdition existingDto = articleAggregationMap.get(articleCode);
                        BigDecimal currentTotal = existingDto.getQteBesoinTotal() != null ? existingDto.getQteBesoinTotal() : BigDecimal.ZERO;
                        BigDecimal amountToAdd = perDayDetail.getConsTotal();
                        existingDto.setQteBesoinTotal(currentTotal.add(amountToAdd));
                    } else {
                        ConsoStandardEdition newDto = new ConsoStandardEdition();
                        newDto.setQteBesoinTotal(perDayDetail.getConsTotal());
                        newDto.setCodeSaisieArticle(perDayDetail.getArticle().getCodeSaisie());
                        newDto.setDesignationArArticle(perDayDetail.getArticle().getDesignationAr());
                        newDto.setDesignationArUnite(perDayDetail.getUniteSecondaire().getDesignationAr());

//                        DetailsConsoStandard parentDetail = perDayDetail.getDetailsConsoStandard();
//                        if (parentDetail != null && parentDetail.getUniteSecondaire() != null) {
//                            newDto.setDesignationArUnite(parentDetail.getUniteSecondaire().getDesignationAr());
//                        } else {
//                            newDto.setDesignationArUnite("N/A"); // Fallback if unit is not found
//                        } 
                        articleAggregationMap.put(articleCode, newDto);
                    }
                }
            }
            flattenedList.addAll(articleAggregationMap.values());
        }

        return flattenedList;
    }

}
