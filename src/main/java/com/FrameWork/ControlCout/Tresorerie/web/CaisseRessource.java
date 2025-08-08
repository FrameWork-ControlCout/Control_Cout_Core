/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.web;

import com.FrameWork.ControlCout.Tresorerie.domaine.Caisse;
import com.FrameWork.ControlCout.Tresorerie.dto.CaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.service.CaisseService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@Slf4j
@RestController
@RequestMapping("/api/recette/")
public class CaisseRessource {

    private final CaisseService caisseService;

    public CaisseRessource(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @GetMapping("caisse/{code}")
    public ResponseEntity<CaisseDTO> getCaisseByCode(@PathVariable Integer code) {
        CaisseDTO dto = caisseService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("caisse/all")
    public ResponseEntity<List<CaisseDTO>> getAllCaisse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
//        return ResponseEntity.ok().body(caisseService.findAllCaisse());
        return new ResponseEntity<>(caisseService.findAllCaisse(), headers, HttpStatus.OK);
    }

//    @GetMapping("caisse/not_in{Code}")
//    public ResponseEntity<List<CaisseDTO>> getAllCaisseNotIn( @RequestParam("Code") Integer code ,@RequestParam("codeDevise") Integer codeDevise   ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON); 
//        return new ResponseEntity<>(caisseService.findByCodeNotInAndCodeDevise(code,codeDevise), headers, HttpStatus.OK);
//    }
    @GetMapping("caisse/not_in")
    public ResponseEntity<List<Caisse>> getAllCaisseByTypeCaisse(@RequestParam("Code") Integer code, @RequestParam("codeDevise") Integer codeDevise) {
        return ResponseEntity.ok().body(caisseService.findByCodeNotInAndCodeDevise(code, codeDevise));
    }
//
//    @GetMapping("caisse/type_caisse/{codeTypeCaisse}")
//    public ResponseEntity<List<Caisse>> getAllCaisseByTypeCaisse(@PathVariable("codeTypeCaisse") Integer codeTypeCaisse) {
//        return ResponseEntity.ok().body(caisseService.findByCodeTypeCaisse(codeTypeCaisse));
//    }
    @GetMapping("caisse/type_caisse/findByTypeCaisse")
    public ResponseEntity<List<CaisseDTO>> getAllCaisseByTypeCaisse(@RequestParam String type) {
        List<CaisseDTO> dTOs = caisseService.findByTypeCaisse(type);
        return ResponseEntity.ok().body(dTOs);
    }
    
    

//        @GetMapping("caisse/codeDevise")
//    public ResponseEntity<List<Caisse>> getAllCaisseByCodeDevise(@RequestParam Integer codeDevise) {
//        return ResponseEntity.ok().body(caisseService.findByCodeTypeCaisse(codeDevise));
//    }
    @GetMapping("caisse/codeDevise")
    public ResponseEntity<List<CaisseDTO>> getAllCaisseByCodeDevise(@RequestParam Integer codeDevise) {
        List<CaisseDTO> dTOs = caisseService.findByCodeDevise(codeDevise);
        return ResponseEntity.ok().body(dTOs);
    }

    @PostMapping("caisse")
    public ResponseEntity<CaisseDTO> postCaisse(@Valid @RequestBody CaisseDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        CaisseDTO result = caisseService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("caisse/update")
    public ResponseEntity<Caisse> updateCaisse(@RequestBody @Valid CaisseDTO dto) throws URISyntaxException {
        Caisse result = caisseService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("caisse/delete/{Code}")
    public ResponseEntity<Caisse> deleteCaisse(@PathVariable("Code") Integer code) {

//         log.info("Returning greetings for locale = {}", locale);
        caisseService.deleteCaisse(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
