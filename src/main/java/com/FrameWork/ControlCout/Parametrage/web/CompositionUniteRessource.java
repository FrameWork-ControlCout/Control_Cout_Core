/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.CompositionUnite;
import com.FrameWork.ControlCout.Parametrage.dto.CompositionUniteDTO;
import com.FrameWork.ControlCout.Parametrage.service.CompositionUniteService;
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
public class CompositionUniteRessource {
     private final CompositionUniteService compositionUniteService;

    public CompositionUniteRessource(CompositionUniteService compositionUniteService) {
        this.compositionUniteService = compositionUniteService;
    }

    @GetMapping("composition_unite/{code}")
    public ResponseEntity<CompositionUniteDTO> getCompositionUniteByCode(@PathVariable Integer code) {
        CompositionUniteDTO dto = compositionUniteService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("composition_unite/findByUnitePrincipal")
    public ResponseEntity<CompositionUniteDTO> getCompositionUniteByUnitePrincipal(@RequestParam Integer codeUnitePrincipal) {
        return ResponseEntity.ok().body(compositionUniteService.findByCodeUnitePrincipal(codeUnitePrincipal));
    }

    @GetMapping("composition_unite/all")
    public ResponseEntity<List<CompositionUniteDTO>> getAllCompositionUnite() {
        return ResponseEntity.ok().body(compositionUniteService.findAllCompositionUnite());
    }

    @PostMapping("composition_unite")
    public ResponseEntity<CompositionUniteDTO> postCompositionUnite(@Valid @RequestBody CompositionUniteDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CompositionUniteDTO result = compositionUniteService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("composition_unite/update")
    public ResponseEntity<CompositionUnite> updateCompositionUnite(@RequestBody @Valid CompositionUniteDTO dto) throws URISyntaxException {
        CompositionUnite result = compositionUniteService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("composition_unite/delete")
    public ResponseEntity<CompositionUnite> deleteCompositionUnite(@RequestParam Integer codeUnitePricipal) {
        compositionUniteService.deleteCompositionUniteByCodeUniPrinc(codeUnitePricipal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
