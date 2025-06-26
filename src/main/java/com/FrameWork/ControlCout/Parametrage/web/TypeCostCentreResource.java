/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.TypeCostCentre;
import com.FrameWork.ControlCout.Parametrage.dto.TypeCostCentreDTO;
import com.FrameWork.ControlCout.Parametrage.service.TypeCostCentreService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
 *
 * @author Administrateur
 */
@RestController
@RequestMapping("/api/parametrage/")
public class TypeCostCentreResource {

    private final TypeCostCentreService typeCostCentreService;

    private final Logger log = LoggerFactory.getLogger(TypeCostCentreService.class);

    public TypeCostCentreResource(TypeCostCentreService typeCostCentreService) {
        this.typeCostCentreService = typeCostCentreService;
    }

    @GetMapping("type_cost_centre/{id}")
    public ResponseEntity<TypeCostCentreDTO> getTypeCostCentre(@PathVariable Integer id) {
        log.debug("Request to get TypeCostCentre: {}", id);
        TypeCostCentreDTO typeCostCentreDTO = typeCostCentreService.findOne(id);
        return ResponseEntity.ok().body(typeCostCentreDTO);
    }

    @GetMapping("type_cost_centre/all")
    public ResponseEntity<Collection<TypeCostCentreDTO>> getAllTypeCostCentre() {
        log.debug("Request to get All TypeCostCentre: {}");
        Collection<TypeCostCentreDTO> typeCostCentreDTOs = typeCostCentreService.findAll();
        return ResponseEntity.ok().body(typeCostCentreDTOs);
    }

    @GetMapping("type_cost_centre/ByActif")
    public ResponseEntity<Collection<TypeCostCentreDTO>> getTypeCostCentreByActif(@RequestParam Collection<Boolean> actif) {
        log.debug("Request to get TypeCostCentre By Actif: {}", actif);
        Collection<TypeCostCentreDTO> typeCostCentreDTOs = typeCostCentreService.findByActifIn(actif);
        return ResponseEntity.ok().body(typeCostCentreDTOs);
    }

    @PostMapping("type_cost_centre")
    public ResponseEntity<TypeCostCentreDTO> postTypeCostCentre(@Valid @RequestBody TypeCostCentreDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeCostCentreDTO result = typeCostCentreService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_cost_centre/update")
    public ResponseEntity<TypeCostCentre> updateTypeCostCentre(@RequestBody @Valid TypeCostCentreDTO dto) throws URISyntaxException {
        TypeCostCentre result = typeCostCentreService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_cost_centre/delete/{Code}")
    public ResponseEntity<TypeCostCentre> deleteTypeCostCentre(@PathVariable("Code") Integer code) {
        typeCostCentreService.deleteTypeCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
