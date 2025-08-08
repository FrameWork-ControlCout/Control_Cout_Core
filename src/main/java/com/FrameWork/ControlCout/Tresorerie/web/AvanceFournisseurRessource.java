/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;

import com.FrameWork.ControlCout.Tresorerie.domaine.AvanceFournisseur;
import com.FrameWork.ControlCout.Tresorerie.dto.AvanceFournisseurDTO;
import com.FrameWork.ControlCout.Tresorerie.service.AvanceFournisseurService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
@RequestMapping("/api/depense/")
public class AvanceFournisseurRessource {
    
    private final AvanceFournisseurService avanceFournisseurService;

    public AvanceFournisseurRessource(AvanceFournisseurService avanceFournisseurService) {
        this.avanceFournisseurService = avanceFournisseurService;
    }
     @GetMapping("avance_fournisseur/{code}")
    public ResponseEntity<AvanceFournisseurDTO> getMouvementCaisseByCode(@PathVariable Integer code) {
        AvanceFournisseurDTO dTO = avanceFournisseurService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("avance_fournisseur/caisse/findByCodeCaisse")
    public ResponseEntity<Collection<AvanceFournisseurDTO>> getMouvementCaisseByCodeCaisse(@PathVariable Collection<Integer> codeCaisse) {
        Collection<AvanceFournisseurDTO> dTO = avanceFournisseurService.findByCodeCaisse(codeCaisse);
        return ResponseEntity.ok().body(dTO);
    }
    
        @GetMapping("avance_fournisseur/caisse/findByCodeDevise")
    public ResponseEntity<Collection<AvanceFournisseurDTO>> getMouvementCaisseByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<AvanceFournisseurDTO> dTO = avanceFournisseurService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTO);
    }
    
           @GetMapping("avance_fournisseur/caisse/findByCodeFournisseur")
    public ResponseEntity<List<AvanceFournisseurDTO>> getMouvementCaisseByCodeFournisseur(@RequestParam Integer codeFournisseur) {
        List<AvanceFournisseurDTO> dTO = avanceFournisseurService.findByCodeFournisseur(codeFournisseur);
        return ResponseEntity.ok().body(dTO);
    }


    @GetMapping("avance_fournisseur/all")
    public ResponseEntity<List<AvanceFournisseurDTO>> getAllMouvementCaisse() {
        return ResponseEntity.ok().body(avanceFournisseurService.findAllAvanceFournisseur());
    }

 
    @PostMapping("avance_fournisseur")
    public ResponseEntity<AvanceFournisseurDTO> postAvnaceFournisseur(@Valid @RequestBody AvanceFournisseurDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        AvanceFournisseurDTO result = avanceFournisseurService.save(dTO);
        return ResponseEntity.created(new URI("/api/recette/" + result.getCode())).body(result);
    }

       @PutMapping("avance_fournisseur/update")
    public ResponseEntity<AvanceFournisseurDTO> updateAvanceFournisseur(@Valid @RequestBody AvanceFournisseurDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        AvanceFournisseurDTO result = avanceFournisseurService.update(dTO);
        return ResponseEntity.ok().body(result);
    }
      @DeleteMapping("avance_fournisseur/delete/{code}")
    public ResponseEntity<AvanceFournisseur> deleteAvanceFournisseur(@PathVariable("Code") Integer code) {
        avanceFournisseurService.deleteAvanceFournisseur(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
