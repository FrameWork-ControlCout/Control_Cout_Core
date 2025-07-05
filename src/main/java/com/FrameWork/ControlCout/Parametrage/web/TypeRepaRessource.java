/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.TypeRepa;
import com.FrameWork.ControlCout.Parametrage.dto.TypeRepaDTO;
import com.FrameWork.ControlCout.Parametrage.service.TypeRepaService;
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
@RequestMapping("/api/parametrage/")
public class TypeRepaRessource {
    private final TypeRepaService typeRepaService;
    
    public TypeRepaRessource(TypeRepaService typeRepaService) {
        this.typeRepaService = typeRepaService;
    }
    
    @GetMapping("type_repa/{code}")
    public ResponseEntity<TypeRepaDTO> getTypeRepaByCode(@PathVariable Integer code) {
        TypeRepaDTO dto = typeRepaService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("type_repa/all")
    public ResponseEntity<List<TypeRepaDTO>> getAllTypeRepa() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(typeRepaService.findAllTypeRepa());
    }
    
   @GetMapping("type_repa/findByActif")
    public ResponseEntity<List<TypeRepaDTO>> getTypeRepaByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(typeRepaService.findAllTypeRepaByActif(actif));
    }
    
 
    
    
    
  
    @PostMapping("type_repa")
    public ResponseEntity<TypeRepaDTO> postTypeRepa(@Valid @RequestBody TypeRepaDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeRepaDTO result = typeRepaService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }
    
    @PutMapping("type_repa/update")
    public ResponseEntity<TypeRepa> updateTypeRepa(@RequestBody @Valid TypeRepaDTO dto) throws URISyntaxException {
        TypeRepa result = typeRepaService.update(dto);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("type_repa/delete/{Code}")
    public ResponseEntity<TypeRepa> deleteTypeRepa(@PathVariable("Code") Integer code) {
        typeRepaService.deleteTypeRepa(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
