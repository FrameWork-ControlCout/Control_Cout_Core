/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Tresorerie.domaine.AvanceFournisseur;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.AvanceFournisseurDTO;
import com.FrameWork.ControlCout.Tresorerie.dto.SoldeCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.AvanceFournisseurFactory;
import com.FrameWork.ControlCout.Tresorerie.factory.CaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.AvanceFournisseurRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementCaisseRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.Collection;
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
public class AvanceFournisseurService {

    private final AvanceFournisseurRepo avanceFournisseurRepo;
    private final SoldeCaisseService soldeCaisseService;
    private final CompteurService compteurService;
    private final MouvementCaisseRepo mouvementCaisseRepo;

    public AvanceFournisseurService(AvanceFournisseurRepo avanceFournisseurRepo, SoldeCaisseService soldeCaisseService, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo) {
        this.avanceFournisseurRepo = avanceFournisseurRepo;
        this.soldeCaisseService = soldeCaisseService;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
    }

    @Transactional(readOnly = true)
    public List<AvanceFournisseurDTO> findAllAvanceFournisseur() {
        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(avanceFournisseurRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public AvanceFournisseurDTO findOne(Integer code) {
        AvanceFournisseur domaine = avanceFournisseurRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AvanceFournisseurNotFound");
// 

        return AvanceFournisseurFactory.avanceFournisserToAvanceFournisseurDTO(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AvanceFournisseurDTO> findByCodeCaisse(Collection<Integer> codeCaisse) {
        Collection<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeCaisseIn(Helper.removeNullValueFromCollection(codeCaisse));
        return AvanceFournisseurFactory.CollectionavanceFournissersToavanceFournissersDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public Collection<AvanceFournisseurDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<AvanceFournisseur> result = avanceFournisseurRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return AvanceFournisseurFactory.CollectionavanceFournissersToavanceFournissersDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<AvanceFournisseurDTO> findByCodeFournisseur(Integer codeFournisseur) {
        return AvanceFournisseurFactory.listAvanceFournisseurToAvanceFournisseurDTOs(avanceFournisseurRepo.findByCodeFournisseur(codeFournisseur));
    }

    public AvanceFournisseurDTO save(AvanceFournisseurDTO dto) {

        AvanceFournisseur domaine = AvanceFournisseurFactory.avanceFournisserDTOToAvanceFournisseur(new AvanceFournisseur(), dto);
        Compteur CompteurCodeSaisie = compteurService.findOne("AvanceFournisseur");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = avanceFournisseurRepo.save(domaine);

        MouvementCaisse mvtCaisse = new MouvementCaisse();
        mvtCaisse.setCodeSaisie(domaine.getCodeSaisie());
        mvtCaisse.setDebit(domaine.getMntAvance());
        mvtCaisse.setMntDevise(domaine.getMontantEnDevise());

        mvtCaisse.setCredit(BigDecimal.ZERO);
        mvtCaisse.setDateCreate(domaine.getDateCreate());
        mvtCaisse.setUserCreate(domaine.getUserCreate());
        mvtCaisse.setCodeTier("");
        mvtCaisse.setCodeCaisse(domaine.getCodeCaisse());
        if (mvtCaisse.getCodeCaisse() != null) {
            mvtCaisse.setCaisse(CaisseFactory.createCaisseByCode(domaine.getCodeCaisse()));
        }

//            mvtCaisse.setCodeCaisseTr(0);
        mvtCaisse.setCodeDevise(domaine.getCodeDevise());
        if (mvtCaisse.getCodeDevise() != null) {
            mvtCaisse.setDevise(DeviseFactory.createDeviseByCode(domaine.getCodeDevise()));

        }

        mvtCaisse.setCodeModeReglement(domaine.getCodeModeReglement());
        if (mvtCaisse.getCodeModeReglement() != null) {
            mvtCaisse.setModeReglement(ModeReglementFactory.createModeReglementByCode(domaine.getCodeModeReglement()));

        }
        mvtCaisse = mouvementCaisseRepo.save(mvtCaisse);

        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
        BigDecimal qteOldDebit = soldeCaisseDTOs.getDebit();
        BigDecimal qteLivree = domaine.getMntAvance();
        BigDecimal sumQteLivred = qteOldDebit.add(qteLivree);
        soldeCaisseDTOs.setDebit(sumQteLivred);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);

        return AvanceFournisseurFactory.avanceFournisserToAvanceFournisseurDTO(domaine);
    }
//
//    public AvanceFournisseur update(AvanceFournisseurDTO dTO) { 
//        AvanceFournisseur domaine = avanceFournisseurRepo.getReferenceById(dTO.getCode());
//        Preconditions.checkArgument(true, "error.AvanceFournisseurNotFound");
//    
//        domaine.getDetailsAvanceFournisseurs().clear();
//        avanceFournisseurRepo.flush();
//        AvanceFournisseurFactory.avanceFournisserDTOToAvanceFournisseur(dTO, domaine);
//        return avanceFournisseurRepo.save(domaine);
//    }
//    

    public AvanceFournisseurDTO update(AvanceFournisseurDTO dto) {
        AvanceFournisseur domaine = avanceFournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AvanceFournisseurNotFound");
        domaine = AvanceFournisseurFactory.avanceFournisserDTOToAvanceFournisseur(domaine, dto);
        domaine = avanceFournisseurRepo.save(domaine);
        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisserToAvanceFournisseurDTO(domaine);
        return resultDTO;
    }

    public void deleteAvanceFournisseur(Integer code) {
        Preconditions.checkArgument(avanceFournisseurRepo.existsById(code), "error.AvanceFournisseurNotFound");
        
        AvanceFournisseur domaine = avanceFournisseurRepo.findByCode(code);
        
         SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
        BigDecimal mntOld = soldeCaisseDTOs.getDebit();
        BigDecimal mntNew = domaine.getMntAvance();
        BigDecimal sumMnt = mntOld.subtract(mntNew);
        soldeCaisseDTOs.setDebit(sumMnt);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);

        mouvementCaisseRepo.deleteByCodeSaisie(domaine.getCodeSaisie());
        
        avanceFournisseurRepo.deleteById(code);
    }

//    public AvanceFournisseurDTO approuveAC(AvanceFournisseurDTO dto) {
//        AvanceFournisseur domaine = avanceFournisseurRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(domaine != null, "error.AvanceFournisseurNotFound");
//        domaine = AvanceFournisseurFactory.ApprouveAvanceFournisseurDTOToAvanceFournisseur(domaine, dto);
//
//        MouvementCaisse mvtCaisse = new MouvementCaisse();
//        if (dto.getCodeEtatApprouver() == 2) {
////            mvtCaisse.setCode(dto.getCode());
//
//        }
//
//        domaine = avanceFournisseurRepo.save(domaine);
//
//        System.out.println("ok marche");
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisserToAvanceFournisseurDTO(domaine);
//        return resultDTO;
//    }

//    public AvanceFournisseurDTO CancelapprouveAC(AvanceFournisseurDTO dto) {
//        AvanceFournisseur domaine = avanceFournisseurRepo.findByCode(dto.getCode());
//        Preconditions.checkArgument(domaine != null, "error.AvanceFournisseurNotFound");
//        domaine = AvanceFournisseurFactory.CancelAvanceFournisseurDTOToAvanceFournisseur(domaine, dto);
//
//        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
//        BigDecimal mntOld = soldeCaisseDTOs.getDebit();
//        BigDecimal mntNew = domaine.getMontant();
//        BigDecimal sumMnt = mntOld.subtract(mntNew);
//        soldeCaisseDTOs.setDebit(sumMnt);
//        soldeCaisseService.updateMontant(soldeCaisseDTOs);
//
//        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());
//
//        domaine = avanceFournisseurRepo.save(domaine);
//        AvanceFournisseurDTO resultDTO = AvanceFournisseurFactory.avanceFournisserToAvanceFournisseurDTO(domaine);
//        return resultDTO;
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<DetailsAvanceFournisseurDTO> findOneWithDetails(Integer code) {
//        Collection<DetailsAvanceFournisseur> domaine = detailsAvanceFournisseurRepo.findByDetailsAvanceFournisseurPK_codeAvanceFournisseur(code);
//        return DetailsAvanceFournisseurFactory.detailsAvanceFournisseurTodetailsAvanceFournisseurDTOCollections(domaine);
//    }
}
