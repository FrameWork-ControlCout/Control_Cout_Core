/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.domaine.TechCard;
import com.FrameWork.ControlCout.Cout.dto.TechCardDTO;
import com.FrameWork.ControlCout.Cout.service.TechCardService;
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
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/cout/")
public class TechCardRessource {
    
    private final TechCardService factureAchatService;
    
    public TechCardRessource(TechCardService factureAchatService) {
        this.factureAchatService = factureAchatService;
    }
    
    @GetMapping("tech_card/{code}")
    public ResponseEntity<TechCardDTO> getTechCardByCode(@PathVariable Integer code) {
        TechCardDTO dTO = factureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }
    
    @GetMapping("tech_card/all")
    public ResponseEntity<List<TechCardDTO>> getAllTechCard() {
        return ResponseEntity.ok().body(factureAchatService.findAllTechCard());
    }
    
    @GetMapping("tech_card/findByActif")
    public ResponseEntity<List<TechCardDTO>> getAllTechCardByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(factureAchatService.findAllTechCardByActif(actif));
    }
    
    @PostMapping("tech_card")
    public ResponseEntity<TechCardDTO> postTechCard(@Valid @RequestBody TechCardDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TechCardDTO result = factureAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/cout/" + result.getCode())).body(result);
    }
    
    @PutMapping("tech_card/update")
    public ResponseEntity<TechCardDTO> updateTechCard(@Valid @RequestBody TechCardDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        TechCardDTO result = factureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("tech_card/delete/{code}")
    public ResponseEntity<TechCard> deleteTechCard(@PathVariable("Code") Integer code) {
        factureAchatService.deleteTechCard(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
