/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;

import com.FrameWork.ControlCout.Tresorerie.domaine.TypeCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.TypeCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.service.TypeCaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
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
public class TypeCaisseRessource {
    
     private final TypeCaisseService typeCaisseService;

    public TypeCaisseRessource(TypeCaisseService typeCaisseService) {
        this.typeCaisseService = typeCaisseService;
    }

    @GetMapping("type_caisse/{code}")
    public ResponseEntity<TypeCaisseDTO> getTypeCaisseByCode(@PathVariable Integer code) {
        TypeCaisseDTO dto = typeCaisseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

  
    @GetMapping("type_caisse/all")
    public ResponseEntity<List<TypeCaisseDTO>> getAllTypeCaisse() { 
        return ResponseEntity.ok().body(typeCaisseService.findAllTypeCaisse());
    }

    @PostMapping("type_caisse")
    public ResponseEntity<TypeCaisseDTO> postTypeCaisse(@Valid @RequestBody TypeCaisseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        TypeCaisseDTO result = typeCaisseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("type_caisse/update")
    public ResponseEntity<TypeCaisse> updateTypeCaisse(@RequestBody @Valid TypeCaisseDTO dto) throws URISyntaxException {
        TypeCaisse result = typeCaisseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("type_caisse/delete/{Code}")
    public ResponseEntity<TypeCaisse> deleteTypeCaisse(@PathVariable("Code") Integer code) {
        typeCaisseService.deleteTypeCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
