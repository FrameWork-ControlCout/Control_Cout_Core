/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse;
import com.FrameWork.ControlCout.Tresorerie.domaine.SoldeCaisse;
import com.FrameWork.ControlCout.Tresorerie.domaine.TransfertCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.SoldeCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.dto.TransfertCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.CaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.factory.TransfertCaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementCaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.SoldeCaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.TransfertCaisseRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TransfertCaisseService {

    private final TransfertCaisseRepo transfertCaisseRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final MouvementCaisseService mouvementCaisseSerivce;

    private final SoldeCaisseRepo soldeCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;

    public TransfertCaisseService(TransfertCaisseRepo transfertCaisseRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, MouvementCaisseService mouvementCaisseSerivce, SoldeCaisseRepo soldeCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.transfertCaisseRepo = transfertCaisseRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.mouvementCaisseSerivce = mouvementCaisseSerivce;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }

    @Transactional(readOnly = true)
    public List<TransfertCaisseDTO> findAllTransfertCaisse() {
        return TransfertCaisseFactory.listTransfertCaisseToTransfertCaisseDTOs(transfertCaisseRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public TransfertCaisseDTO findOne(Integer code) {
        TransfertCaisse domaine = transfertCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.TransfertCaisseNotFound");
        return TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<TransfertCaisseDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return TransfertCaisseFactory.listTransfertCaisseToTransfertCaisseDTOs(transfertCaisseRepo.findTransfertCaisseByCodeEtatApprouver(CodeEtatApprouver));
    }

    public TransfertCaisseDTO save(TransfertCaisseDTO dto) {

        //control solde caisse 
        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();
        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);
        if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("error.SoldeCaisseNegative");

        }
        TransfertCaisse domaine = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(new TransfertCaisse(), dto);

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieTR");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
       domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = transfertCaisseRepo.save(domaine);

        return TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
    }
//
//    public TransfertCaisse update(TransfertCaisseDTO dTO) { 
//        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dTO.getCode());
//        Preconditions.checkArgument(true, "error.TransfertCaisseNotFound");
//    
//        domaine.getDetailsTransfertCaisses().clear();
//        transfertCaisseRepo.flush();
//        TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(dTO, domaine);
//        return transfertCaisseRepo.save(domaine);
//    }
//    

//    public TransfertCaisseDTO update(TransfertCaisseDTO dto) {
//
//        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(domaine != null, "error.TransfertCaisseNotFound");
////        domaine.getDetailsTransfertCaisses().clear();
//
//        detailsTransfertCaisseRepo.deleteByCodeTransfertCaisse(domaine.getCode());
//        transfertCaisseRepo.deleteById(domaine.getCode());
//        domaine = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(domaine, dto);
//        domaine = transfertCaisseRepo.save(domaine);
//        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
//        return resultDTO;
//    }
    public TransfertCaisseDTO updateNewWithFlush(TransfertCaisseDTO dto) {

        //control solde caisse 
        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();
        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);
        if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("error.SoldeCaisseNegative");

        }

        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TransfertCaisseNotFound");
        domaine = TransfertCaisseFactory.transfertCaisseDTOToTransfertCaisse(domaine, dto);
        domaine = transfertCaisseRepo.save(domaine);
        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
        return resultDTO;
    }

    public void deleteTransfertCaisse(Integer code) {
        Preconditions.checkArgument(transfertCaisseRepo.existsById(code), "error.TransfertCaisseNotFound");
        transfertCaisseRepo.deleteById(code);
    }

    public TransfertCaisseDTO approuveAC(TransfertCaisseDTO dto) {

        SoldeCaisse soldeCaisse = soldeCaisseRepo.findByCodeCaisse(dto.getCodeCaisseTr());

        BigDecimal mnt = dto.getMontant();

        BigDecimal debit = soldeCaisse.getDebit();

        BigDecimal credit = soldeCaisse.getCredit();
        BigDecimal DebitCredit = debit.subtract(credit);

        BigDecimal soldeTransfered = DebitCredit.subtract(mnt);

        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TransfertCaisseNotFound");

        MouvementCaisse mvtCaisse = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) { 
            mvtCaisse.setCodeSaisie(domaine.getCodeSaisie());
            mvtCaisse.setDebit(domaine.getMontant());
            mvtCaisse.setMntDevise(domaine.getMontantEnDevise());

            mvtCaisse.setCredit(BigDecimal.ZERO);
            mvtCaisse.setDateCreate(domaine.getDateCreate());
            mvtCaisse.setUserCreate(domaine.getUserCreate());
            mvtCaisse.setCodeTier("");
            mvtCaisse.setCodeCaisse(domaine.getCodeCaisse());
            if (mvtCaisse.getCodeCaisse() != null) {
                mvtCaisse.setCaisse(CaisseFactory.createCaisseByCode(domaine.getCodeCaisse()));
            }
            mvtCaisse.setCodeCaisseTr(domaine.getCodeCaisseTr());

            mvtCaisse.setCodeDevise(domaine.getCodeDevise());
            if (mvtCaisse.getCodeDevise() != null) {
                mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(domaine.getCodeDevise()));
            }

            mvtCaisse.setCodeModeReglement(domaine.getCodeModeReglement());
            if (mvtCaisse.getCodeModeReglement() != null) {
                mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(domaine.getCodeModeReglement()));
            }
            mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);
            if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("error.SoldeCaisseNegative");

            }
        }

        /// mvt caisseTR
        MouvementCaisse mvtCaisseTR = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) {
//            mvtCaisse.setCode(dto.getCode());
            System.out.println(" mvt caisseTR OK");
            mvtCaisseTR.setCodeSaisie(domaine.getCodeSaisie());
            mvtCaisseTR.setDebit(BigDecimal.ZERO);
            mvtCaisseTR.setMntDevise(domaine.getMontantEnDevise());
            mvtCaisseTR.setCredit(domaine.getMontant());
            mvtCaisseTR.setDateCreate(domaine.getDateCreate());
            mvtCaisseTR.setUserCreate(domaine.getUserCreate());
            mvtCaisseTR.setCodeTier("");
            mvtCaisseTR.setCodeCaisse(domaine.getCodeCaisseTr());
            if (mvtCaisseTR.getCodeCaisse() != null) {
                mvtCaisseTR.setCaisse(CaisseFactory.createCaisseByCode(domaine.getCodeCaisseTr()));
            }
            mvtCaisseTR.setCodeDevise(domaine.getCodeDevise());
            if (mvtCaisseTR.getCodeDevise() != null) {
                mvtCaisseTR.setDevise(DeviseFactory.createDeviseByCode(domaine.getCodeDevise()));
            }

            mvtCaisseTR.setCodeModeReglement(domaine.getCodeModeReglement());
            if (mvtCaisseTR.getCodeModeReglement() != null) {
                mvtCaisseTR.setModeReglement(ModeReglementFactory.createModeReglementByCode(domaine.getCodeModeReglement()));
            }
            mvtCaisseTR = mouvementCaisseRepo.save(mvtCaisseTR);
            //debit solde caisse 
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
            BigDecimal qteOldDebit = soldeCaisseDTOs.getDebit();
            BigDecimal qteLivree = domaine.getMontant();
            BigDecimal sumQteLivred = qteOldDebit.add(qteLivree);
            soldeCaisseDTOs.setDebit(sumQteLivred);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //credit solde caisse tr
            SoldeCaisseDTO soldeCaisseDTOTR = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisseTr());
            BigDecimal qteOldCreditTR = soldeCaisseDTOTR.getCredit();
            BigDecimal qteMnTTR = domaine.getMontant();
            BigDecimal sumCreditTR = qteOldCreditTR.add(qteMnTTR);
            soldeCaisseDTOTR.setCredit(sumCreditTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOTR);
            if (soldeTransfered.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("error.SoldeCaisseNegative");

            }

        }

        Integer oldEtatApprouve = domaine.getCodeEtatApprouver();
        Integer NewEtatApprouve = dto.getCodeEtatApprouver();
        if (oldEtatApprouve == 2 && NewEtatApprouve == 3) {

            //annule operation trans caisse
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getDebit();
            BigDecimal mntNew = domaine.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setDebit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //annule operation trans caisse TR
            SoldeCaisseDTO soldeCaisseDTOsTR = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisseTr());
            BigDecimal mntOldTR = soldeCaisseDTOsTR.getCredit();
            BigDecimal mntNewTR = domaine.getMontant();
            BigDecimal sumMntTR = mntOldTR.subtract(mntNewTR);
            soldeCaisseDTOsTR.setCredit(sumMntTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOsTR);
            mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
        }

        domaine = TransfertCaisseFactory.ApprouveTransfertCaisseDTOToTransfertCaisse(domaine, dto);
        domaine = transfertCaisseRepo.save(domaine);

        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
        return resultDTO;
    }

    public TransfertCaisseDTO CancelapprouveAC(TransfertCaisseDTO dto) {
        TransfertCaisse domaine = transfertCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TransfertCaisseNotFound");

        Integer oldEtatApprouve = domaine.getCodeEtatApprouver();
        Integer NewEtatApprouve = dto.getCodeEtatApprouver();
        System.out.println("etat approuve old CancelapprouveAC " + oldEtatApprouve + " newww approuve  CancelapprouveAC " + NewEtatApprouve);
//        if (oldEtatApprouve == 3 && NewEtatApprouve == 1 || oldEtatApprouve == 2 && NewEtatApprouve == 1) {  
        if (oldEtatApprouve == 2 && NewEtatApprouve == 1) {

            //annule operation trans caisse
            System.out.println("etat update solde ");
            SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
            BigDecimal mntOld = soldeCaisseDTOs.getDebit();
            BigDecimal mntNew = domaine.getMontant();
            BigDecimal sumMnt = mntOld.subtract(mntNew);
            soldeCaisseDTOs.setDebit(sumMnt);
            soldeCaisseService.updateMontant(soldeCaisseDTOs);

            //annule operation trans caisse TR
            SoldeCaisseDTO soldeCaisseDTOsTR = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisseTr());
            BigDecimal mntOldTR = soldeCaisseDTOsTR.getCredit();
            BigDecimal mntNewTR = domaine.getMontant();
            BigDecimal sumMntTR = mntOldTR.subtract(mntNewTR);
            soldeCaisseDTOsTR.setCredit(sumMntTR);
            soldeCaisseService.updateMontant(soldeCaisseDTOsTR);
        }
        domaine = TransfertCaisseFactory.CancelTransfertCaisseDTOToTransfertCaisse(domaine, dto);
        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());

        domaine = transfertCaisseRepo.save(domaine);
        TransfertCaisseDTO resultDTO = TransfertCaisseFactory.transfertCaisseToTransfertCaisseDTO(domaine);
        return resultDTO;
    }

}
