/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;

import com.FrameWork.ControlCout.Tresorerie.domaine.TypeRecette;
import com.FrameWork.ControlCout.Tresorerie.dto.TypeRecetteDTO;
import com.FrameWork.ControlCout.Tresorerie.service.TypeRecetteService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/recette/")
public class TypeRecetteRessource {
     private final TypeRecetteService typeRecetteService;

    public TypeRecetteRessource(TypeRecetteService typeRecetteService) {
        this.typeRecetteService = typeRecetteService;
    }

    @GetMapping("type_recette/{code}")
    public ResponseEntity<TypeRecetteDTO> getTypeRecetteByCode(@PathVariable Integer code) {
        TypeRecetteDTO dto = typeRecetteService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("type_recette/all")
    public ResponseEntity<List<TypeRecetteDTO>> getAllTypeRecette() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(typeRecetteService.findAllTypeRecette());
    }

    @PostMapping("type_recette")
    public ResponseEntity<TypeRecetteDTO> postTypeRecette(@Valid @RequestBody TypeRecetteDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeRecetteDTO result = typeRecetteService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_recette/update")
    public ResponseEntity<TypeRecette> updateTypeRecette(@RequestBody @Valid TypeRecetteDTO dto) throws URISyntaxException {
        TypeRecette result = typeRecetteService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_recette/delete/{Code}")
    public ResponseEntity<TypeRecette> deleteTypeRecette(@PathVariable("Code") Integer code) {
        typeRecetteService.deleteTypeRecette(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
