/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import com.FrameWork.ControlCout.Parametrage.dto.SocieteDTO;
import com.FrameWork.ControlCout.Parametrage.dto.TraceSocieteDTO; 
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import com.FrameWork.ControlCout.Parametrage.factory.TraceSocieteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.TraceSocieteRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
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
public class TraceSocieteService {
    
    private final TraceSocieteRepo traceSocieteRepo;

    public TraceSocieteService(TraceSocieteRepo traceSocieteRepo) {
        this.traceSocieteRepo = traceSocieteRepo;
    }

   
       @Transactional(readOnly = true)
    public List<TraceSocieteDTO> findAllTraceSocieteByCodeSociete(Integer codeSociete) {
         Sort sort = Sort.by(Sort.Direction.DESC, "code");
        return TraceSocieteFactory.listTraceSocieteToTraceSocieteDTOs(traceSocieteRepo.findByCodeSociete(codeSociete,sort));

    }
    
      public TraceSocieteDTO save(TraceSocieteDTO dto) {
        TraceSociete domaine = TraceSocieteFactory.traceSocieteDTOToTraceSociete(dto, new TraceSociete());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());  
        domaine = traceSocieteRepo.save(domaine); 
        return TraceSocieteFactory.traceSocieteToTraceSocieteDTO(domaine);
    }
}
