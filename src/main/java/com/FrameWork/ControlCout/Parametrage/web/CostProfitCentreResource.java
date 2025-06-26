package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.dto.CostProfitCentreDTO;
import com.FrameWork.ControlCout.Parametrage.service.CostProfitCentreService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import jakarta.validation.Valid; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing CostProfitCentre.
 */
@RestController
@RequestMapping("/api/parametrage/")
public class CostProfitCentreResource {
 

    private final CostProfitCentreService costprofitcentreService;

    private final Logger log = LoggerFactory.getLogger(CostProfitCentreService.class);

    public CostProfitCentreResource(CostProfitCentreService costprofitcentreService) {
        this.costprofitcentreService = costprofitcentreService;
    }

    @PostMapping("cost_centre")
    public ResponseEntity<CostProfitCentreDTO> createCostProfitCentre(@Valid @RequestBody CostProfitCentreDTO costprofitcentreDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save CostProfitCentre : {}", costprofitcentreDTO);
      
        CostProfitCentreDTO result = costprofitcentreService.save(costprofitcentreDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }
 
    @PutMapping("cost_centre/update")
    public ResponseEntity<CostProfitCentreDTO> updateCostProfitCentre( @Valid @RequestBody CostProfitCentreDTO costprofitcentreDTO) throws MethodArgumentNotValidException {
        log.debug("Request to update CostProfitCentre: {}");
//        costprofitcentreDTO.setCode(code);
        CostProfitCentreDTO result = costprofitcentreService.update(costprofitcentreDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("cost_centre/{code}")
    public ResponseEntity<CostProfitCentreDTO> getCostProfitCentre(@PathVariable Integer code) {
        log.debug("Request to get CostProfitCentre: {}", code);
        CostProfitCentreDTO dto = costprofitcentreService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

 
      @GetMapping("cost_centre/all")
    public ResponseEntity<Collection<CostProfitCentreDTO>> getAllCostProfitCentres() {
        log.debug("Request to get all  CostProfitCentres : {}");
//        return costprofitcentreService.findAll();
         return ResponseEntity.ok().body(costprofitcentreService.findAll());
    }

 
    @DeleteMapping("cost_centre/delete/{Code}")
    public ResponseEntity<Void> deleteCostProfitCentre(@PathVariable Integer code) {
        log.debug("Request to delete CostProfitCentre: {}", code);
        costprofitcentreService.delete(code);
        return ResponseEntity.ok().build();
    }
 
    @GetMapping("cost_centre/actif")
    public ResponseEntity<Collection<CostProfitCentreDTO>> findByActifIn(@RequestParam(name = "Actif") Collection<Boolean> actif) {
        log.debug("Request to get  CostProfitCentres by actif : {}");
        return ResponseEntity.ok().body(costprofitcentreService.findByActifIn(actif));
    }
 
    @GetMapping("cost_centre/detail")
    public ResponseEntity<Collection<CostProfitCentreDTO>> findDetail(@RequestParam(name = "detail", required = true) Collection<Boolean> detail) {
        log.debug("Request to get  findFilsActif by code pere  : {}", detail);
        return ResponseEntity.ok().body(costprofitcentreService.findByDetail(detail));
    }
 
    @GetMapping("cost_centre/findByCodesIn")
    public ResponseEntity<Collection<CostProfitCentreDTO>> findByCodesIn(@RequestBody Collection<Integer> codes) {
        log.debug("Request to get  CostProfitCentres by codes in : {}", (Object) codes);
        return ResponseEntity.ok().body(costprofitcentreService.findByCodesIn(codes));
    }

    @GetMapping("cost_centre/findByCodeSaisieBetween")
    public ResponseEntity<Collection<CostProfitCentreDTO>> findByCodeSaisieBetween(@RequestParam String codeDu, @RequestParam String codeAu) {
        log.debug("Request to  findByCodeBetween  : {} and  : {} ", codeDu, codeAu);
        return ResponseEntity.ok().body(costprofitcentreService.findByCodeSaisieBetween(codeDu, codeAu));
    }
  

}
