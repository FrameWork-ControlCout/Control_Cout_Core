/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.dto.CompteurDTO;
import com.FrameWork.ControlCout.Parametrage.factory.CompteurFactory;
import com.FrameWork.ControlCout.Parametrage.repository.CompteurRepo;
import com.FrameWork.ControlCout.web.Util.Helper; 
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
public class CompteurService {

    private final CompteurRepo compteurRepo;

    public CompteurService(CompteurRepo compteurRepo) {
        this.compteurRepo = compteurRepo;
    }

    @Transactional(readOnly = true)
    public List<CompteurDTO> findAllCompteur() {
        return CompteurFactory.listCompteurToCompteurDTOs(compteurRepo.findAll());
    }

    @Transactional(readOnly = true)
    public Compteur findOne(String id) {
        Compteur compteur = compteurRepo.findByCompteur(id);
        com.FrameWork.ControlCout.web.Util.Preconditions.checkBusinessLogique(compteur != null, "error.CompteurNotFound", id);
        return compteur;
    }
//

    public Boolean incrementeSuffixe(Compteur compteur) {
        if (compteur != null) {
            compteur.setSuffixe(Helper.incrementString(compteur.getSuffixe()));
            compteurRepo.save(compteur);
            return true;
        } else {
            return false;
        }
    }

    public Compteur update(CompteurDTO dTO) {
        Preconditions.checkArgument((dTO.getCode() != null), "error.CompteurNotFound");
        Compteur domaine = compteurRepo.getReferenceById(dTO.getCode());
        Preconditions.checkArgument(true, "error.CompteurNotFound");
        dTO.setCode(domaine.getCode());
        CompteurFactory.compteurDTOToCompteur(dTO, domaine);
        return compteurRepo.save(domaine);
    }

}
