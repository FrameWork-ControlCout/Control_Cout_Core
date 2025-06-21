/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.dto.ArticleDTO;
import com.FrameWork.ControlCout.Parametrage.service.ArticleService;
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
public class ArticleRessource {

    private final ArticleService familleArticleService;

    public ArticleRessource(ArticleService familleArticleService) {
        this.familleArticleService = familleArticleService;
    }

    @GetMapping("article/{code}")
    public ResponseEntity<ArticleDTO> getArticleByCode(@PathVariable Integer code) {
        ArticleDTO dto = familleArticleService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("article/all")
    public ResponseEntity<List<ArticleDTO>> getAllArticle() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(familleArticleService.findAllArticle());
    }

    @GetMapping("article/findByTypeAndActif")
    public ResponseEntity<List<ArticleDTO>> getArticleByTypeAndActif(@RequestParam String type, @RequestParam Boolean actif) {
        return ResponseEntity.ok().body(familleArticleService.findAllArticleByTypeAndActif(type, actif));
    }

    @GetMapping("article/findByType")
    public ResponseEntity<List<ArticleDTO>> getArticleByType(@RequestParam String type) {
        return ResponseEntity.ok().body(familleArticleService.findAllArticleByType(type));
    }

    
    @PostMapping("article")
    public ResponseEntity<ArticleDTO> postArticle(@Valid @RequestBody ArticleDTO dto, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ArticleDTO result = familleArticleService.save(dto);
        return ResponseEntity.created(new URI("/api/parametrage_achat/" + result.getCode())).body(result);
    }

    @PutMapping("article/update")
    public ResponseEntity<Article> updateArticle(@RequestBody @Valid ArticleDTO dto) throws URISyntaxException {
        Article result = familleArticleService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("article/delete/{Code}")
    public ResponseEntity<Article> deleteArticle(@PathVariable("Code") Integer code) {
        familleArticleService.deleteArticle(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
