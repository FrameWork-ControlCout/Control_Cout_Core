/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Etat;
import com.FrameWork.ControlCout.Parametrage.dto.EtatDTO;
import com.FrameWork.ControlCout.Parametrage.service.EtatService;
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
@RequestMapping("/api/parametrage/")
public class EtatRessource {
    private final EtatService etatFactureService;
    
    public EtatRessource(EtatService etatFactureService) {
        this.etatFactureService = etatFactureService;
    }
    
    @GetMapping("etat/{code}")
    public ResponseEntity<EtatDTO> getEtatFactureByCode(@PathVariable Integer code) {
        EtatDTO dto = etatFactureService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("etat/all")
    public ResponseEntity<List<EtatDTO>> getAllEtatFacture() { 
        return ResponseEntity.ok().body(etatFactureService.findAllEtatFacture());
    }
    
 
    
   
    
 
    
  
}
