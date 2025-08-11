/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;

 
import com.FrameWork.ControlCout.Tresorerie.domaine.EtatApprouver;
import com.FrameWork.ControlCout.Tresorerie.dto.EtatApprouverDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.EtatApprouverFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.EtatApprouverRepo;
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
public class EtatApprouverService {
    private final EtatApprouverRepo etatApprouveRepo;

    public EtatApprouverService(EtatApprouverRepo etatApprouveRepo) {
        this.etatApprouveRepo = etatApprouveRepo;
    }

   

    @Transactional(readOnly = true)
    public List<EtatApprouverDTO> findAllEtatApprouve() {
        return EtatApprouverFactory.listEtatApprouverToEtatApprouverDTOs(etatApprouveRepo.findAll());

    }

    @Transactional(readOnly = true)
    public EtatApprouverDTO findOne(Integer code) {
        EtatApprouver domaine = etatApprouveRepo.getReferenceById(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.EtatApprouverOrdreAchatNotFound");
        return EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine);
    }

 
 

  
}
