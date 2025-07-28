/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.factory.DepotFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.repository.DepotRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DepotService {

    private final DepotRepo depotRepo;
    private final CompteurService compteurService;

    public DepotService(DepotRepo depotRepo, CompteurService compteurService) {
        this.depotRepo = depotRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<DepotDTO> findAllDepot() {
        return DepotFactory.listDepotToDepotDTOs(depotRepo.findAll(Sort.by("code").descending()));

    }

    
    @Transactional(readOnly = true)
    public List<DepotDTO> findAllDepotByActif(Boolean actif) {
        return DepotFactory.listDepotToDepotDTOs(depotRepo.findByActif(actif));

    }

 @Transactional(readOnly = true)
    public List<DepotDTO> findAllDepotByActifAndAutosieRecep(Boolean actif,Boolean autoriseRecep) {
        return DepotFactory.listDepotToDepotDTOs(depotRepo.findByActifAndAutoriseRecep(actif,autoriseRecep));

    }
    @Transactional(readOnly = true)
    public DepotDTO findOne(Integer code) {
        Depot domaine = depotRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DepotNotFound");
        return DepotFactory.depotToDepotDTO(domaine);
    }

//
    public DepotDTO save(DepotDTO dto) {
        Depot domaine = DepotFactory.depotDTOToDepot(dto, new Depot());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieDP");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = depotRepo.save(domaine);
        return DepotFactory.depotToDepotDTO(domaine);
    }

    public Depot update(DepotDTO dto) {
        Depot domaine = depotRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DepotNotFound");
        dto.setCode(domaine.getCode());
        DepotFactory.depotDTOToDepot(dto, domaine);
        return depotRepo.save(domaine);
    }

    public void deleteDepot(Integer code) {
        Preconditions.checkArgument(depotRepo.existsById(code), "error.DepotNotFound");
        depotRepo.deleteById(code);
    }
}
