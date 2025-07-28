/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.dto.EtatPaiementDTO;
import com.FrameWork.ControlCout.Parametrage.service.EtatPaiementService;
import java.util.List; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class EtatPaiementRessource {
    private final EtatPaiementService etatPaiementService;
    
    public EtatPaiementRessource(EtatPaiementService etatPaiementService) {
        this.etatPaiementService = etatPaiementService;
    }
    
    @GetMapping("etat_paiement/{code}")
    public ResponseEntity<EtatPaiementDTO> getEtapReceptionByCode(@PathVariable Integer code) {
        EtatPaiementDTO dto = etatPaiementService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("etat_paiement/all")
    public ResponseEntity<List<EtatPaiementDTO>> getAllEtapReception() { 
        return ResponseEntity.ok().body(etatPaiementService.findAllEtapReception());
    }
    
 
    
   
    
 
    
  
}
