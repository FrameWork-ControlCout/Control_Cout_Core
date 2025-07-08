/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.domaine.CalculeConsommation;
import com.FrameWork.ControlCout.Cout.dto.CalculeConsommationDTO;
import com.FrameWork.ControlCout.Cout.service.CalculeConsommationService;
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
public class CalculeConsommationRessource {
    
    private final CalculeConsommationService factureAchatService;
    
    public CalculeConsommationRessource(CalculeConsommationService factureAchatService) {
        this.factureAchatService = factureAchatService;
    }
    
    @GetMapping("calcul_consommation/{code}")
    public ResponseEntity<CalculeConsommationDTO> getCalculeConsommationByCode(@PathVariable Integer code) {
        CalculeConsommationDTO dTO = factureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }
    
    @GetMapping("calcul_consommation/all")
    public ResponseEntity<List<CalculeConsommationDTO>> getAllCalculeConsommation() {
        return ResponseEntity.ok().body(factureAchatService.findAllCalculeConsommation());
    }
    
    @GetMapping("calcul_consommation/findByActif")
    public ResponseEntity<List<CalculeConsommationDTO>> getAllCalculeConsommationByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(factureAchatService.findAllCalculeConsommationByActif(actif));
    }
    
    @PostMapping("calcul_consommation")
    public ResponseEntity<CalculeConsommationDTO> postCalculeConsommation(@Valid @RequestBody CalculeConsommationDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CalculeConsommationDTO result = factureAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/cout/" + result.getCode())).body(result);
    }
    
    @PutMapping("calcul_consommation/update")
    public ResponseEntity<CalculeConsommationDTO> updateCalculeConsommation(@Valid @RequestBody CalculeConsommationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        CalculeConsommationDTO result = factureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("calcul_consommation/delete/{Code}")
    public ResponseEntity<CalculeConsommation> deleteCalculeConsommation(@PathVariable("Code") Integer code) {
        factureAchatService.deleteCalculeConsommation(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
