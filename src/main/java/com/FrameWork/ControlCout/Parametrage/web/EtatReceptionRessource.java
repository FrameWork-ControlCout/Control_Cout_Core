/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.dto.EtatReceptionDTO;
import com.FrameWork.ControlCout.Parametrage.service.EtatReceptionService;
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
public class EtatReceptionRessource {
    private final EtatReceptionService etapReceptionService;
    
    public EtatReceptionRessource(EtatReceptionService etapReceptionService) {
        this.etapReceptionService = etapReceptionService;
    }
    
    @GetMapping("etat_reception/{code}")
    public ResponseEntity<EtatReceptionDTO> getEtapReceptionByCode(@PathVariable Integer code) {
        EtatReceptionDTO dto = etapReceptionService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("etat_reception/all")
    public ResponseEntity<List<EtatReceptionDTO>> getAllEtapReception() { 
        return ResponseEntity.ok().body(etapReceptionService.findAllEtapReception());
    }
    
 
    
   
    
 
    
  
}
