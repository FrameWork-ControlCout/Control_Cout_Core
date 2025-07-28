/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsFactureGrosDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.DetailsFactureGrosService;
import com.FrameWork.ControlCout.Achat.service.DetailsOrderAchatService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/achat/")
public class DetailsFactureGrosRessource {

    private final DetailsFactureGrosService detailsFactureGrosService; 

    public DetailsFactureGrosRessource(DetailsFactureGrosService detailsFactureGrosService) {
        this.detailsFactureGrosService = detailsFactureGrosService;
    }

    

    @GetMapping("details_facture_gros/{code}")
    public ResponseEntity<DetailsFactureGrosDTO> getDetailsFactureGrosByCode(@PathVariable Integer code) {
        DetailsFactureGrosDTO dTO = detailsFactureGrosService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }


    @GetMapping("details_facture_gros/findByCodeFactureGros")
    public ResponseEntity<List<DetailsFactureGrosDTO>> getDetailsFactureGrosByCodeFactureGros(@RequestParam Integer codeFactureGros) {
        List<DetailsFactureGrosDTO> dTO = detailsFactureGrosService.findByCodeFactureGros(codeFactureGros);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_facture_gros/all")
    public ResponseEntity<List<DetailsFactureGrosDTO>> getAllDetailsFactureGros() {
        return ResponseEntity.ok().body(detailsFactureGrosService.findAllDetailsFactureGros());
    }

//    @PostMapping("details_facture_gros/List")
//    public ResponseEntity<String> saveList(@RequestBody List<DetailsFactureGrosDTO> detailsFactureGrosDTOs) {
//        List<DetailsFactureGrosDTO> result = detailsFactureGrosService.saveList(detailsFactureGrosDTOs);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PutMapping("details_facture_gros/update")
    public ResponseEntity<DetailsFactureGrosDTO> updateDetailsFactureGros(@Valid @RequestBody DetailsFactureGrosDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsFactureGrosDTO result = detailsFactureGrosService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
}
