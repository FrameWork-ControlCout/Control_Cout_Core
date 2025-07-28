/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.factory;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.Edition.BonReceptionEdition;
import com.FrameWork.ControlCout.Stock.Edition.SoldeDepotEdition;
import com.FrameWork.ControlCout.Stock.domaine.EtatStock;
import com.FrameWork.ControlCout.Stock.domaine.EtatStockPK;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class EtatStockFactory {
//     public static Collection<SoldeDepotEdition> flattenSoldeDepotForEdition(List<EtatStock> etatStocks) {
//        if (etatStocks == null) {
//            return new ArrayList<>();
//        }
//
//        List<SoldeDepotEdition> flattenedList = new ArrayList<>();
//
//        // Loop through each EtatStock record. There is no inner loop needed.
//        for (EtatStock etatStock : etatStocks) {
//
//            // Create a new DTO for each record
//            SoldeDepotEdition dto = new SoldeDepotEdition();
//
//            // Populate from the main EtatStock entity
//            dto.setQteDisponible(etatStock.getQuantiteDisponible());
//
//            // Get the composite key, which contains the relationships
//            EtatStockPK pk = etatStock.getId();
//
//            // It's good practice to check if the embedded ID is not null
//            if (pk != null) {
//                
//                // --- Populate from the Article relationship within the PK ---
//                if (pk.getArticle() != null) {
//                    dto.setCodeSaisieArticle(pk.getArticle().getCodeSaisie());
//                    dto.setDesignationArArticle(pk.getArticle().getDesignationAr());
//
//                    // Assuming Article has a relationship to a Unite entity
//                    if (pk.getArticle().getUnite() != null) {
//                        dto.setDesignationArUnite(pk.getArticle().getUnite().getDesignationAr());
//                    }
//                }
//
//                // --- Populate from the Depot relationship within the PK ---
//                if (pk.getDepot() != null) {
//                    dto.setCodeSaisieDepot(pk.getDepot().getCodeSaisie());
//                    dto.setDesignationArDepot(pk.getDepot().getDesignationAr());
//                }
//            }
//
//            flattenedList.add(dto);
//        }
//
//        return flattenedList;
//    }
}
