/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.TauxDeChange;
import com.FrameWork.ControlCout.Parametrage.dto.TauxDeChangeDTO;
import com.FrameWork.ControlCout.Parametrage.service.TauxDeChangeService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List; 
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class TauxDeChangeRessource {

    private final TauxDeChangeService tauxDeChangeService;

    public TauxDeChangeRessource(TauxDeChangeService tauxDeChangeService) {
        this.tauxDeChangeService = tauxDeChangeService;
    }

    @GetMapping("taux_change/{code}")
    public ResponseEntity<TauxDeChangeDTO> getTauxDeChangeByCode(@PathVariable Integer code) {
        TauxDeChangeDTO dto = tauxDeChangeService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("taux_change/code_devise/{codeDevise}")
    public ResponseEntity<TauxDeChangeDTO> getTauxDeChangeByCodeDevise(@PathVariable Integer codeDevise) {
        TauxDeChangeDTO dto = tauxDeChangeService.findOneByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("taux_change/all")
    public ResponseEntity<List<TauxDeChangeDTO>> getAllTauxDeChange() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(tauxDeChangeService.findAllTauxDeChange());
    }

    @PostMapping("taux_change")
    public ResponseEntity<TauxDeChangeDTO> postTauxDeChange(@Valid @RequestBody TauxDeChangeDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TauxDeChangeDTO result = tauxDeChangeService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("taux_change/update")
    public ResponseEntity<TauxDeChange> updateTauxDeChange(@RequestBody @Valid TauxDeChangeDTO dto) throws URISyntaxException {
        TauxDeChange result = tauxDeChangeService.update(dto);
        return ResponseEntity.ok().body(result);
    }
 
}
