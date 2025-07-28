/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.FactureGros;
import com.FrameWork.ControlCout.Achat.dto.FactureGrosDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.FactureGrosService;
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
public class FactureGrosECRessource {

    private final FactureGrosService factureGrosService;

    public FactureGrosECRessource(FactureGrosService factureGrosService) {
        this.factureGrosService = factureGrosService;
    }

    @GetMapping("facture_gros/{code}")
    public ResponseEntity<FactureGrosDTO> getFactureGrosByCode(@PathVariable Integer code) {
        FactureGrosDTO dTO = factureGrosService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("facture_gros/all")
    public ResponseEntity<List<FactureGrosDTO>> getAllFactureGros() {
        return ResponseEntity.ok().body(factureGrosService.findAllFactureGros());
    }

    @GetMapping("facture_gros/findByFournisseur")
    public ResponseEntity<List<FactureGrosDTO>> getAllFactureGrosByFournisseur(@RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(factureGrosService.findOrderAchatByCodeFournisseur(codeFournisseur));
    }

    @GetMapping("facture_gros/findByDate")
    public ResponseEntity<Collection<FactureGrosDTO>> getAllFactureGrosByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(factureGrosService.findFactureGrosByDateCreateBetween(dateDebut, dateFin));
    }

    @PostMapping("facture_gros")
    public ResponseEntity<FactureGrosDTO> postFactureGros(@Valid @RequestBody FactureGrosDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureGrosDTO result = factureGrosService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("facture_gros/update")
    public ResponseEntity<FactureGrosDTO> updateFactureGros(@Valid @RequestBody FactureGrosDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureGrosDTO result = factureGrosService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("facture_gros/delete/{Code}")
    public ResponseEntity<FactureGros> deleteFactureGros(@PathVariable("Code") Integer code) {
        factureGrosService.deleteFactureGros(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
