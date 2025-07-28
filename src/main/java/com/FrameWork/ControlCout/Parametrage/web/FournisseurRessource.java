/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.service.FournisseurService;
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
public class FournisseurRessource {
    private final FournisseurService fournisseurService;
    
    public FournisseurRessource(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    
    @GetMapping("fournisseur/{code}")
    public ResponseEntity<FournisseurDTO> getFournisseurByCode(@PathVariable Integer code) {
        FournisseurDTO dto = fournisseurService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("fournisseur/all")
    public ResponseEntity<List<FournisseurDTO>> getAllFournisseur() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(fournisseurService.findAllFournisseur());
    }
    
   @GetMapping("fournisseur/findByActif")
    public ResponseEntity<List<FournisseurDTO>> getFournisseurByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(fournisseurService.findAllFournisseurByActif(actif));
    }
      
   @GetMapping("fournisseur/findByActifAndGros")
    public ResponseEntity<List<FournisseurDTO>> getFournisseurByActifAndGros(@RequestParam Boolean actif,@RequestParam Boolean gros) {
        return ResponseEntity.ok().body(fournisseurService.findAllFournisseurByActifAndGros(actif,gros));
    }
  
    @PostMapping("fournisseur")
    public ResponseEntity<FournisseurDTO> postFournisseur(@Valid @RequestBody FournisseurDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FournisseurDTO result = fournisseurService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }
    
    @PutMapping("fournisseur/update")
    public ResponseEntity<Fournisseur> updateFournisseur(@RequestBody @Valid FournisseurDTO dto) throws URISyntaxException {
        Fournisseur result = fournisseurService.update(dto);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("fournisseur/delete/{Code}")
    public ResponseEntity<Fournisseur> deleteFournisseur(@PathVariable("Code") Integer code) {
        fournisseurService.deleteFournisseur(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
