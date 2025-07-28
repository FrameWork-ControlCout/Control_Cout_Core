/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.factory;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Stock.Edition.DepenseEdition;
import com.FrameWork.ControlCout.Stock.domaine.Depense;
import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import com.FrameWork.ControlCout.Stock.dto.DepenseDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class DepenseFactory {

    public static Depense createDepenseByCode(int code) {
        Depense domaine = new Depense();
        domaine.setCode(code);
        return domaine;
    }

    public static Depense depenseDTOToDepense(DepenseDTO dto, Depense domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());

            domaine.setCodeDepotSource(dto.getCodeDepotSource());
            if (domaine.getCodeDepotSource() != null) {
                domaine.setDepotSource(DepotFactory.createDepotByCode(dto.getCodeDepotSource()));
            }

            domaine.setCodeDepotDestination(dto.getCodeDepotDestination());
            if (domaine.getCodeDepotDestination() != null) {
                domaine.setDepotDestination(DepotFactory.createDepotByCode(dto.getCodeDepotDestination()));
            }

            
            domaine.setCodeConsoStandard(dto.getCodeConsoStandard());
            if (domaine.getCodeConsoStandard() != null) {
                domaine.setConsoStandard(ConsoStandardFactory.createConsoStandardByCode(dto.getCodeConsoStandard()));
            }
            
            return domaine;
        } else {
            return null;
        }
    }

    public static DepenseDTO depenseToDepenseDTO(Depense domaine) {

        if (domaine != null) {
            DepenseDTO dto = new DepenseDTO();
            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
        
          
            dto.setDepotSourceDTO(DepotFactory.depotToDepotDTO(domaine.getDepotSource()));
            dto.setCodeDepotSource(domaine.getCodeDepotSource());
            
            
            dto.setDepotDestinationDTO(DepotFactory.depotToDepotDTO(domaine.getDepotDestination()));
            dto.setCodeDepotDestination(domaine.getCodeDepotDestination());
            
            
            
            dto.setConsoStandardDTO(ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine.getConsoStandard()));
            dto.setCodeConsoStandard(domaine.getCodeConsoStandard());
            return dto;
        } else {
            return null;
        }
    }

    public static List<DepenseDTO> listDepenseToDepenseDTOs(List<Depense> depenses) {
        List<DepenseDTO> list = new ArrayList<>();
        for (Depense depense : depenses) {
            list.add(depenseToDepenseDTO(depense));
        }
        return list;
    }

    public static Collection<DepenseDTO> CollectionDepenseToDepenseDTOs(Collection<Depense> depenses) {
        Collection<DepenseDTO> collection = new ArrayList<>();
        for (Depense depense : depenses) {
            collection.add(depenseToDepenseDTO(depense));
        }
        return collection;
    }
    
    
     public static Collection<DepenseEdition> flattenDepenseForEdition(List<Depense> depenses) {
    if (depenses == null) {
        return new ArrayList<>();
    }

    List<DepenseEdition> flattenedList = new ArrayList<>();

    for (Depense dp : depenses) {
        // Since getDetailsBonReceptions() is lazy-loaded, ensure you are in a transactional context when calling this.
        // Your service method already is, so this is safe.
        if (dp.getDetailsDepenses()!= null) {
            for (DetailsDepense detail : dp.getDetailsDepenses()) {
                DepenseEdition dto = new DepenseEdition();

                // --- Populate from the parent BonReception ---
                dto.setCodeSaisie(dp.getCodeSaisie());
                if (dp.getDepotSource()!= null) {
                    // Assuming you want the Arabic designation as per your SQL
                    dto.setDepotSource(dp.getDepotSource().getDesignationAr());
                }
                if (dp.getDepotDestination()!= null) {
                    // Assuming you want the Arabic designation as per your SQL
                    dto.setDepotDestination(dp.getDepotDestination().getDesignationAr());
                }
                dto.setUserCreate(dp.getUserCreate());

                // --- Populate from the child DetailsBonReception ---
                dto.setQteDispenser(detail.getQteDispenser());
                if (detail.getArticle() != null) {
                    dto.setCodeSaisieArticle(detail.getArticle().getCodeSaisie());
                    dto.setDesignationArArticle(detail.getArticle().getDesignationAr());
                    // Assuming Article has a relationship to a Unite entity
                    if (detail.getArticle().getUniteSecondaire()!= null) {
                        dto.setUnite(detail.getArticle().getUniteSecondaire().getDesignationAr());
                    }
                }
                flattenedList.add(dto);
            }
        }
    }
    return flattenedList;
}
}
