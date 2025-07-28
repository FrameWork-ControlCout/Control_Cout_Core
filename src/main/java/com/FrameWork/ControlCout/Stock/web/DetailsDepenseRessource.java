/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.web;

import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Stock.dto.DetailsDepenseDTO;
import com.FrameWork.ControlCout.Stock.service.DetailsDepenseService;
import java.util.List;
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
@RequestMapping("/api/stock/")
public class DetailsDepenseRessource {
    private final DetailsDepenseService detailsDepenseService;

    public DetailsDepenseRessource(DetailsDepenseService detailsDepenseService) {
        this.detailsDepenseService = detailsDepenseService;
    }
    
     @GetMapping("details_Depense/findByCodeDepense")
    public ResponseEntity<List<DetailsDepenseDTO>> getDetailsDepenseByCodeDepense(@RequestParam Integer codeDepense) {
        List<DetailsDepenseDTO> dTO = detailsDepenseService.findByCodeDepense(codeDepense);
        return ResponseEntity.ok().body(dTO);
    }
    
}
