/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;

import com.FrameWork.ControlCout.Tresorerie.dto.EtatApprouverDTO;
import com.FrameWork.ControlCout.Tresorerie.service.EtatApprouverService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/api/recette/")
public class EtatApprouveRessource {

    private final EtatApprouverService etatApprouverService;

    public EtatApprouveRessource(EtatApprouverService etatApprouverService) {
        this.etatApprouverService = etatApprouverService;
    }

    
     @GetMapping("etat_approuve/all")
    public ResponseEntity<List<EtatApprouverDTO>> getAllEtatApprouve() {
        return ResponseEntity.ok().body(etatApprouverService.findAllEtatApprouve());
    }

}
