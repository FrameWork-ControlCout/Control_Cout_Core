/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsFactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.DetailsFactureBonReceptionService;
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
public class DetailsFactureBonReceptionRessource {

    private final DetailsFactureBonReceptionService detailsFactureBonReceptionService; 

    public DetailsFactureBonReceptionRessource(DetailsFactureBonReceptionService detailsFactureBonReceptionService) {
        this.detailsFactureBonReceptionService = detailsFactureBonReceptionService;
    }

    

    @GetMapping("details_facture_bon_reception/{code}")
    public ResponseEntity<DetailsFactureBonReceptionDTO> getDetailsFactureBonReceptionByCode(@PathVariable Integer code) {
        DetailsFactureBonReceptionDTO dTO = detailsFactureBonReceptionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }


    @GetMapping("details_facture_bon_reception/findByCodeFactureBonReception")
    public ResponseEntity<List<DetailsFactureBonReceptionDTO>> getDetailsFactureBonReceptionByCodeFactureBonReception(@RequestParam Integer codeFactureBonReception) {
        List<DetailsFactureBonReceptionDTO> dTO = detailsFactureBonReceptionService.findByCodeFactureBonReception(codeFactureBonReception);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_facture_bon_reception/all")
    public ResponseEntity<List<DetailsFactureBonReceptionDTO>> getAllDetailsFactureBonReception() {
        return ResponseEntity.ok().body(detailsFactureBonReceptionService.findAllDetailsFactureBonReception());
    }
 

    @PutMapping("details_facture_bon_reception/update")
    public ResponseEntity<DetailsFactureBonReceptionDTO> updateDetailsFactureBonReception(@Valid @RequestBody DetailsFactureBonReceptionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsFactureBonReceptionDTO result = detailsFactureBonReceptionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
}
