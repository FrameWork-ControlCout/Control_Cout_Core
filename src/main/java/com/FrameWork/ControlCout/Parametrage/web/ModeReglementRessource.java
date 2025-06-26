/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.ModeReglement;
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import com.FrameWork.ControlCout.Parametrage.dto.ModeReglementDTO;
import com.FrameWork.ControlCout.Parametrage.service.ModeReglementService;
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
@RequestMapping("/api/parametrage/")
public class ModeReglementRessource {
     private final ModeReglementService modeReglementService;

    public ModeReglementRessource(ModeReglementService modeReglementService) {
        this.modeReglementService = modeReglementService;
    }

    @GetMapping("mode_reglement/{code}")
    public ResponseEntity<ModeReglementDTO> getModeReglementByCode(@PathVariable Integer code) {
        ModeReglementDTO dto = modeReglementService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }
    
        @GetMapping("mode_reglement/findByActif")
    public ResponseEntity<List<ModeReglementDTO>> getAllModeReglementByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(modeReglementService.findAllModeReglementByActif(actif));
    }
    

    @GetMapping("mode_reglement/all")
    public ResponseEntity<List<ModeReglementDTO>> getAllModeReglement() {
//        List<DdeAchat> ddeAchatList = ddeAchatService.findAllDdeAchat();
        return ResponseEntity.ok().body(modeReglementService.findAllModeReglement());
    }

    @PostMapping("mode_reglement")
    public ResponseEntity<ModeReglementDTO> postModeReglement(@Valid @RequestBody ModeReglementDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ModeReglementDTO result = modeReglementService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("mode_reglement/update")
    public ResponseEntity<ModeReglement> updateModeReglement(@RequestBody @Valid ModeReglementDTO dto) throws URISyntaxException {
        ModeReglement result = modeReglementService.update(dto);
        return ResponseEntity.ok().body(result);
    }

//    @DeleteMapping("mode_reglement/delete/{Code}")
//    public ResponseEntity<ModeReglement> deleteModeReglement(@PathVariable("Code") Integer code) {
//        modeReglementService.deleteModeReglement(code);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
