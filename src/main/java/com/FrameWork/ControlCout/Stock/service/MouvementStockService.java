/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Stock.domaine.MouvementStock;
import com.FrameWork.ControlCout.Stock.repository.MouvementStockRepo;
import com.FrameWork.ControlCout.constants.AchatConstants;
import com.FrameWork.ControlCout.web.Util.Helper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class MouvementStockService {
    
    private final Logger log = LoggerFactory.getLogger(MouvementStockService.class);

    
//    public static final String TYPE_MVT_ENTREE_RECEPTION = "ENTREE_RECEPTION";

    
    private final MouvementStockRepo mouvementStockRepo;
    // You might later inject an EtatStockService here to update stock levels

    public MouvementStockService(MouvementStockRepo mouvementStockRepo) {
        this.mouvementStockRepo = mouvementStockRepo;
    }
    
    public MouvementStock createMouvement(MouvementStock mouvementStock) {
        // Set audit fields before saving
        mouvementStock.setDateCreate(new Date());
        mouvementStock.setUserCreate(Helper.getUserAuthenticated());
        
        log.info("Creating new stock movement: Article {}, Qty {}, From {} To {}", 
                mouvementStock.getArticle().getCodeSaisie(),
                mouvementStock.getQuantite(),
                mouvementStock.getDepot().getCodeSaisie(),
                mouvementStock.getDepotDestination().getCodeSaisie()
        );
        
        MouvementStock savedMouvement = mouvementStockRepo.save(mouvementStock);
         
        return savedMouvement;
    }
    
     public void createEntreesFromBonReception(List<DetailsBonReception> detailsList) {
        if (detailsList == null || detailsList.isEmpty()) {
            return;
        }

        List<MouvementStock> mouvements = new ArrayList<>();
        String user = Helper.getUserAuthenticated();

        for (DetailsBonReception detail : detailsList) {
            MouvementStock mvt = new MouvementStock();
            mvt.setDateCreate(new Date());
            mvt.setQuantite(detail.getQteReceptionner());
            mvt.setTypeMouvement(AchatConstants.TYPE_MVT_ENTREE_RECEPTION.toString()); 
            mvt.setCodeOrigine(detail.getBonReception().getCode());
            mvt.setUserCreate(user);
            mvt.setArticle(detail.getArticle());
            mvt.setDepot(detail.getBonReception().getDepot());

            mouvements.add(mvt);
        }

        mouvementStockRepo.saveAll(mouvements);
    }

    /**
     * Deletes all stock movements associated with a specific BonReception.
     */
    public void deleteMouvementsForBonReception(Integer codeBonReception) {
        List<MouvementStock> mouvementsToDelete = mouvementStockRepo.findByCodeOrigine( codeBonReception);
        if (!mouvementsToDelete.isEmpty()) {
            mouvementStockRepo.deleteAllInBatch(mouvementsToDelete);
        }
    }
    
}
