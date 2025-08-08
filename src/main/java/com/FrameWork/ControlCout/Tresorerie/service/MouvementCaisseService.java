/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;
 
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.Tresorerie.domaine.Caisse;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.MouvementCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.MouvementCaisseFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.CaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementCaisseRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class MouvementCaisseService {

    private final MouvementCaisseRepo mouvementCaisseRepo;

    private final CompteurService compteurService;
    private final CaisseRepo caisseRepo;

    public MouvementCaisseService(MouvementCaisseRepo mouvementCaisseRepo, CompteurService compteurService, CaisseRepo caisseRepo) {
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.compteurService = compteurService;
        this.caisseRepo = caisseRepo;
    }

    @Transactional(readOnly = true)
    public List<MouvementCaisseDTO> findAllMouvementCaisse() {
        List<MouvementCaisse> domaine = mouvementCaisseRepo.findAllByOrderByDateCreateDesc();
        List<MouvementCaisseDTO> dtos = MouvementCaisseFactory.listMouvementCaisseToMouvementCaisseDTOs(domaine);
        for (MouvementCaisseDTO dto : dtos) {

            if (dto.getCodeCaisseTr() == null) {

            } else {
                Caisse caisse = caisseRepo.findByCode(dto.getCodeCaisseTr());
                dto.setDesignationCaisse(caisse.getDesignationAr());
            } 
        }
        return dtos;
    }
 
    @Transactional(readOnly = true)
    public MouvementCaisseDTO findOne(Integer code) {
        MouvementCaisse domaine = mouvementCaisseRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.MouvementCaisseNotFound");
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public MouvementCaisseDTO findByCodeSaisie(String codeCaisse) {
        MouvementCaisse result = mouvementCaisseRepo.findMouvementCaisseByCodeSaisie(codeCaisse);
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(result);
    }

//    @Transactional(readOnly = true)
//    public MouvementCaisseDTO findByCodeCaisse(Integer codeCaisse) {
//        MouvementCaisse result = mouvementCaisseRepo.findMouvementCaisseByCodeCaisse(codeCaisse);
//        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(result);
//    }

    
      @Transactional(readOnly = true)
    public List<MouvementCaisseDTO> findByCodeCaisse(Integer codeCaisse) {
        List<MouvementCaisse> domaine = mouvementCaisseRepo.findMouvementCaisseByCodeCaisse(codeCaisse);
        List<MouvementCaisseDTO> dtos = MouvementCaisseFactory.listMouvementCaisseToMouvementCaisseDTOs(domaine);
        for (MouvementCaisseDTO dto : dtos) {

            if (dto.getCodeCaisseTr() == null) {

            } else {
                Caisse caisse = caisseRepo.findByCode(dto.getCodeCaisseTr());
                dto.setDesignationCaisse(caisse.getDesignationAr());
            } 
        }
        return dtos;
    }
 
    
    
    public MouvementCaisseDTO save(MouvementCaisseDTO dTO) {
        MouvementCaisse domaine = MouvementCaisseFactory.mouvementCaisseDTOToMouvementCaisse(dTO, new MouvementCaisse());
        domaine = mouvementCaisseRepo.save(domaine);
        return MouvementCaisseFactory.mouvementCaisseToMouvementCaisseDTO(domaine);
    }

}
