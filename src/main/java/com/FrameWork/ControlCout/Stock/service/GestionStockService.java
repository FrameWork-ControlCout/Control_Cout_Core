/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.service;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.repository.DepotRepo;
import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import com.FrameWork.ControlCout.Stock.domaine.EtatStock;
import com.FrameWork.ControlCout.Stock.domaine.EtatStockPK;
import com.FrameWork.ControlCout.Stock.domaine.MouvementStock;
import com.FrameWork.ControlCout.Stock.repository.EtatStockRepo;
import com.FrameWork.ControlCout.Stock.repository.MouvementStockRepo;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementFournisseur;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementFournisseurRepo;
import com.FrameWork.ControlCout.constants.AchatConstants;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class GestionStockService {

    private final MouvementStockRepo mouvementStockRepo;
    private final MouvementFournisseurRepo mouvementFournisseurRepo;
    private final DepotRepo depotRepo; // Assuming you have a repository for Depot

    private final EtatStockRepo etatStockRepo;

    public GestionStockService(MouvementStockRepo mouvementStockRepo, MouvementFournisseurRepo mouvementFournisseurRepo, DepotRepo depotRepo, EtatStockRepo etatStockRepo) {
        this.mouvementStockRepo = mouvementStockRepo;
        this.mouvementFournisseurRepo = mouvementFournisseurRepo;
        this.depotRepo = depotRepo;
        this.etatStockRepo = etatStockRepo;
    }

  
   

    public void gererEntreeStock(MouvementStock mouvement) {
        Preconditions.checkArgument(mouvement.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'entrée doit être positive.");

        mouvement.setTypeMouvement("ENTREE_RECEPTION");
        mouvement.setDateCreate(new Date());
        mouvement.setUserCreate(Helper.getUserAuthenticated());
        mouvementStockRepo.save(mouvement);

        EtatStockPK pk = new EtatStockPK(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        EtatStock etatStock = etatStockRepo.findById(pk)
                .orElse(new EtatStock(pk, BigDecimal.ZERO));

        BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().add(mouvement.getQuantite());
        etatStock.setQuantiteDisponible(nouvelleQuantite);
        etatStockRepo.save(etatStock);
    }

    public void gererSoldeFrsEntreeStock(MouvementFournisseur mouvementFournisseur) {
        Preconditions.checkArgument(mouvementFournisseur.getCodeFournisseur() != null, "error.FournisseurObligatoire");

        mouvementFournisseur.setTypeMouvement("BON_RECEPTION");
        mouvementFournisseur.setDateCreate(new Date());
        mouvementFournisseur.setUserCreate(Helper.getUserAuthenticated());
        mouvementFournisseurRepo.save(mouvementFournisseur);

    }

    public void gererSortieStock(MouvementStock mouvement) {
        Preconditions.checkArgument(mouvement.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité de sortie doit être positive.");

        // 1. Vérification de la disponibilité
        BigDecimal qteDisponible = getQuantiteDisponible(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        if (qteDisponible.compareTo(mouvement.getQuantite()) < 0) {
            // Vous pouvez créer une exception personnalisée ici
            throw new IllegalStateException("Stock insuffisant pour l'article " + mouvement.getArticle().getCodeSaisie() + ". Demandé: " + mouvement.getQuantite() + ", Disponible: " + qteDisponible);
        }

        mouvement.setTypeMouvement("SORTIE_DISPENSATION");
        mouvement.setDateCreate(new Date());
        mouvement.setUserCreate(Helper.getUserAuthenticated());
        mouvementStockRepo.save(mouvement);

        EtatStockPK pk = new EtatStockPK(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        EtatStock etatStock = etatStockRepo.findById(pk)
                .orElseThrow(() -> new IllegalStateException("Incohérence de données: EtatStock non trouvé pour une sortie."));

        BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().subtract(mouvement.getQuantite());
        etatStock.setQuantiteDisponible(nouvelleQuantite);
        etatStockRepo.save(etatStock);
    }

    public void annulerEntreeStockBonReception(MouvementStock mouvement) {
        Preconditions.checkArgument(mouvement.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'annulation doit être positive.");

        mouvementStockRepo.delete(mouvement);

        EtatStockPK pk = new EtatStockPK(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        EtatStock etatStock = etatStockRepo.findById(pk)
                .orElseThrow(() -> new IllegalStateException("Incohérence de données: EtatStock non trouvé pour une annulation d'entrée."));

        BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().subtract(mouvement.getQuantite());
        etatStock.setQuantiteDisponible(nouvelleQuantite);
        etatStockRepo.save(etatStock);
    }

    @Transactional(readOnly = true)
    public BigDecimal getQuantiteDisponible(Integer codeArticle, Integer codeDepot) {
        EtatStockPK pk = new EtatStockPK(codeArticle, codeDepot);
        return etatStockRepo.findById(pk)
                .map(EtatStock::getQuantiteDisponible)
                .orElse(BigDecimal.ZERO);
    }

    public void createEntreesFromBonReception(List<DetailsBonReception> detailsList) {
        for (DetailsBonReception detail : detailsList) {
            MouvementStock mvt = new MouvementStock();
            mvt.setArticle(detail.getArticle());
            mvt.setCodeArticle(detail.getCodeArticle());
            mvt.setDepot(detail.getBonReception().getDepot());
            mvt.setQuantite(detail.getQteReceptionner());
            mvt.setCodeOrigine(detail.getBonReception().getCode());
            // mvt.setPrixUnitaire(detail.getPrixUni()); // Important pour la valorisation
            gererEntreeStock(mvt);
        }
    }

    public void createSortieFromDepenseSource(List<DetailsDepense> detailsList) {
        for (DetailsDepense detail : detailsList) {
            MouvementStock mvt = new MouvementStock();

            mvt.setArticle(detail.getArticle());
            mvt.setCodeArticle(detail.getCodeArticle());
            mvt.setDepot(detail.getDepotSource());
            mvt.setDepotDestination(detail.getDepotDestination());

            mvt.setQuantite(detail.getQteDispenser());
            mvt.setCodeOrigine(detail.getDepense().getCode());

            mvt.setTypeMouvement("DEPENSE");
            mvt.setDateCreate(new Date());
            mvt.setUserCreate(Helper.getUserAuthenticated());
            mouvementStockRepo.save(mvt);
            Preconditions.checkArgument(mvt.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'entrée doit être positive.");

            EtatStockPK pk = new EtatStockPK(mvt.getCodeArticle(), mvt.getDepot().getCode());
            EtatStock etatStock = etatStockRepo.findById(pk)
                    .orElse(new EtatStock(pk, BigDecimal.ZERO));

            BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().subtract(mvt.getQuantite());
            etatStock.setQuantiteDisponible(nouvelleQuantite);
            etatStockRepo.save(etatStock);

        }
    }

    public void createEntreeFromDepenseDestination(List<DetailsDepense> detailsList) {
        for (DetailsDepense detail : detailsList) {
            MouvementStock mvt = new MouvementStock();
            mvt.setArticle(detail.getArticle());
            mvt.setCodeArticle(detail.getCodeArticle());
            mvt.setDepot(detail.getDepotDestination());

            mvt.setQuantite(detail.getQteDispenser());
            mvt.setCodeOrigine(detail.getDepense().getCode());

            mvt.setTypeMouvement("DEPENSE");
            mvt.setDateCreate(new Date());
            mvt.setUserCreate(Helper.getUserAuthenticated());
            mouvementStockRepo.save(mvt);
            Preconditions.checkArgument(mvt.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'entrée doit être positive.");

            EtatStockPK pk = new EtatStockPK(mvt.getCodeArticle(), detail.getDepotDestination().getCode());
            EtatStock etatStock = etatStockRepo.findById(pk)
                    .orElse(new EtatStock(pk, BigDecimal.ZERO));

            BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().add(mvt.getQuantite());
            etatStock.setQuantiteDisponible(nouvelleQuantite);
            etatStockRepo.save(etatStock);

//            gererEntreeStockDepenseDestination(mvt);
        }
    }

    public void deleteMouvementsForBonReception(Integer codeBonReception) {
        List<MouvementStock> mouvementsToDelete = mouvementStockRepo.findByCodeOrigineAndTypeMouvement(codeBonReception, "ENTREE_RECEPTION");
        for (MouvementStock mvt : mouvementsToDelete) {
            annulerEntreeStockBonReception(mvt);
        }
    }

    public void createMouvementFrsFromFactureBonReception(FactureBonReception factureBonReception) {
        MouvementFournisseur mvtFrs = new MouvementFournisseur();
        mvtFrs.setCodeFournisseur(factureBonReception.getCodeFournisseur());
        if (mvtFrs.getCodeFournisseur() != null) {
            mvtFrs.setFournisseur(FournisseurFactory.createFournisseurByCode(factureBonReception.getCodeFournisseur()));
        }
        mvtFrs.setCodeEtatPaiement(Integer.parseInt(AchatConstants.CODE_ETAT_PAIEMENT_NON_PAYEE.toString()));
        if (mvtFrs.getCodeEtatPaiement() != null) {
            mvtFrs.setEtatPaiement(EtatPaiementFactory.createEtatPaiementFactureByCode(Integer.parseInt(AchatConstants.CODE_ETAT_PAIEMENT_NON_PAYEE.toString())));
        }
        mvtFrs.setPrixFacture(factureBonReception.getPrixTotal());
        mvtFrs.setCodeFactureBonReception(factureBonReception.getCode());
        mvtFrs.setDeductionFacture(factureBonReception.getMontantDiffPrix());
        gererSoldeFrsEntreeStock(mvtFrs);

    }

    public void annulerSortieDepenseSource(MouvementStock mouvement) {
        Preconditions.checkArgument(mouvement.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'annulation doit être positive.");

        EtatStockPK pk = new EtatStockPK(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        System.out.println(" details pk " + pk.getCodeArticle() + " codeDepot : " + pk.getCodeDepot());
        EtatStock etatStock = etatStockRepo.findById(pk)
                .orElseThrow(() -> new IllegalStateException("Incohérence de données: EtatStock non trouvé pour une annulation d'entrée."));

        BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().add(mouvement.getQuantite());
        etatStock.setQuantiteDisponible(nouvelleQuantite);
        etatStockRepo.save(etatStock);
        mouvementStockRepo.delete(mouvement);
    }

    public void deleteMouvementsForDepenseSource(Integer codeDepense,Integer codeDepotSource) {
        List<MouvementStock> mouvementsToDelete = mouvementStockRepo.findByCodeOrigineAndTypeMouvementAndCodeDepotSource(codeDepense, "DEPENSE",codeDepotSource);
        for (MouvementStock mvt : mouvementsToDelete) {
            annulerSortieDepenseSource(mvt);
        }
    }

    public void annulerEntreeDepenseDestination(MouvementStock mouvement) {
        Preconditions.checkArgument(mouvement.getQuantite().compareTo(BigDecimal.ZERO) > 0, "La quantité d'annulation doit être positive.");

   

        EtatStockPK pk = new EtatStockPK(mouvement.getCodeArticle(), mouvement.getDepot().getCode());
        EtatStock etatStock = etatStockRepo.findById(pk)
                .orElseThrow(() -> new IllegalStateException("Incohérence de données: EtatStock non trouvé pour une annulation d'entrée."));

        BigDecimal nouvelleQuantite = etatStock.getQuantiteDisponible().subtract(mouvement.getQuantite());
        etatStock.setQuantiteDisponible(nouvelleQuantite);
        etatStockRepo.save(etatStock);
             mouvementStockRepo.delete(mouvement);
    }

    public void deleteMouvementsForDepenseDestination(Integer codeDepense,Integer codeDepotDestination) {
        List<MouvementStock> mouvementsToDelete = mouvementStockRepo.findByCodeOrigineAndTypeMouvementAndCodeDepotSource(codeDepense, "DEPENSE",codeDepotDestination);
        for (MouvementStock mvt : mouvementsToDelete) {
            annulerEntreeDepenseDestination(mvt);
        }
    }

// 
//    @Transactional(readOnly = true)
//     public List<EtatStock> getEtatStockForDepot(Integer depotCode) {
//        // First, create a reference to the Depot entity. 
//        // Using getReferenceById is efficient as it doesn't hit the DB if the object is only used for the query.
//         Depot depot = depotRepo.findByCode(depotCode);
//         System.out.println("depot code saisie" + depot.getCodeSaisie());
//        // Now, call the updated repository method with the Depot object
//        return etatStockRepo.findAllByDepot(depot);
//    }

}
