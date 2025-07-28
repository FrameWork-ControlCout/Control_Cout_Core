/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.factory;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.dto.BonReceptionDTO;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleEdition;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class BonReceptionFactory {

    public static BonReception createBonReceptionByCode(int code) {
        BonReception domaine = new BonReception();
        domaine.setCode(code);
        return domaine;
    }

    public static BonReception bonReceptionDTOToBonReception(BonReceptionDTO dto, BonReception domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setPrixTotal(dto.getPrixTotal());

            domaine.setNumFactFrs(dto.getNumFactFrs());
            domaine.setMontantFactFrs(dto.getMontantFactFrs());
            domaine.setDateFactFrs(dto.getDateFactFrs());
            domaine.setHaveFBR(dto.getHaveFBR());

            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setCodeOrderAchat(dto.getCodeOrderAchat());
            domaine.setCodeFournisseur(dto.getCodeFournisseur());
            if (domaine.getCodeFournisseur() != null) {
                domaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
            }

            domaine.setCodeDepot(dto.getCodeDepot());
            if (domaine.getCodeDepot() != null) {
                domaine.setDepot(DepotFactory.createDepotByCode(dto.getCodeDepot()));
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static BonReceptionDTO bonReceptionToBonReceptionDTO(BonReception domaine) {

        if (domaine != null) {
            BonReceptionDTO dto = new BonReceptionDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setPrixTotal(domaine.getPrixTotal());
            dto.setHaveFBR(domaine.getHaveFBR());

            dto.setMontantFactFrs(domaine.getMontantFactFrs());
            dto.setNumFactFrs(domaine.getNumFactFrs());
            dto.setDateFactFrs(domaine.getDateFactFrs());

            dto.setCodeOrderAchat(domaine.getCodeOrderAchat());

            dto.setFournisseurDTO(FournisseurFactory.fournisseurToFournisseurDTO(domaine.getFournisseur()));
            dto.setCodeFournisseur(domaine.getCodeFournisseur());

            dto.setDepotDTO(DepotFactory.depotToDepotDTO(domaine.getDepot()));
            dto.setCodeDepot(domaine.getCodeDepot());
            return dto;
        } else {
            return null;
        }
    }

    public static List<BonReceptionDTO> listBonReceptionToBonReceptionDTOs(List<BonReception> bonReceptions) {
        List<BonReceptionDTO> list = new ArrayList<>();
        for (BonReception bonReception : bonReceptions) {
            list.add(bonReceptionToBonReceptionDTO(bonReception));
        }
        return list;
    }

    public static Collection<BonReceptionDTO> CollectionBonReceptionToBonReceptionDTOs(Collection<BonReception> bonReceptions) {
        Collection<BonReceptionDTO> collection = new ArrayList<>();
        for (BonReception bonReception : bonReceptions) {
            collection.add(bonReceptionToBonReceptionDTO(bonReception));
        }
        return collection;
    }

//    //Edition
//    public static BonReceptionEdition bonReceptionToBonReceptionEdition(BonReception domaine) {
//        if (domaine == null) {
//            return null;
//        }
//
//        BonReceptionEdition domaineEdition = new BonReceptionEdition();
//
//        domaineEdition.setCodeSaisie(domaine.getCodeSaisie());
//        domaineEdition.setDesignationFrs(domaine.getFournisseur().getDesignationAr());
//        domaineEdition.setUserCreate(domaine.getUserCreate());
//
//        return domaineEdition;
//    }
//
//    public static Collection<BonReceptionEdition> listBonReceptionToBonReceptionEdition(List<BonReception> domines) {
//        if (domines == null) {
//            return null;
//        }
//        List<BonReceptionEdition> list = new ArrayList<>();
//        for (BonReception domaine : domines) {
//            list.add(bonReceptionToBonReceptionEdition(domaine));
//        }
//        return list;
//    }
    public static Collection<BonReceptionEdition> flattenBonReceptionForEdition(List<BonReception> bonReceptions) {
        if (bonReceptions == null) {
            return new ArrayList<>();
        }

        List<BonReceptionEdition> flattenedList = new ArrayList<>();

        for (BonReception br : bonReceptions) {
            // Since getDetailsBonReceptions() is lazy-loaded, ensure you are in a transactional context when calling this.
            // Your service method already is, so this is safe.
            if (br.getDetailsBonReceptions() != null) {
                for (DetailsBonReception detail : br.getDetailsBonReceptions()) {
                    BonReceptionEdition dto = new BonReceptionEdition();

                    // --- Populate from the parent BonReception ---
                    dto.setCodeSaisie(br.getCodeSaisie());
                    if (br.getFournisseur() != null) {
                        // Assuming you want the Arabic designation as per your SQL
                        dto.setDesignationFrs(br.getFournisseur().getDesignationAr());
                    }
                    dto.setUserCreate(br.getUserCreate());

                    // --- Populate from the child DetailsBonReception ---
                    dto.setQteReceptionner(detail.getQteReceptionner());
                    if (detail.getArticle() != null) {
                        dto.setCodeSaisieArticle(detail.getArticle().getCodeSaisie());
                        dto.setDesignationArticle(detail.getArticle().getDesignationAr());
                        // Assuming Article has a relationship to a Unite entity
                        if (detail.getArticle().getUnite() != null) {
                            dto.setUnite(detail.getArticle().getUnite().getDesignationAr());
                        }
                    }
                    flattenedList.add(dto);
                }
            }
        }
        return flattenedList;
    }
}
