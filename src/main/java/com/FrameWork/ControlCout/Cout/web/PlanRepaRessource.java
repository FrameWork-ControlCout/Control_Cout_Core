/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.web;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.dto.PlanRepaDTO;
import com.FrameWork.ControlCout.Cout.service.PlanRepaService;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/cout/")
public class PlanRepaRessource {

    private final PlanRepaService factureAchatService;

    public PlanRepaRessource(PlanRepaService factureAchatService) {
        this.factureAchatService = factureAchatService;
    }

    @GetMapping("plan_repa/{code}")
    public ResponseEntity<PlanRepaDTO> getPlanRepaByCode(@PathVariable Integer code) {
        PlanRepaDTO dTO = factureAchatService.findOne(code);
        return ResponseEntity.ok().body(dTO);
    }

    @GetMapping("plan_repa/all")
    public ResponseEntity<List<PlanRepaDTO>> getAllPlanRepa() {
        return ResponseEntity.ok().body(factureAchatService.findAllPlanRepa());
    }

//    @GetMapping("plan_repa/findByDatePlan")
//    public ResponseEntity<List<PlanRepaDTO>> getAllPlanRepaByDatePlan(@RequestParam Date datePlan) {
//        return ResponseEntity.ok().body(factureAchatService.findAllPlanRepaByDatePlan(datePlan));
//    }
    @GetMapping("/plan_repa/findByDateRange")
    public ResponseEntity<List<PlanRepaDTO>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<PlanRepaDTO> results = factureAchatService.findAllPlanRepaByDateRange(startDate, endDate);
        return ResponseEntity.ok(results);
    }

    @PostMapping("plan_repa")
    public ResponseEntity<PlanRepaDTO> postPlanRepa(@Valid @RequestBody PlanRepaDTO dTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        PlanRepaDTO result = factureAchatService.save(dTO);
        return ResponseEntity.created(new URI("/api/cout/" + result.getCode())).body(result);
    }

    @PutMapping("plan_repa/update")
    public ResponseEntity<PlanRepaDTO> updatePlanRepa(@Valid @RequestBody PlanRepaDTO dTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        PlanRepaDTO result = factureAchatService.update(dTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("plan_repa/delete/{Code}")
    public ResponseEntity<PlanRepa> deletePlanRepa(@PathVariable("Code") Integer code) {
        factureAchatService.deletePlanRepa(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
