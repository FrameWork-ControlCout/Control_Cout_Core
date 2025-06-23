/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.service.DetailsFactureAchatService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/achat/")
public class DetailsFactureAchatRessource {
    private final DetailsFactureAchatService detailsFactureAchatService;

    public DetailsFactureAchatRessource(DetailsFactureAchatService detailsFactureAchatService) {
        this.detailsFactureAchatService = detailsFactureAchatService;
    }
    
     @GetMapping("details_facture/{code}")
    public ResponseEntity<DetailsFactureAchatDTO> getDetailsFactureAchatByCode(@PathVariable Integer code) {
        DetailsFactureAchatDTO dTO = detailsFactureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_facture/all")
    public ResponseEntity<List<DetailsFactureAchatDTO>> getAllDetailsFactureAchat() {
        return ResponseEntity.ok().body(detailsFactureAchatService.findAllDetailsFactureAchat());
    }

  

   

//    @PostMapping("details_facture")
//    public ResponseEntity<DetailsFactureAchatDTO> postDetailsFactureAchat(@Valid @RequestBody DetailsFactureAchatDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
//        DetailsFactureAchatDTO result = detailsFactureAchatService.save(dTO);
//        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
//    }
    
     @PostMapping("details_facture/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsFactureAchatDTO> detailsFactureAchatDTOs) {
        List<DetailsFactureAchatDTO> result = detailsFactureAchatService.saveList(detailsFactureAchatDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
 
    @PutMapping("details_facture/update")
    public ResponseEntity<DetailsFactureAchatDTO> updateDetailsFactureAchat(@Valid @RequestBody DetailsFactureAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsFactureAchatDTO result = detailsFactureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
   

//    @DeleteMapping("details_facture/delete/{code}")
//    public ResponseEntity<DetailsFactureAchat> deleteDetailsFactureAchat(@PathVariable("Code") Integer code) {
//        detailsFactureAchatService.deleteDetailsFactureAchat(code);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    
    
}
