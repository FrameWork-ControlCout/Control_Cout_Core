/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.EtatFacture;
import com.FrameWork.ControlCout.Parametrage.dto.EtatFactureDTO;
import com.FrameWork.ControlCout.Parametrage.service.EtatFactureService;
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
@RequestMapping("/api/parametrage_achat/")
public class EtatFactureRessource {
    private final EtatFactureService etatFactureService;
    
    public EtatFactureRessource(EtatFactureService etatFactureService) {
        this.etatFactureService = etatFactureService;
    }
    
    @GetMapping("etat_facture/{code}")
    public ResponseEntity<EtatFactureDTO> getEtatFactureByCode(@PathVariable Integer code) {
        EtatFactureDTO dto = etatFactureService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("etat_facture/all")
    public ResponseEntity<List<EtatFactureDTO>> getAllEtatFacture() { 
        return ResponseEntity.ok().body(etatFactureService.findAllEtatFacture());
    }
    
 
    
   
    
 
    
  
}
