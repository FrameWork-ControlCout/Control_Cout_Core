/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import com.FrameWork.ControlCout.Achat.dto.FactureAchatDTO;
import com.FrameWork.ControlCout.Achat.service.FactureAchatService;
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
public class FactureAchatRessource {

    private final FactureAchatService factureAchatService;

    public FactureAchatRessource(FactureAchatService factureAchatService) {
        this.factureAchatService = factureAchatService;
    }

    @GetMapping("facture_achat/{code}")
    public ResponseEntity<FactureAchatDTO> getFactureAchatByCode(@PathVariable Integer code) {
        FactureAchatDTO dTO = factureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("facture_achat/all")
    public ResponseEntity<List<FactureAchatDTO>> getAllFactureAchat() {
        return ResponseEntity.ok().body(factureAchatService.findAllFactureAchat());
    }

    @GetMapping("facture_achat/findByEtatFacture")
    public ResponseEntity<List<FactureAchatDTO>> getAllFactureAchatByCodeEtatFacture(@RequestParam Integer codeEtatFacture) {
        return ResponseEntity.ok().body(factureAchatService.findFactureAchatByEtatFacture(codeEtatFacture));
    }
    
    
     @GetMapping("facture_achat/findByFournisseur")
    public ResponseEntity<List<FactureAchatDTO>> getAllFactureAchatByFournisseur( @RequestParam Integer codeFournisseur) {
        return ResponseEntity.ok().body(factureAchatService.findFactureAchatByFournisseur(codeFournisseur));
    }
     @GetMapping("facture_achat/findByFournisseurAndEtatFacture")
    public ResponseEntity<List<FactureAchatDTO>> getAllFactureAchatByFournisseurAndEtatFacture(@RequestParam Integer codeFournisseur,@RequestParam Integer codeEtatFacture) {
        return ResponseEntity.ok().body(factureAchatService.findFactureAchatByFournisseurAndCodeEtatFacture(codeFournisseur,codeEtatFacture));
    }

       @GetMapping("facture_achat/findByDate")
    public ResponseEntity<Collection<FactureAchatDTO>> getAllFactureAchatByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin)  {
        return ResponseEntity.ok().body(factureAchatService.findFactureByDateCreateBetween(dateDebut,dateFin));
    }

    @PostMapping("facture_achat")
    public ResponseEntity<FactureAchatDTO> postFactureAchat(@Valid @RequestBody FactureAchatDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        FactureAchatDTO result = factureAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/parametrage/" + result.getCode())).body(result);
    }

    @PutMapping("facture_achat/update")
    public ResponseEntity<FactureAchatDTO> updateFactureAchat(@Valid @RequestBody FactureAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        FactureAchatDTO result = factureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("facture_achat/delete/{code}")
    public ResponseEntity<FactureAchat> deleteFactureAchat(@PathVariable("Code") Integer code) {
        factureAchatService.deleteFactureAchat(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
