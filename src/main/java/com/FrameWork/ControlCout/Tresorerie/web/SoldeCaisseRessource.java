/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;
 
import com.FrameWork.ControlCout.Tresorerie.dto.SoldeCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.service.SoldeCaisseService;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class SoldeCaisseRessource {

    private final SoldeCaisseService soldeCaisseService;

    public SoldeCaisseRessource(SoldeCaisseService soldeCaisseService) {
        this.soldeCaisseService = soldeCaisseService;
    }

    @GetMapping("solde_caisse/{code}")
    public ResponseEntity<SoldeCaisseDTO> getSoldeCaisseByCode(@PathVariable Integer code) {
        SoldeCaisseDTO dTO = soldeCaisseService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("solde_caisse/all")
    public ResponseEntity<List<SoldeCaisseDTO>> getAllSoldeCaisse() {
        return ResponseEntity.ok().body(soldeCaisseService.findAllSoldeCaisse());
    }

    @GetMapping("solde_caisse/codeDevise")
    public ResponseEntity<Collection<SoldeCaisseDTO>> getSoldeCaisseByCodeDevise(@RequestParam Collection<Integer> codeDevise) {
        Collection<SoldeCaisseDTO> dTOs = soldeCaisseService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("solde_caisse/ByCodeAndCodeDevise")
    public ResponseEntity<List<SoldeCaisseDTO>> getSoldeCaisseByCodeDeviseAndCode(@RequestParam Integer codeDevise, Integer code) {
        List<SoldeCaisseDTO> dTOs = soldeCaisseService.findByCodeDeviseAndCode(codeDevise, code);
        return ResponseEntity.ok().body(dTOs);
    }

    @GetMapping("solde_caisse/code_caisse")
    public ResponseEntity<SoldeCaisseDTO> getSoldeCaisseByCodeCaisse(@RequestParam Integer codeCaisse) {
        SoldeCaisseDTO dTOs = soldeCaisseService.findByCodeCaisse(codeCaisse);
        return ResponseEntity.ok().body(dTOs);
    }

}
