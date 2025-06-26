/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.web;

import com.FrameWork.ControlCout.Parametrage.domaine.Banque;
import com.FrameWork.ControlCout.Parametrage.dto.BanqueDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.service.BanqueService;
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
 * @author Administrator
 */
@RestController
@RequestMapping("/api/parametrage/")
public class BanqueRessource {

    private final BanqueService banqueService;

    public BanqueRessource(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    @GetMapping("banque/{code}")
    public ResponseEntity<BanqueDTO> getBanqueByCode(@PathVariable Integer code) {
        BanqueDTO dto = banqueService.findOne(code);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("banque/findByActif")
    public ResponseEntity<List<BanqueDTO>> getBanqueByActif(@RequestParam Boolean actif) {
        return ResponseEntity.ok().body(banqueService.findAllBanqueByActif(actif));
    }

    @GetMapping("banque/all")
    public ResponseEntity<List<BanqueDTO>> getAllBanque() {
        return ResponseEntity.ok().body(banqueService.findAllBanque());
    }

    @PostMapping("banque")
    public ResponseEntity<BanqueDTO> postBanque(@Valid @RequestBody BanqueDTO ddeTransfertDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        BanqueDTO result = banqueService.save(ddeTransfertDTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("banque/update")
    public ResponseEntity<Banque> updateBanque(@RequestBody @Valid BanqueDTO dto) throws URISyntaxException {
        Banque result = banqueService.update(dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("banque/delete/{Code}")
    public ResponseEntity<Banque> deleteBanque(@PathVariable("Code") Integer code) {
        banqueService.deleteBanque(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
