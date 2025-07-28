/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.FactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.FactureBonReceptionService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;
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
@RequestMapping("/api/achat/")
public class FactureBonReceptionRessource {

    private final FactureBonReceptionService factureBonReceptionService;

    public FactureBonReceptionRessource(FactureBonReceptionService factureBonReceptionService) {
        this.factureBonReceptionService = factureBonReceptionService;
    }

    @GetMapping("facture_bon_reception/{code}")
    public ResponseEntity<FactureBonReceptionDTO> getFactureBonReceptionByCode(@PathVariable Integer code) {
        FactureBonReceptionDTO dTO = factureBonReceptionService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("facture_bon_reception/all")
    public ResponseEntity<List<FactureBonReceptionDTO>> getAllFactureBonReception() {
        return ResponseEntity.ok().body(factureBonReceptionService.findAllFactureBonReception());
    }

    @GetMapping("facture_bon_reception/findByFournisseur")
    public ResponseEntity<List<FactureBonReceptionDTO>> getAllFactureBonReceptionByFournisseur(@RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(factureBonReceptionService.findFactureBonReceptionByCodeFournisseur(codeFournisseur));
    }

    @GetMapping("facture_bon_reception/findByDate")
    public ResponseEntity<Collection<FactureBonReceptionDTO>> getAllFactureBonReceptionByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(factureBonReceptionService.findFactureBonReceptionByDateCreateBetween(dateDebut, dateFin));
    }

    @PostMapping("facture_bon_reception")
    public ResponseEntity<FactureBonReceptionDTO> postFactureBonReception(@Valid @RequestBody FactureBonReceptionDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureBonReceptionDTO result = factureBonReceptionService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("facture_bon_reception/update")
    public ResponseEntity<FactureBonReceptionDTO> updateFactureBonReception(@Valid @RequestBody FactureBonReceptionDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureBonReceptionDTO result = factureBonReceptionService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("facture_bon_reception/delete/{Code}")
    public ResponseEntity<FactureBonReception> deleteFactureBonReception(@PathVariable("Code") Integer code) {
        factureBonReceptionService.deleteFactureBonReception(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
