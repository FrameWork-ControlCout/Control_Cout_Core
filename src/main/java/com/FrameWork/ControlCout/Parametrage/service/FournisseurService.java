/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.FournisseurRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class FournisseurService {

    private final FournisseurRepo fournisseurRepo;
    private final CompteurService compteurService;

    public FournisseurService(FournisseurRepo fournisseurRepo, CompteurService compteurService) {
        this.fournisseurRepo = fournisseurRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAllFournisseur() {
        return FournisseurFactory.listFournisseurToFournisseurDTOs(fournisseurRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public FournisseurDTO findOne(Integer code) {
        Fournisseur domaine = fournisseurRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.FournisseurNotFound");
        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAllFournisseurByActif(Boolean actif) {
        return FournisseurFactory.listFournisseurToFournisseurDTOs(fournisseurRepo.findByActif(actif));

    }
 
    @Transactional(readOnly = true)
    public List<FournisseurDTO> findAllFournisseurByActifAndGros(Boolean actif,Boolean gros) {
        return FournisseurFactory.listFournisseurToFournisseurDTOs(fournisseurRepo.findByActifAndGros(actif,gros));

    }

    public FournisseurDTO save(FournisseurDTO dto) {
        Fournisseur domaine = FournisseurFactory.fournisseurDTOToFournisseur(dto, new Fournisseur());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("Fournisseur");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = fournisseurRepo.save(domaine);

        return FournisseurFactory.fournisseurToFournisseurDTO(domaine);
    }

    public Fournisseur update(FournisseurDTO dto) {
        Fournisseur domaine = fournisseurRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FournisseurNotFound");
        dto.setCode(domaine.getCode());
        FournisseurFactory.fournisseurDTOToFournisseur(dto, domaine);

        return fournisseurRepo.save(domaine);
    }

    public void deleteFournisseur(Integer code) {
        Preconditions.checkArgument(fournisseurRepo.existsById(code), "error.FournisseurNotFound");
        fournisseurRepo.deleteById(code);
    }
}
