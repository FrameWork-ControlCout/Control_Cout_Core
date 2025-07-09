/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.domaine.FicheTech;
import com.FrameWork.ControlCout.Cout.dto.FicheTechDTO;
import com.FrameWork.ControlCout.Cout.service.FicheTechService;
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
public class FicheTechRessource {
    
    private final FicheTechService ficheTechniqueService;
    
    public FicheTechRessource(FicheTechService ficheTechniqueService) {
        this.ficheTechniqueService = ficheTechniqueService;
    }
    
    @GetMapping("fiche_technique/{code}")
    public ResponseEntity<FicheTechDTO> getFicheTechniqueByCode(@PathVariable Integer code) {
        FicheTechDTO dTO = ficheTechniqueService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }
    
    @GetMapping("fiche_technique/all")
    public ResponseEntity<List<FicheTechDTO>> getAllFicheTechnique() {
        return ResponseEntity.ok().body(ficheTechniqueService.findAllFicheTechnique());
    }
    
    @GetMapping("fiche_technique/findByActif")
    public ResponseEntity<List<FicheTechDTO>> getAllFicheTechniqueByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(ficheTechniqueService.findAllFicheTechniqueByActif(actif));
    }
    
    @PostMapping("fiche_technique")
    public ResponseEntity<FicheTechDTO> postFicheTechnique(@Valid @RequestBody FicheTechDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FicheTechDTO result = ficheTechniqueService.save(dTO);
        return ResponseEntity.created(new URI("/api/cout/" + result.getCode())).body(result);
    }
    
    @PutMapping("fiche_technique/update")
    public ResponseEntity<FicheTechDTO> updateFicheTechnique(@Valid @RequestBody FicheTechDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FicheTechDTO result = ficheTechniqueService.update(dTO);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("fiche_technique/delete/{Code}")
    public ResponseEntity<FicheTech> deleteFicheTechnique(@PathVariable("Code") Integer code) {
        ficheTechniqueService.deleteFicheTechnique(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
