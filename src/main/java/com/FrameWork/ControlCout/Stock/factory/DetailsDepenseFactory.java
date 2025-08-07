/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.factory;

import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import com.FrameWork.ControlCout.Stock.dto.DetailsDepenseDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsDepenseFactory {

    public static DetailsDepense createDetailsDepenseByCode(int code) {
        DetailsDepense domaine = new DetailsDepense();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsDepense detailsDepenseDTOToDetailsDepense(DetailsDepenseDTO dto, DetailsDepense domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setQteDispenser(dto.getQteDispenser());
            domaine.setQteDemander(dto.getQteDemander());
                        domaine.setConsStandard(dto.isConsStandard());


            domaine.setCodeDepense(dto.getCodeDepense());
            if (domaine.getCodeDepense() != null) {
                domaine.setDepense(DepenseFactory.createDepenseByCode(dto.getCodeDepense()));
            }

            domaine.setCodeDepotSource(dto.getCodeDepotSource());
            if (domaine.getCodeDepotSource() != null) {
                domaine.setDepotSource(DepotFactory.createDepotByCode(dto.getCodeDepotSource()));
            }

            domaine.setCodeDepotDestination(dto.getCodeDepotDestination());
            if (domaine.getCodeDepotDestination() != null) {
                domaine.setDepotDestination(DepotFactory.createDepotByCode(dto.getCodeDepotDestination()));
            }

            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }

            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static DetailsDepenseDTO detailsDepenseToDetailsDepenseDTO(DetailsDepense domaine) {

        if (domaine != null) {
            DetailsDepenseDTO dto = new DetailsDepenseDTO();
            dto.setCode(domaine.getCode());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setQteDispenser(domaine.getQteDispenser());
            dto.setQteDemander(domaine.getQteDemander());
            dto.setDepotSourceDTO(DepotFactory.depotToDepotDTO(domaine.getDepotSource()));
            dto.setCodeDepotSource(domaine.getCodeDepotSource());

            dto.setDepenseDTO(DepenseFactory.depenseToDepenseDTO(domaine.getDepense()));
            dto.setCodeDepense(domaine.getCodeDepense());

            dto.setDepotDestinationDTO(DepotFactory.depotToDepotDTO(domaine.getDepotDestination()));
            dto.setCodeDepotDestination(domaine.getCodeDepotDestination());

            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());

            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));
            dto.setCodeArticle(domaine.getCodeArticle());
            
                        dto.setConsStandard(domaine.isConsStandard());


            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsDepenseDTO> listDetailsDepenseToDetailsDepenseDTOs(List<DetailsDepense> detailsDepenses) {
        List<DetailsDepenseDTO> list = new ArrayList<>();
        for (DetailsDepense detailsDepense : detailsDepenses) {
            list.add(detailsDepenseToDetailsDepenseDTO(detailsDepense));
        }
        return list;
    }

    public static Collection<DetailsDepenseDTO> CollectionDetailsDepenseToDetailsDepenseDTOs(Collection<DetailsDepense> detailsDepenses) {
        Collection<DetailsDepenseDTO> collection = new ArrayList<>();
        for (DetailsDepense detailsDepense : detailsDepenses) {
            collection.add(detailsDepenseToDetailsDepenseDTO(detailsDepense));
        }
        return collection;
    }
}
