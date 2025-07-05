/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.dto.paramDTO;
import com.FrameWork.ControlCout.Parametrage.service.ParamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class ParamRessource {

    private final ParamService paramService;

    public ParamRessource(ParamService paramService) {
        this.paramService = paramService;
    }

    @GetMapping("param/code")
    public ResponseEntity<paramDTO> getParamByCodeParam(@RequestParam String codeParam) {
        paramDTO dTOs = paramService.findParamByCodeParamS(codeParam);

        return ResponseEntity.ok().body(dTOs);
    }
}
