/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsBonReceptionFactory;
import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import com.FrameWork.ControlCout.Stock.dto.DetailsDepenseDTO;
import com.FrameWork.ControlCout.Stock.factory.DetailsDepenseFactory;
import com.FrameWork.ControlCout.Stock.repository.DetailsDepenseRepo;
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
public class DetailsDepenseService {
    private final DetailsDepenseRepo detailsDepenseRepo;

    public DetailsDepenseService(DetailsDepenseRepo detailsDepenseRepo) {
        this.detailsDepenseRepo = detailsDepenseRepo;
    }
    
    
      @Transactional(readOnly = true)
    public List<DetailsDepenseDTO> findByCodeDepense(Integer codeDepense) {
        List<DetailsDepense> domaine = detailsDepenseRepo.findByCodeDepense(codeDepense);
        Preconditions.checkArgument(domaine != null, "error.DetailsBonReceptionNotFound");
        return DetailsDepenseFactory.listDetailsDepenseToDetailsDepenseDTOs(domaine);
    }

    
}
