/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.ComparDetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.service.ComparDetailsFactureAchatService;
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
@RequestMapping("/api/achat/")
public class ComparDetailsFactureAchatRessource {
    private final ComparDetailsFactureAchatService compardetailsFactureAchatService;

    public ComparDetailsFactureAchatRessource(ComparDetailsFactureAchatService compardetailsFactureAchatService) {
        this.compardetailsFactureAchatService = compardetailsFactureAchatService;
    }
 
    
    
     @GetMapping("details_compar_facture_achat/{code}")
    public ResponseEntity<ComparDetailsFactureAchatDTO> getDetailsComparFactureAchatByCode(@PathVariable Integer code) {
        ComparDetailsFactureAchatDTO dTO = compardetailsFactureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    
       
     @GetMapping("details_compar_facture_achat/findByCodeComparFacture")
    public ResponseEntity<List<ComparDetailsFactureAchatDTO>> getDetailsComparFactureAchatByCodeComparFacture(@RequestParam Integer codeComparFacture) {
        List<ComparDetailsFactureAchatDTO> dTO = compardetailsFactureAchatService.findByCodeComparFactureAchat(codeComparFacture);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_compar_facture_achat/all")
    public ResponseEntity<List<ComparDetailsFactureAchatDTO>> getAllDetailsFactureAchat() {
        return ResponseEntity.ok().body(compardetailsFactureAchatService.findAllComparDetailsFactureAchat());
    }

  

 
     @PostMapping("details_compar_facture_achat/List")
    public ResponseEntity<String> saveList(@RequestBody List<ComparDetailsFactureAchatDTO> detailsFactureAchatDTOs) {
        List<ComparDetailsFactureAchatDTO> result = compardetailsFactureAchatService.saveList(detailsFactureAchatDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
 
    @PutMapping("details_compar_facture_achat/update")
    public ResponseEntity<ComparDetailsFactureAchatDTO> updateDetailsComparFactureAchat(@Valid @RequestBody ComparDetailsFactureAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ComparDetailsFactureAchatDTO result = compardetailsFactureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
    
    
    
}
