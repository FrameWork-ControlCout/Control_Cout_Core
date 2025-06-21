/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.FamilleArticle;
import com.FrameWork.ControlCout.Parametrage.dto.FamilleArticleDTO;
import com.FrameWork.ControlCout.Parametrage.service.FamilleArticleService;
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
public class FamilleArticleRessource {
    private final FamilleArticleService familleArticleService;
    
    public FamilleArticleRessource(FamilleArticleService familleArticleService) {
        this.familleArticleService = familleArticleService;
    }
    
    @GetMapping("famille_article/{code}")
    public ResponseEntity<FamilleArticleDTO> getFamilleArticleByCode(@PathVariable Integer code) {
        FamilleArticleDTO dto = familleArticleService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping("famille_article/all")
    public ResponseEntity<List<FamilleArticleDTO>> getAllFamilleArticle() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(familleArticleService.findAllFamilleArticle());
    }
    
   @GetMapping("famille_article/findByActif")
    public ResponseEntity<List<FamilleArticleDTO>> getFamilleArticleByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(familleArticleService.findAllFamilleArticleByActif(actif));
    }
    
    @GetMapping("famille_article/findByActifAndType")
    public ResponseEntity<List<FamilleArticleDTO>> getFamilleArticleByActifAndType(@RequestParam Boolean actif,@RequestParam String type) {
        return ResponseEntity.ok().body(familleArticleService.findAllFamilleArticleByActifAndType(actif,type));
    }
    @PostMapping("famille_article")
    public ResponseEntity<FamilleArticleDTO> postFamilleArticle(@Valid @RequestBody FamilleArticleDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FamilleArticleDTO result = familleArticleService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }
    
    @PutMapping("famille_article/update")
    public ResponseEntity<FamilleArticle> updateFamilleArticle(@RequestBody @Valid FamilleArticleDTO dto) throws URISyntaxException {
        FamilleArticle result = familleArticleService.update(dto);
        return ResponseEntity.ok().body(result);
    }
    
    @DeleteMapping("famille_article/delete/{Code}")
    public ResponseEntity<FamilleArticle> deleteFamilleArticle(@PathVariable("Code") Integer code) {
        familleArticleService.deleteFamilleArticle(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
