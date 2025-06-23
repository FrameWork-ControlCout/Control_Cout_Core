/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import com.FrameWork.ControlCout.Parametrage.service.UniteService;
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
public class UniteRessource {
    private final UniteService uniteService;
    
    public UniteRessource(UniteService uniteService) {
        this.uniteService = uniteService;
    }
    
    @GetMapping("unite/{code}")
    public ResponseEntity<UniteDTO> getUniteByCode(@PathVariable Integer code) {
        UniteDTO dto = uniteService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("unite/all")
    public ResponseEntity<List<UniteDTO>> getAllUnite() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(uniteService.findAllUnite());
    }
    
   @GetMapping("unite/findByActif")
    public ResponseEntity<List<UniteDTO>> getUniteByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(uniteService.findAllUniteByActif(actif));
    }
    
  
    @PostMapping("unite")
    public ResponseEntity<UniteDTO> postUnite(@Valid @RequestBody UniteDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        UniteDTO result = uniteService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }
    
    @PutMapping("unite/update")
    public ResponseEntity<Unite> updateUnite(@RequestBody @Valid UniteDTO dto) throws URISyntaxException {
        Unite result = uniteService.update(dto);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("unite/delete/{Code}")
    public ResponseEntity<Unite> deleteUnite(@PathVariable("Code") Integer code) {
        uniteService.deleteUnite(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
