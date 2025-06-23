/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.EtatFacture;
import com.FrameWork.ControlCout.Parametrage.dto.EtatFactureDTO;
import com.FrameWork.ControlCout.Parametrage.factory.EtatFactureFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.EtatFactureRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class EtatFactureService {

    private final EtatFactureRepo etatFactureRepo; 

    public EtatFactureService(EtatFactureRepo etatFactureRepo) {
        this.etatFactureRepo = etatFactureRepo;
    }

    

    @Transactional(readOnly = true)
    public List<EtatFactureDTO> findAllEtatFacture() {
        return EtatFactureFactory.listEtatFactureToEtatFactureDTOs(etatFactureRepo.findAll(Sort.by("code").ascending()));

    }

    @Transactional(readOnly = true)
    public EtatFactureDTO findOne(Integer code) {
        EtatFacture domaine = etatFactureRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.EtatFactureNotFound");
        return EtatFactureFactory.etatFactureToEtatFactureDTO(domaine);
    }

 
 

   
}
