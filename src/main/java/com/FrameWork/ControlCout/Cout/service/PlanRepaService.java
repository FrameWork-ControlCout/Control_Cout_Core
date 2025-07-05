/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.dto.PlanRepaDTO;
import com.FrameWork.ControlCout.Cout.factory.PlanRepaFactory;
import com.FrameWork.ControlCout.Cout.repository.PlanRepaRepo;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class PlanRepaService {

    private final Logger log = LoggerFactory.getLogger(PlanRepaService.class);

    private final PlanRepaRepo planRepaRepo;

    public PlanRepaService(PlanRepaRepo planRepaRepo) {
        this.planRepaRepo = planRepaRepo;
    }

    @Transactional(readOnly = true)
    public List<PlanRepaDTO> findAllPlanRepa() {
        List<PlanRepa> domaine = planRepaRepo.findAll(Sort.by("code").descending());
        return PlanRepaFactory.listPlanRepaToPlanRepaDTOs(domaine);
    }

//      @Transactional(readOnly = true)
//    public List<PlanRepaDTO> findAllPlanRepaByDatePlan(Date datePlan) { 
//        List<PlanRepa> domaine = planRepaRepo.findByDatePlan(datePlan); 
//        return PlanRepaFactory.listPlanRepaToPlanRepaDTOs(domaine); 
//    }
    @Transactional(readOnly = true)
    public List<PlanRepaDTO> findAllPlanRepaByDateRange(LocalDate startDate, LocalDate endDate) {
        // Convert LocalDate to the start of the day as a LocalDateTime
        LocalDateTime startDateTime = startDate.atStartOfDay();
        // The end date from FullCalendar is exclusive, so we can use it directly
        LocalDateTime endDateTime = endDate.atStartOfDay();

        // Convert to old java.util.Date if your repository/entity still uses it
        Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Call the new repository method
        List<PlanRepa> domaine = planRepaRepo.findAllInDateRange(start, end);

        return PlanRepaFactory.listPlanRepaToPlanRepaDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public PlanRepaDTO findOne(Integer code) {
        PlanRepa domaine = planRepaRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.PlanRepaNotFound");
        return PlanRepaFactory.planRepaToPlanRepaDTO(domaine);
    }

    public PlanRepaDTO save(PlanRepaDTO dto) {
        PlanRepa domaine = PlanRepaFactory.planRepaDTOToPlanRepa(dto, new PlanRepa());
        domaine.setDateCreate(new Date());
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = planRepaRepo.save(domaine);
        return PlanRepaFactory.planRepaToPlanRepaDTO(domaine);
    }

    public void deletePlanRepa(Integer code) {
        Preconditions.checkArgument(planRepaRepo.existsById(code), "error.PlanRepaNotFound");
        planRepaRepo.deleteById(code);
    }

    public PlanRepaDTO update(PlanRepaDTO dto) {
        PlanRepa domaine = planRepaRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = PlanRepaFactory.planRepaDTOToPlanRepa(dto, domaine);
        domaine = planRepaRepo.save(domaine);
        PlanRepaDTO resultDTO = PlanRepaFactory.planRepaToPlanRepaDTO(domaine);
        return resultDTO;
    }

}
