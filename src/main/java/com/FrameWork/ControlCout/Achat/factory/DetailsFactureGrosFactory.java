/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureGros;
import com.FrameWork.ControlCout.Achat.domaine.FactureGros;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureGrosDTO;
import com.FrameWork.ControlCout.Achat.dto.FactureGrosDTO;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory; 
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DetailsFactureGrosFactory {

    public static DetailsFactureGros createDetailsFactureGrosByCode(int code) {
        DetailsFactureGros domaine = new DetailsFactureGros();
        domaine.setCode(code);
        return domaine;
    }

    public static DetailsFactureGros detailsfactureGrosDTOToDetailsFactureGros(DetailsFactureGrosDTO dto, DetailsFactureGros domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
 
            domaine.setCodeArticle(dto.getCodeArticle());
            if (domaine.getCodeArticle() != null) {
                domaine.setArticle(ArticleFactory.createArticleByCode(dto.getCodeArticle()));
            }
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeUnite(dto.getCodeUnite());
            if (domaine.getCodeUnite() != null) {
                domaine.setUnite(UniteFactory.createUniteByCode(dto.getCodeUnite()));
            }
            domaine.setPrixUni(dto.getPrixUni());
            domaine.setCaracterstique(dto.getCaracterstique());

            return domaine;
        } else {
            return null;
        }
    }

    public static DetailsFactureGrosDTO detailsfactureGrosToDetailsFactureGrosDTO(DetailsFactureGros domaine) {

        if (domaine != null) {
            DetailsFactureGrosDTO dto = new DetailsFactureGrosDTO();
            dto.setCode(domaine.getCode()); 
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setCaracterstique(domaine.getCaracterstique());
            dto.setCodeArticle(domaine.getCodeArticle());
            dto.setArticleDTO(ArticleFactory.articleToArticleDTO(domaine.getArticle()));
            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());
            dto.setUniteDTO(UniteFactory.uniteToUniteDTO(domaine.getUnite()));
            dto.setCodeUnite(domaine.getCodeUnite());
            dto.setPrixUni(domaine.getPrixUni());

            return dto;
        } else {
            return null;
        }
    }

    public static List<DetailsFactureGrosDTO> listDetailsFactureGrosToDetailsFactureGrosDTOs(List<DetailsFactureGros> detailsFactureGroses) {
        List<DetailsFactureGrosDTO> list = new ArrayList<>();
        for (DetailsFactureGros detailsFactureGros : detailsFactureGroses) {
            list.add(detailsfactureGrosToDetailsFactureGrosDTO(detailsFactureGros));
        }
        return list;
    }

    public static Collection<DetailsFactureGrosDTO> CollectionDetailsFactureGrosToDetailsFactureGrosDTOs(Collection<DetailsFactureGros> detailsFactureGroses) {
        Collection<DetailsFactureGrosDTO> collection = new ArrayList<>();
        for (DetailsFactureGros detailsFactureGros : detailsFactureGroses) {
            collection.add(detailsfactureGrosToDetailsFactureGrosDTO(detailsFactureGros));
        }
        return collection;
    }
}
