/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.dto.DetailsTechCardDTO;
import com.FrameWork.ControlCout.Cout.service.DetailsTechCardService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/cout/")
public class DetailsTechCardRessource {

    private final DetailsTechCardService detailsTechCardService;

    public DetailsTechCardRessource(DetailsTechCardService detailsTechCardService) {
        this.detailsTechCardService = detailsTechCardService;
    }

    @GetMapping("details_tech_card/{code}")
    public ResponseEntity<DetailsTechCardDTO> getDetailsTechCardByCode(@PathVariable Integer code) {
        DetailsTechCardDTO dTO = detailsTechCardService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_tech_card/findByCodeTechCard")
    public ResponseEntity<List<DetailsTechCardDTO>> getDetailsTechCardByCodeFacture(@RequestParam Integer codeTechCard) {
        List<DetailsTechCardDTO> dTO = detailsTechCardService.findByCodeTechCard(codeTechCard);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_tech_card/all")
    public ResponseEntity<List<DetailsTechCardDTO>> getAllDetailsTechCard() {
        return ResponseEntity.ok().body(detailsTechCardService.findAllDetailsTechCard());
    }

    @PostMapping("details_tech_card/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsTechCardDTO> detailsTechCardDTOs) {
        List<DetailsTechCardDTO> result = detailsTechCardService.saveList(detailsTechCardDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_tech_card/update")
    public ResponseEntity<DetailsTechCardDTO> updateDetailsTechCard(@Valid @RequestBody DetailsTechCardDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsTechCardDTO result = detailsTechCardService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

}
