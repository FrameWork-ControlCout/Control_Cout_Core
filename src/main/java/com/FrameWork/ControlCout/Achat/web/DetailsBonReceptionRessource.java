/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.DetailsBonReceptionService;
import com.FrameWork.ControlCout.Achat.service.DetailsOrderAchatService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api/achat/")
public class DetailsBonReceptionRessource {

    private final DetailsBonReceptionService detailsBonReceptionService; 

    public DetailsBonReceptionRessource(DetailsBonReceptionService detailsBonReceptionService) {
        this.detailsBonReceptionService = detailsBonReceptionService;
    }

    

    @GetMapping("details_bon_reception/{code}")
    public ResponseEntity<DetailsBonReceptionDTO> getDetailsBonReceptionByCode(@PathVariable Integer code) {
        DetailsBonReceptionDTO dTO = detailsBonReceptionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }


    @GetMapping("details_bon_reception/findByCodeBonReception")
    public ResponseEntity<List<DetailsBonReceptionDTO>> getDetailsBonReceptionByCodeBonReception(@RequestParam Integer codeBonReception) {
        List<DetailsBonReceptionDTO> dTO = detailsBonReceptionService.findByCodeBonReception(codeBonReception);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_bon_reception/all")
    public ResponseEntity<List<DetailsBonReceptionDTO>> getAllDetailsBonReception() {
        return ResponseEntity.ok().body(detailsBonReceptionService.findAllDetailsBonReception());
    }

    @PostMapping("details_bon_reception/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsBonReceptionDTO> detailsBonReceptionDTOs) {
        List<DetailsBonReceptionDTO> result = detailsBonReceptionService.saveList(detailsBonReceptionDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_bon_reception/update")
    public ResponseEntity<DetailsBonReceptionDTO> updateDetailsBonReception(@Valid @RequestBody DetailsBonReceptionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsBonReceptionDTO result = detailsBonReceptionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

  
}
