/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.CompositionUnite;
import com.FrameWork.ControlCout.Parametrage.dto.CompositionUniteDTO;
import com.FrameWork.ControlCout.Parametrage.factory.CompositionUniteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.CompositionUniteRepo;
import com.google.common.base.Preconditions;
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
public class CompositionUniteService {
    private final CompositionUniteRepo compositionUniteRepo; 

    public CompositionUniteService(CompositionUniteRepo compositionUniteRepo) {
        this.compositionUniteRepo = compositionUniteRepo;
    }

 
    @Transactional(readOnly = true)
    public List<CompositionUniteDTO> findAllCompositionUnite() {
        return CompositionUniteFactory.listCompositionUniteToCompositionUniteDTOs(compositionUniteRepo.findAll(Sort.by("code").descending()));
    }
    
    @Transactional(readOnly = true)
    public CompositionUniteDTO findOne(Integer code) {
        CompositionUnite domaine = compositionUniteRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.CompositionUniteNotFound");
        return CompositionUniteFactory.compositionUniteToCompositionUniteDTO(domaine);
    }
    
       @Transactional(readOnly = true)
    public CompositionUniteDTO findByCodeUnitePrincipal(Integer codeUnitePrincipal) {
        CompositionUnite domaine = compositionUniteRepo.findByCodeUnitePrinc(codeUnitePrincipal);
        Preconditions.checkArgument(domaine != null, "error.CompositionUniteNotFound");
        return CompositionUniteFactory.compositionUniteToCompositionUniteDTO(domaine);
    }

    public CompositionUniteDTO save(CompositionUniteDTO dto) {
        CompositionUnite domaine = CompositionUniteFactory.compositionUniteDTOToCompositionUnite(dto, new CompositionUnite());
 
        domaine = compositionUniteRepo.save(domaine);
        return CompositionUniteFactory.compositionUniteToCompositionUniteDTO(domaine);
    }

    public CompositionUnite update(CompositionUniteDTO dto) {
        CompositionUnite domaine = compositionUniteRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.CompositionUniteNotFound");
        dto.setCode(domaine.getCode());
        CompositionUniteFactory.compositionUniteDTOToCompositionUnite(dto, domaine);
       
        return compositionUniteRepo.save(domaine);
    }

   public void deleteCompositionUniteByCodeUniPrinc(Integer code) {
        Preconditions.checkArgument(compositionUniteRepo.existsById(code), "error.CompositionUniteNotFound");
        compositionUniteRepo.deleteByCodeUnitePrinc(code);
    }
}
