/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandardPerDay;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardPerDayDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.factory.DetailsConsoStandardPerDayFactory;
import com.FrameWork.ControlCout.Cout.repository.DetailsConsoStandardPerDayRepo;
import com.google.common.base.Preconditions;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsConsoStandardPerDayService {
    
    private final DetailsConsoStandardPerDayRepo detailsConsoStandardPerDayRepo;

    public DetailsConsoStandardPerDayService(DetailsConsoStandardPerDayRepo detailsConsoStandardPerDayRepo) {
        this.detailsConsoStandardPerDayRepo = detailsConsoStandardPerDayRepo;
    }
    
    
      @Transactional(readOnly = true)
    public List<DetailsConsoStandardPerDayDTO> findByCodeConsoStandard(Integer codeConsoStandard) {
        List<DetailsConsoStandardPerDay> domaine = detailsConsoStandardPerDayRepo.findByCodeConsoStandardPerDay(codeConsoStandard);
        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardPerDayNotFound");
        return DetailsConsoStandardPerDayFactory.listDetailsConsoStandardPerDayToDetailsConsoStandardPerDayDTOs(domaine);
    }
}
