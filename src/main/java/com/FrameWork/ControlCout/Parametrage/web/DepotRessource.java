/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.dto.DepotDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.service.DepotService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class DepotRessource {

    private final DepotService depotService;

    public DepotRessource(DepotService depotService) {
        this.depotService = depotService;
    }

    @GetMapping("depot/{code}")
    public ResponseEntity<DepotDTO> getDepotByCode(@PathVariable Integer code) {
        DepotDTO dto = depotService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("depot/findByActif")
    public ResponseEntity<List<DepotDTO>> getDepotByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(depotService.findAllDepotByActif(actif));
    }
  @GetMapping("depot/findByActifAndAutoriseRecep")
    public ResponseEntity<List<DepotDTO>> getDepotByActifAndAutoriseRecep(@RequestParam Boolean actif,@RequestParam Boolean autoriseRecep) {
        return ResponseEntity.ok().body(depotService.findAllDepotByActifAndAutosieRecep(actif,autoriseRecep));
    }
    @GetMapping("depot/all")
    public ResponseEntity<List<DepotDTO>> getAllDepot() {
        return ResponseEntity.ok().body(depotService.findAllDepot());
    }

    @PostMapping("depot")
    public ResponseEntity<DepotDTO> postDepot(@Valid @RequestBody DepotDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        DepotDTO result = depotService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("depot/update")
    public ResponseEntity<Depot> updateDepot(@RequestBody @Valid DepotDTO dto) throws URISyntaxException {
        Depot result = depotService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("depot/delete/{Code}")
    public ResponseEntity<Depot> deleteDepot(@PathVariable("Code") Integer code) {
        depotService.deleteDepot(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
