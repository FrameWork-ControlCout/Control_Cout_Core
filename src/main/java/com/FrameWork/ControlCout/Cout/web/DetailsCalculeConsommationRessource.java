/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.dto.DetailsCalculeConsommationDTO;
import com.FrameWork.ControlCout.Cout.service.DetailsCalculeConsommationService;
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
public class DetailsCalculeConsommationRessource {

    private final DetailsCalculeConsommationService detailsCalculeConsommationService;

    public DetailsCalculeConsommationRessource(DetailsCalculeConsommationService detailsCalculeConsommationService) {
        this.detailsCalculeConsommationService = detailsCalculeConsommationService;
    }

    @GetMapping("details_consommation/{code}")
    public ResponseEntity<DetailsCalculeConsommationDTO> getDetailsCalculeConsommationByCode(@PathVariable Integer code) {
        DetailsCalculeConsommationDTO dTO = detailsCalculeConsommationService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_consommation/findByCodeCalculeConsommation")
    public ResponseEntity<List<DetailsCalculeConsommationDTO>> getDetailsCalculeConsommationByCodeFacture(@RequestParam Integer codeCalculeConsommation) {
        List<DetailsCalculeConsommationDTO> dTO = detailsCalculeConsommationService.findByCodeCalculeConsommation(codeCalculeConsommation);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_consommation/all")
    public ResponseEntity<List<DetailsCalculeConsommationDTO>> getAllDetailsCalculeConsommation() {
        return ResponseEntity.ok().body(detailsCalculeConsommationService.findAllDetailsCalculeConsommation());
    }

    @PostMapping("details_consommation/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsCalculeConsommationDTO> detailsCalculeConsommationDTOs) {
        List<DetailsCalculeConsommationDTO> result = detailsCalculeConsommationService.saveList(detailsCalculeConsommationDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_consommation/update")
    public ResponseEntity<DetailsCalculeConsommationDTO> updateDetailsCalculeConsommation(@Valid @RequestBody DetailsCalculeConsommationDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsCalculeConsommationDTO result = detailsCalculeConsommationService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

}
