/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.EtatReception;
import com.FrameWork.ControlCout.Parametrage.dto.EtatReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.EtatReceptionRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class EtatReceptionService {

    private final EtatReceptionRepo etapReceptionRepo; 

    public EtatReceptionService(EtatReceptionRepo etapReceptionRepo) {
        this.etapReceptionRepo = etapReceptionRepo;
    }

    

    @Transactional(readOnly = true)
    public List<EtatReceptionDTO> findAllEtapReception() {
        return EtatReceptionFactory.listEtapReceptionToEtapReceptionDTOs(etapReceptionRepo.findAll(Sort.by("code").ascending()));

    }

    @Transactional(readOnly = true)
    public EtatReceptionDTO findOne(Integer code) {
        EtatReception domaine = etapReceptionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.EtapReceptionNotFound");
        return EtatReceptionFactory.etapReceptionToEtapReceptionDTO(domaine);
    }

 
 

   
}
