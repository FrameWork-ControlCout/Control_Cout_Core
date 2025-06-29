/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

//import com.FrameWork.MedLite.Authentification.service.AccessUserService;
import com.FrameWork.ControlCout.Achat.domaine.ComparFactureAchat;
import com.FrameWork.ControlCout.Achat.dto.ComparFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.service.ComparFactureAchatService;
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
public class ComparFactureAchatRessource {

    private final ComparFactureAchatService comparfactureAchatService;

    public ComparFactureAchatRessource(ComparFactureAchatService comparfactureAchatService) {
        this.comparfactureAchatService = comparfactureAchatService;
    }

    @GetMapping("compar_facture_achat/{code}")
    public ResponseEntity<ComparFactureAchatDTO> getComparFactureAchatByCode(@PathVariable Integer code) {
        ComparFactureAchatDTO dTO = comparfactureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("compar_facture_achat/all")
    public ResponseEntity<List<ComparFactureAchatDTO>> getAllComparFactureAchat() {
        return ResponseEntity.ok().body(comparfactureAchatService.findAllComparFactureAchat());
    }

    @GetMapping("compar_facture_achat/findByFournisseurCompar")
    public ResponseEntity<List<ComparFactureAchatDTO>> getAllComparFactureAchatByFournisseurCompar(@RequestParam Integer codeFournisseurCompar) {
        return ResponseEntity.ok().body(comparfactureAchatService.findComparFactureAchatByFournisseurCompar(codeFournisseurCompar));
    }

    @GetMapping("compar_facture_achat/findByDate")
    public ResponseEntity<Collection<ComparFactureAchatDTO>> getAllComparFactureAchatByDateCreate(@RequestParam LocalDate dateDebut, @RequestParam LocalDate dateFin) {
        return ResponseEntity.ok().body(comparfactureAchatService.findComparFactureByDateCreateBetween(dateDebut, dateFin));
    }

    @PostMapping("compar_facture_achat")
    public ResponseEntity<ComparFactureAchatDTO> postComparFactureAchat(@Valid @RequestBody ComparFactureAchatDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        ComparFactureAchatDTO result = comparfactureAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/achat/" + result.getCode())).body(result);
    }

    @PutMapping("compar_facture_achat/update")
    public ResponseEntity<ComparFactureAchatDTO> updateComparFactureAchat(@Valid @RequestBody ComparFactureAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        ComparFactureAchatDTO result = comparfactureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("compar_facture_achat/delete/{code}")
    public ResponseEntity<ComparFactureAchat> deleteComparFactureAchat(@PathVariable("Code") Integer code) {
        comparfactureAchatService.deleteComparFactureAchat(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
