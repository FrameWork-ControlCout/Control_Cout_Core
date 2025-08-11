/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Tresorerie.domaine.AlimentationCaisse;
import com.FrameWork.ControlCout.Tresorerie.domaine.DetailsAlimentationCaisse;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.AlimentationCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.dto.DetailsAlimentationCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.dto.SoldeCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.AlimentationCaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.factory.CaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.factory.DetailsAlimentationCaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.AlimentationCaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.DetailsAlimentationCaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementCaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.SoldeCaisseRepo;
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
public class AlimentationCaisseService {

    private final AlimentationCaisseRepo alimentationCaisseRepo;

    private final CompteurService compteurService;

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo;
    private final MouvementCaisseService mouvementCaisseSerivce;

    private final SoldeCaisseRepo soldeCaisseRepo;
    private final SoldeCaisseService soldeCaisseService;

    public AlimentationCaisseService(AlimentationCaisseRepo alimentationCaisseRepo, CompteurService compteurService, MouvementCaisseRepo mouvementCaisseRepo, DetailsAlimentationCaisseRepo detailsAlimentationCaisseRepo, MouvementCaisseService mouvementCaisseSerivce, SoldeCaisseRepo soldeCaisseRepo, SoldeCaisseService soldeCaisseService) {
        this.alimentationCaisseRepo = alimentationCaisseRepo;
        this.compteurService = compteurService;
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.detailsAlimentationCaisseRepo = detailsAlimentationCaisseRepo;
        this.mouvementCaisseSerivce = mouvementCaisseSerivce;
        this.soldeCaisseRepo = soldeCaisseRepo;
        this.soldeCaisseService = soldeCaisseService;
    }

    @Transactional(readOnly = true)
    public List<AlimentationCaisseDTO> findAllAlimentationCaisse() {
        return AlimentationCaisseFactory.listAlimentationCaisseToAlimentationCaisseDTOs(alimentationCaisseRepo.findAllByOrderByCodeSaisieDesc());

    }

    @Transactional(readOnly = true)
    public AlimentationCaisseDTO findOne(Integer code) {
        AlimentationCaisse domaine = alimentationCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.AlimentationCaisseNotFound");
// 

        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTOUpdate(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeCaisse(Collection<Integer> codeCaisse) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeCaisseIn(Helper.removeNullValueFromCollection(codeCaisse));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public Collection<AlimentationCaisseDTO> findByCodeDevise(Collection<Integer> codeDevise) {
        Collection<AlimentationCaisse> result = alimentationCaisseRepo.findByCodeDeviseIn(Helper.removeNullValueFromCollection(codeDevise));
        return AlimentationCaisseFactory.CollectionalimentationCaissesToalimentationCaissesDTOsCollection(result);
    }

    @Transactional(readOnly = true)
    public List<AlimentationCaisseDTO> findByEtatApprouver(Integer CodeEtatApprouver) {
        return AlimentationCaisseFactory.listAlimentationCaisseToAlimentationCaisseDTOs(alimentationCaisseRepo.findAlimentationCaisseByCodeEtatApprouver(CodeEtatApprouver));
    }

    public AlimentationCaisseDTO save(AlimentationCaisseDTO dto) {

        AlimentationCaisse domaine = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(new AlimentationCaisse(), dto);
        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieAC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = alimentationCaisseRepo.save(domaine);
        return AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
    }

    public AlimentationCaisseDTO updateNewWithFlush(AlimentationCaisseDTO dto) {
        AlimentationCaisse domaine = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        detailsAlimentationCaisseRepo.deleteByCodeAlimentationCaisse(dto.getCode());
        domaine = AlimentationCaisseFactory.alimentationCaisseDTOToAlimentationCaisse(domaine, dto);
        domaine = alimentationCaisseRepo.save(domaine);
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
        return resultDTO;
    }

    public void deleteAlimentationCaisse(Integer code) {
        Preconditions.checkArgument(alimentationCaisseRepo.existsById(code), "error.AlimentationCaisseNotFound");
        alimentationCaisseRepo.deleteById(code);
    }

    public AlimentationCaisseDTO approuveAC(AlimentationCaisseDTO dto) {
        AlimentationCaisse domaine = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = AlimentationCaisseFactory.ApprouveAlimentationCaisseDTOToAlimentationCaisse(domaine, dto);

        MouvementCaisse mvtCaisse = new MouvementCaisse();
        if (dto.getCodeEtatApprouver() == 2) {
//            mvtCaisse.setCode(dto.getCode());
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
        }

        domaine = alimentationCaisseRepo.save(domaine);

        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
        BigDecimal qteOldDebit = soldeCaisseDTOs.getDebit();
        BigDecimal qteLivree = domaine.getMontant();
        BigDecimal sumQteLivred = qteOldDebit.add(qteLivree);
        soldeCaisseDTOs.setDebit(sumQteLivred);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);
        System.out.println("ok marche");
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
        return resultDTO;
    }

    public AlimentationCaisseDTO CancelapprouveAC(AlimentationCaisseDTO dto) {
        AlimentationCaisse domaine = alimentationCaisseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = AlimentationCaisseFactory.CancelAlimentationCaisseDTOToAlimentationCaisse(domaine, dto);

        SoldeCaisseDTO soldeCaisseDTOs = soldeCaisseService.findByCodeCaisse(domaine.getCodeCaisse());
        BigDecimal mntOld = soldeCaisseDTOs.getDebit();
        BigDecimal mntNew = domaine.getMontant();
        BigDecimal sumMnt = mntOld.subtract(mntNew);
        soldeCaisseDTOs.setDebit(sumMnt);
        soldeCaisseService.updateMontant(soldeCaisseDTOs);

        mouvementCaisseRepo.deleteByCodeSaisie(dto.getCodeSaisie());

        domaine = alimentationCaisseRepo.save(domaine);
        AlimentationCaisseDTO resultDTO = AlimentationCaisseFactory.alimentationCaisseToAlimentationCaisseDTO(domaine);
        return resultDTO;
    }

    @Transactional(readOnly = true)
    public Collection<DetailsAlimentationCaisseDTO> findOneWithDetails(Integer code) {
        Collection<DetailsAlimentationCaisse> domaine = detailsAlimentationCaisseRepo.findByDetailsAlimentationCaissePK_codeAlimentationCaisse(code);
        return DetailsAlimentationCaisseFactory.detailsAlimentationCaisseTodetailsAlimentationCaisseDTOCollections(domaine);
    }

}
