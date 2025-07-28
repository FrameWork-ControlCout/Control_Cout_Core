/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.EtatPaiement;
import com.FrameWork.ControlCout.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.ControlCout.Parametrage.factory.EtatPaiementFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.EtatPaiementRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class EtatPaiementService {

    private final EtatPaiementRepo etatPaiementRepo; 

    public EtatPaiementService(EtatPaiementRepo etatPaiementRepo) {
        this.etatPaiementRepo = etatPaiementRepo;
    }

    

    @Transactional(readOnly = true)
    public List<EtatPaiementDTO> findAllEtapReception() {
        return EtatPaiementFactory.listEtatPaiementToEtatPaiementDTOs(etatPaiementRepo.findAll(Sort.by("code").ascending()));

    }

    @Transactional(readOnly = true)
    public EtatPaiementDTO findOne(Integer code) {
        EtatPaiement domaine = etatPaiementRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.EtapPaiementNotFound");
        return EtatPaiementFactory.etatPaiementToEtatPaiementDTO(domaine);
    }

 
 

   
}
