/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.factory.SocFactory;
import com.FrameWork.ControlCout.Parametrage.domaine.Soc;
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import com.FrameWork.ControlCout.Parametrage.repository.SocRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.FrameWork.ControlCout.web.Util.RestPreconditions;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */

@Service
@Transactional
public class SocService {

    private final Logger log = LoggerFactory.getLogger(SocService.class);
    
    private final SocRepo socRepo; 

    public SocService(SocRepo socRepo) {
        this.socRepo = socRepo;
    }

    
    
   

    /**
     * Save a socDTO.
     *
     * @param socDTO
     * @return the persisted entity
     */
    public SocDTO save(SocDTO socDTO) throws IOException {
        log.debug("Request to save Soc: {}", socDTO);
        Soc soc = SocFactory.socDTOToSoc(socDTO);
        if (!socDTO.getImagePath().isEmpty()) {
            soc.setLogo(Helper.extractBytes(socDTO.getImagePath(), "png"));
        }
        soc = socRepo.save(soc);        
        SocDTO resultDTO = SocFactory.socToSocDTO(soc, false);
        return resultDTO;
    }
 
    /**
     * Get one socDTO by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public SocDTO findOne(Integer id) {
        log.debug("Request to get Soc: {}", id);
        Soc soc = socRepo.findByCode(id);
        RestPreconditions.checkFound(soc, "soc.NotFound");
        SocDTO dto = SocFactory.socToSocDTO(soc, false);
        return dto;
    }
 
    /**
     * Delete soc by id.
     *
     * @param id the id of the entity
     */
    public void delete(Integer id) {
        log.debug("Request to delete Soc: {}", id);
        socRepo.deleteById(id);
    } 
    
      @Transactional(
            readOnly = true
    )
    public SocDTO findFirst() {
        Soc result = socRepo.findFirstBy();
        return SocFactory.socToSocDTO(result, false);
    }
    
    
    @Transactional(
            readOnly = true
    )
    public SocDTO findOne(Integer id, Boolean withoutLogo) {
        log.debug("Request to get Soc: {}", id);
        Soc soc = socRepo.findByCode(id);
        RestPreconditions.checkFound(soc, "soc.NotFound");
        SocDTO dto = SocFactory.socToSocDTO(soc, withoutLogo);
        return dto;
    }
}
