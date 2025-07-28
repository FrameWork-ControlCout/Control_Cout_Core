/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.service.DetailsConsoStandardService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
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
public class DetailsConsoStandardRessource {

    private final DetailsConsoStandardService detailsConsoStandardService;

    public DetailsConsoStandardRessource(DetailsConsoStandardService detailsConsoStandardService) {
        this.detailsConsoStandardService = detailsConsoStandardService;
    }

    @GetMapping("details_consommation_standard/{code}")
    public ResponseEntity<DetailsConsoStandardDTO> getDetailsConsoStandardByCode(@PathVariable Integer code) {
        DetailsConsoStandardDTO dTO = detailsConsoStandardService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_consommation_standard/findByCodeConsoStandard")
    public ResponseEntity<List<DetailsConsoStandardDTO>> getDetailsConsoStandardByCodeFacture(@RequestParam List<Integer> codeConsoStandard) {
        List<DetailsConsoStandardDTO> dTO = detailsConsoStandardService.findByCodeConsoStandard(codeConsoStandard);
        return ResponseEntity.ok().body(dTO);
    }
    
    
//    @GetMapping("details_consommation_standard/by-conso-standard-recalculated")
//    public ResponseEntity<List<DetailsConsoStandardDTO>> getDetailsByConsoStandardAndRecalculate(
//            @RequestParam Integer codeConsoStandard,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
//    ) {
//        List<DetailsConsoStandardDTO> dtoList = detailsConsoStandardService.findByConsoStandardAndRecalculate(codeConsoStandard, startDate, endDate);
//        return ResponseEntity.ok().body(dtoList);
//    }
      @GetMapping("details_consommation_standard/findByCodeConsoStandardAndHaveOA")
    public ResponseEntity<List<DetailsConsoStandardDTO>> getDetailsConsoStandardByCodeFactureAndHaveOA(
    @RequestParam List<Integer> codeConsoStandard,
            @RequestParam boolean haveOA) {
        List<DetailsConsoStandardDTO> dTO = detailsConsoStandardService.findByCodeConsoStandardAndHaveOA(codeConsoStandard,haveOA);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_consommation_standard/all")
    public ResponseEntity<List<DetailsConsoStandardDTO>> getAllDetailsConsoStandard() {
        return ResponseEntity.ok().body(detailsConsoStandardService.findAllDetailsConsoStandard());
    }

    @PostMapping("details_consommation_standard/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsConsoStandardDTO> detailsConsoStandardDTOs) {
        List<DetailsConsoStandardDTO> result = detailsConsoStandardService.saveList(detailsConsoStandardDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_consommation_standard/update")
    public ResponseEntity<DetailsConsoStandardDTO> updateDetailsConsoStandard(@Valid @RequestBody DetailsConsoStandardDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsConsoStandardDTO result = detailsConsoStandardService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

}
