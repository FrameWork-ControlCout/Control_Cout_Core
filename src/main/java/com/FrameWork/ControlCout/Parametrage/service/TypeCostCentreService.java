/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.service;

 
import com.FrameWork.ControlCout.Parametrage.domaine.TypeCostCentre;
import com.FrameWork.ControlCout.Parametrage.dto.TypeCostCentreDTO;
import com.FrameWork.ControlCout.Parametrage.factory.TypeCostCentreFactory;
import com.google.common.base.Preconditions;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.TypeCostCentreRepository;
import com.FrameWork.ControlCout.web.Util.Helper;
import java.util.Date;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Administrateur
 */
@Service
@Transactional
public class TypeCostCentreService {

    private final Logger log = LoggerFactory.getLogger(TypeCostCentreService.class);

    private final TypeCostCentreRepository typeCostCentreRepository; 

    public TypeCostCentreService(TypeCostCentreRepository typeCostCentreRepository) {
        this.typeCostCentreRepository = typeCostCentreRepository;
    }

   

     
    @Transactional(readOnly = true)
    public TypeCostCentreDTO findOne(Integer id) {
        log.debug("Request to get TypeCostCentre: {}", id);
        TypeCostCentre typeCostCentre = typeCostCentreRepository.getReferenceById(id);
        TypeCostCentreDTO dto = TypeCostCentreFactory.TypeCostCentreTOTypeCostCentreDTO(typeCostCentre);
        return dto;
    }

    @Transactional(readOnly = true)
    public Collection<TypeCostCentreDTO> findAll() {
        log.debug("Request to get All TypeCostCentre: {}");
        Collection<TypeCostCentre> typeCostCentres = typeCostCentreRepository.findAll(Sort.by("code").descending());
        return TypeCostCentreFactory.TypeCostCentresTOTypeCostCentreDTOs(typeCostCentres);
    }

    @Transactional(readOnly = true)
    public Collection<TypeCostCentreDTO> findByActifIn(Collection<Boolean> actif) {
        log.debug("Request to get All TypeCostCentre by actif: {}", actif);
        Collection<TypeCostCentre> typeCostCentres = typeCostCentreRepository.findByActifIn(actif);
        return TypeCostCentreFactory.TypeCostCentresTOTypeCostCentreDTOs(typeCostCentres);
    }
    
    public TypeCostCentreDTO save(TypeCostCentreDTO dto) {
        TypeCostCentre domaine = TypeCostCentreFactory.TypeCostCentreDTOTOTypeCostCentre(dto, new TypeCostCentre());
               domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = typeCostCentreRepository.save(domaine);
        return TypeCostCentreFactory.TypeCostCentreTOTypeCostCentreDTO(domaine);
    }

    public TypeCostCentre update(TypeCostCentreDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TypeCaisseNotFound");
        TypeCostCentre domaine = typeCostCentreRepository.getReferenceById(dto.getCode());
        Preconditions.checkArgument(true, "error.TypeCaisseNotFound");
        dto.setCode(domaine.getCode());
        TypeCostCentreFactory.TypeCostCentreDTOTOTypeCostCentre(dto, domaine);
        return typeCostCentreRepository.save(domaine);
    }

    public void deleteTypeCaisse(Integer code) {
        Preconditions.checkArgument(typeCostCentreRepository.existsById(code), "error.TypeCaisseNotFound");
        typeCostCentreRepository.deleteById(code);
    }

     
}
