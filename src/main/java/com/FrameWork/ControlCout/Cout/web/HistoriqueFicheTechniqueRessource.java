/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.dto.HistoriqueFicheTechniqueDTO;
import com.FrameWork.ControlCout.Cout.service.HistoriqueFicheTechniqueService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/cout/")
public class HistoriqueFicheTechniqueRessource {
    private final HistoriqueFicheTechniqueService historiqueFicheTechniqueService;

    public HistoriqueFicheTechniqueRessource(HistoriqueFicheTechniqueService historiqueFicheTechniqueService) {
        this.historiqueFicheTechniqueService = historiqueFicheTechniqueService;
    }

    @GetMapping("historique_fiche_technique/findByFicheTechniqueAndArticle")
    public ResponseEntity<List<HistoriqueFicheTechniqueDTO>> getHistoriqueFicheTechniqueByFicheTechniqueAndArticle(@RequestParam Integer codeArticle,@RequestParam Integer codeFicheTechnique ) {
        List<HistoriqueFicheTechniqueDTO> dTO = historiqueFicheTechniqueService.findAllHistoriqueFicheTechniqueByCodeArticleAndCodeFicheTechnique(codeArticle,codeFicheTechnique);
        return ResponseEntity.ok().body(dTO);
    }
}
