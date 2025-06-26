package com.FrameWork.ControlCout.Parametrage.service;
 
import com.FrameWork.ControlCout.Parametrage.domaine.CostProfitCentre;
import com.FrameWork.ControlCout.Parametrage.dto.CostProfitCentreDTO;
import com.FrameWork.ControlCout.Parametrage.factory.CostProfitCentreFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.CostProfitCentreRepository;
import org.springframework.data.domain.Sort;

/**
 * Service Implementation for managing CostProfitCentre.
 */
@Service
@Transactional
public class CostProfitCentreService {

    private final Logger log = LoggerFactory.getLogger(CostProfitCentreService.class);

    @Autowired
    EntityManager entityManager;
    private static final String ENTITY_NAME = "Code";

    private final CostProfitCentreRepository costprofitcentreRepository; 

    public CostProfitCentreService(CostProfitCentreRepository costprofitcentreRepository) {
        this.costprofitcentreRepository = costprofitcentreRepository;
    }

    

 
 
    /**
     * Update a costprofitcentreDTO.
     *
     * @param costprofitcentreDTO
     * @return the updated entity
     */
    public CostProfitCentreDTO update(CostProfitCentreDTO costprofitcentreDTO) {
        log.debug("Request to update CostProfitCentre: {}", costprofitcentreDTO);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(costprofitcentreDTO.getCode());
        Preconditions.checkArgument(costprofitcentre.getCode() != null, "costprofitcentre.NotFound");
//        Preconditions.checkArgument(!costprofitcentre.getCode().equals(costprofitcentreDTO.getParent().getCode()), "costprofitcentre.verif-parent-node");
        costprofitcentre = CostProfitCentreFactory.costProfitCentreDTOToCostProfitCentreForUpdate(costprofitcentreDTO, costprofitcentre, costprofitcentreRepository);
        costprofitcentre = costprofitcentreRepository.save(costprofitcentre);
        CostProfitCentreDTO resultDTO = CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(costprofitcentre);
        return resultDTO;
    }

    /**
     * Get one costprofitcentreDTO by id.
     *
     * @param id the id of the entity
     * @return the entity DTO
     */
    @Transactional(
            readOnly = true
    )
    public CostProfitCentreDTO findOne(Integer id) {
        log.debug("Request to get CostProfitCentre: {}", id);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(id);
        Preconditions.checkArgument(costprofitcentre != null, "costprofitcentre.NotFound");
        CostProfitCentreDTO dto = CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(costprofitcentre);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public CostProfitCentre findCostProfitCentre(Integer id) {
        log.debug("Request to get CostProfitCentre: {}", id);
        CostProfitCentre costprofitcentre = costprofitcentreRepository.getReferenceById(id);
        Preconditions.checkArgument(costprofitcentre != null, "costprofitcentre.NotFound");
        return costprofitcentre;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findAll() {
        log.debug("Request to get All CostProfitCentres");
        List<CostProfitCentre> result = costprofitcentreRepository.findAll(Sort.by(ENTITY_NAME).ascending());

        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOs(result);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByActifIn(Collection<Boolean> actif) {
        log.debug("Request to get CostProfitCentres by actif ");
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByActifIn(actif);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    public void delete(Integer id) {
        log.debug("Request to delete CostProfitCentre: {}", id);
        Preconditions.checkArgument(costprofitcentreRepository.existsById(id) == true, "costprofitcentre.NotFound");
        costprofitcentreRepository.deleteById(id);
    }

    /**
     * Get find Last CodeSaisie.
     *
     * @return
     */
//    public String findLastCodeSaisie() {
//        return compteurService.findOne("codeSaisieCostProfitCentre").getValeur();
//    }
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByDetail(Collection<Boolean> detail) {
        log.debug("Request to findFilsActif by code pere :{} ", detail);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByDetailIn(detail);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    /**
     * Get costprofitcentres by actif .
     *
     * @param codes
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentreDTO> findByCodesIn(Collection<Integer> codes) {
        log.debug("Request to get CostProfitCentres by codes {}", (Object) codes);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByCodeIn(Helper.removeNullValueFromCollection(codes));
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    /**
     * Get costprofitcentres by actif .
     *
     * @param actif
     * @return the the list of entities
     */
    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findByActifInForEdition(Collection<Boolean> actif) {
        log.debug("Request to get findByActifInForedition by actif in ");
        return costprofitcentreRepository.findByActifIn(actif);
    }

    @Transactional(readOnly = true)
    public Collection<CostProfitCentreDTO> findByCodeSaisieBetween(String codeDu, String codeAu) {
        log.debug("Request to findByCodeBetween :{} and :{}  ", codeDu, codeAu);
        Collection<CostProfitCentre> result = costprofitcentreRepository.findByActifAndCodeSaisieBetween(true, codeDu, codeAu);
        return CostProfitCentreFactory.listCostProfitCentreToCostProfitCentreDTOsCollection(result);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findByActif(Boolean actif) {
        log.debug("Request to get CostProfitCentres by actif ");
        return costprofitcentreRepository.findByActif(actif);
    }

    @Transactional(
            readOnly = true
    )
    public Collection<CostProfitCentre> findAllCostProfitCentre() {
        log.debug("Request to get All CostProfitCentres");
        return costprofitcentreRepository.findAll();
    }

    public CostProfitCentreDTO save(CostProfitCentreDTO dto) {
        CostProfitCentre domaine = CostProfitCentreFactory.costProfitCentreDTOToCostProfitCentre(dto, new CostProfitCentre());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = costprofitcentreRepository.save(domaine);

        return CostProfitCentreFactory.costProfitCentreToCostProfitCentreDTO(domaine);
    }

}
