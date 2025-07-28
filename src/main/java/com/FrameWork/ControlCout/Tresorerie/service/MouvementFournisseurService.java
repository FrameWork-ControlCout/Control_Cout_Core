/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.BonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureBonReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementFournisseur;
import com.FrameWork.ControlCout.Tresorerie.dto.MouvementFournisseurDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.MouvementFournisseurFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementFournisseurRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Transactional
@Service
public class MouvementFournisseurService {
    
    private final MouvementFournisseurRepo mouvemenetFournisseurRepo;

    public MouvementFournisseurService(MouvementFournisseurRepo mouvemenetFournisseurRepo) {
        this.mouvemenetFournisseurRepo = mouvemenetFournisseurRepo;
    }
    
    @Transactional(readOnly = true)
    public List<MouvementFournisseurDTO> findAllMouvementFournisseur() {
        return MouvementFournisseurFactory.listMouvementFournisseurToMouvementFournisseurDTOs( 
                mouvemenetFournisseurRepo.findAll(Sort.by("code").descending())
        );
    }
    
      @Transactional(readOnly = true)
    public List<MouvementFournisseurDTO> findAllMouvementFournisseurByFournisseur(Integer codeFournisseur) {
        return MouvementFournisseurFactory.listMouvementFournisseurToMouvementFournisseurDTOs( 
                mouvemenetFournisseurRepo.findByCodeFournisseur(codeFournisseur)
        );
    }
    
       @Transactional(readOnly = true)
    public List<MouvementFournisseurDTO> findAllMouvementFournisseurByFournisseurAndTypeMouvement(Integer codeFournisseur,String typeMouvement) {
        return MouvementFournisseurFactory.listMouvementFournisseurToMouvementFournisseurDTOs( 
                mouvemenetFournisseurRepo.findByCodeFournisseurAndTypeMouvement(codeFournisseur,typeMouvement)
        );
    }
    
     @Transactional
    public MouvementFournisseurDTO save(MouvementFournisseurDTO dto) {
        // 1. SETUP BON RECEPTION HEADER
         MouvementFournisseur domaine = MouvementFournisseurFactory.mouvementFournisseurDTOToMouvementFournisseur(dto, new MouvementFournisseur()); 
        domaine.setDateCreate(new Date());
        domaine.setUserCreate(Helper.getUserAuthenticated()); 
        domaine = mouvemenetFournisseurRepo.save(domaine); 
        return MouvementFournisseurFactory.mouvementFournisseurToMouvementFournisseurDTO(domaine);
    }
}
