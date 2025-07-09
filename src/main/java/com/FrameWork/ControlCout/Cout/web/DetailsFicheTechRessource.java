/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.dto.DetailsFicheTechDTO;
import com.FrameWork.ControlCout.Cout.service.DetailsFicheTechService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class DetailsFicheTechRessource {

    private final DetailsFicheTechService detailsFicheTechniqueService;

    public DetailsFicheTechRessource(DetailsFicheTechService detailsFicheTechniqueService) {
        this.detailsFicheTechniqueService = detailsFicheTechniqueService;
    }

    @GetMapping("details_fiche_technique/{code}")
    public ResponseEntity<DetailsFicheTechDTO> getDetailsFicheTechniqueByCode(@PathVariable Integer code) {
        DetailsFicheTechDTO dTO = detailsFicheTechniqueService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_fiche_technique/findByCodeFicheTechnique")
    public ResponseEntity<List<DetailsFicheTechDTO>> getDetailsFicheTechniqueByCodeFacture(@RequestParam Integer codeFicheTechnique) {
        List<DetailsFicheTechDTO> dTO = detailsFicheTechniqueService.findByCodeFicheTechnique(codeFicheTechnique);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_fiche_technique/all")
    public ResponseEntity<List<DetailsFicheTechDTO>> getAllDetailsFicheTechnique() {
        return ResponseEntity.ok().body(detailsFicheTechniqueService.findAllDetailsFicheTechnique());
    }

    @PostMapping("details_fiche_technique/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsFicheTechDTO> detailsFicheTechniqueDTOs) {
        List<DetailsFicheTechDTO> result = detailsFicheTechniqueService.saveList(detailsFicheTechniqueDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_fiche_technique/update")
    public ResponseEntity<DetailsFicheTechDTO> updateDetailsFicheTechnique(@Valid @RequestBody DetailsFicheTechDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsFicheTechDTO result = detailsFicheTechniqueService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

}
