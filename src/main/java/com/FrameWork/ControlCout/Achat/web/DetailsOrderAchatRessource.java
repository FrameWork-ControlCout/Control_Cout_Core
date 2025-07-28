/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.service.DetailsOrderAchatService;
import com.FrameWork.ControlCout.web.Util.parseCode;
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
public class DetailsOrderAchatRessource {

    private final DetailsOrderAchatService detailsOrderAchatService;

    public DetailsOrderAchatRessource(DetailsOrderAchatService detailsOrderAchatService) {
        this.detailsOrderAchatService = detailsOrderAchatService;
    }

    @GetMapping("details_order_achat/{code}")
    public ResponseEntity<DetailsOrderAchatDTO> getDetailsOrderAchatByCode(@PathVariable Integer code) {
        DetailsOrderAchatDTO dTO = detailsOrderAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_order_achat/findByCodeOrderAchat")
    public ResponseEntity<List<DetailsOrderAchatDTO>> getDetailsOrderAchatByCodeOrderAchat(@RequestParam Integer codeOrderAchat) {
        List<DetailsOrderAchatDTO> dTO = detailsOrderAchatService.findByCodeOrderAchat(codeOrderAchat);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_order_achat/findByCodeOrderAchatAndSatisfait")
    public ResponseEntity<List<DetailsOrderAchatDTO>> getDetailsOrderAchatByCodeOrderAchatAndSatisfait(@RequestParam Integer codeOrderAchat,
             @RequestParam Boolean satisfait) {
        List<DetailsOrderAchatDTO> dTO = detailsOrderAchatService.findByCodeOrderAchatAndSatisfait(codeOrderAchat, satisfait);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("details_order_achat/all")
    public ResponseEntity<List<DetailsOrderAchatDTO>> getAllDetailsOrderAchat() {
        return ResponseEntity.ok().body(detailsOrderAchatService.findAllDetailsOrderAchat());
    }

    @PostMapping("details_order_achat/List")
    public ResponseEntity<String> saveList(@RequestBody List<DetailsOrderAchatDTO> detailsOrderAchatDTOs) {
        List<DetailsOrderAchatDTO> result = detailsOrderAchatService.saveList(detailsOrderAchatDTOs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("details_order_achat/update")
    public ResponseEntity<DetailsOrderAchatDTO> updateDetailsOrderAchat(@Valid @RequestBody DetailsOrderAchatDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        DetailsOrderAchatDTO result = detailsOrderAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    
          @GetMapping("details_order_achat/details_by_codes")
    public ResponseEntity<List<DetailsOrderAchatDTO>> getDetailsByOrderCodes(@RequestParam String codes) {
        String rawCodeString = codes;
        List<Integer> codeList = parseCodeStringToList(rawCodeString);
        List<DetailsOrderAchatDTO> details = detailsOrderAchatService.findByCodeOrderAchatIn(codeList);
        return ResponseEntity.ok(details);
    }

    
      public List<Integer> parseCodeStringToList(String codeString) {
        if (codeString == null || codeString.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String[] codes = codeString.replace("(", "").replace(")", "").split(",");
        return Arrays.stream(codes)
                .map(String::trim)
                .filter(s -> !s.isEmpty() && s.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
      
      
}
