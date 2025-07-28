/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureBonReceptionRepo;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsFactureBonReceptionService {

    private final DetailsFactureBonReceptionRepo detailsFactureBonReceptionRepo;

    public DetailsFactureBonReceptionService(DetailsFactureBonReceptionRepo detailsFactureBonReceptionRepo) {
        this.detailsFactureBonReceptionRepo = detailsFactureBonReceptionRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsFactureBonReceptionDTO> findAllDetailsFactureBonReception() {
        return DetailsFactureBonReceptionFactory.listDetailsFactureBonReceptionToDetailsFactureBonReceptionDTOs(detailsFactureBonReceptionRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsFactureBonReceptionDTO findOne(Integer code) {
        DetailsFactureBonReception domaine = detailsFactureBonReceptionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureBonReceptionNotFound");
        return DetailsFactureBonReceptionFactory.detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsFactureBonReceptionDTO> findByCodeFactureBonReception(Integer codeFacture) {
        List<DetailsFactureBonReception> domaine = detailsFactureBonReceptionRepo.findByCodeFactureBonReception(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsFactureBonReceptionNotFound");
        return DetailsFactureBonReceptionFactory.listDetailsFactureBonReceptionToDetailsFactureBonReceptionDTOs(domaine);
    }

    public DetailsFactureBonReceptionDTO save(DetailsFactureBonReceptionDTO dto) {
        DetailsFactureBonReception domaine = DetailsFactureBonReceptionFactory.detailsfactureBonReceptionDTOTodetailsFactureBonReception(dto, new DetailsFactureBonReception());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsFactureBonReceptionRepo.save(domaine);
        return DetailsFactureBonReceptionFactory.detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(domaine);
    }
 
    public DetailsFactureBonReceptionDTO update(DetailsFactureBonReceptionDTO dto) {
        DetailsFactureBonReception domaine = detailsFactureBonReceptionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsFactureBonReceptionFactory.detailsfactureBonReceptionDTOTodetailsFactureBonReception(dto, domaine);
        domaine = detailsFactureBonReceptionRepo.save(domaine);
        DetailsFactureBonReceptionDTO resultDTO = DetailsFactureBonReceptionFactory.detailsfactureFactureBonReceptionToDetailsFactureBonReceptionDTO(domaine);
        return resultDTO;
    }

    public void deleteDetailsFactureBonReception(Integer code) {
        Preconditions.checkArgument(detailsFactureBonReceptionRepo.existsById(code), "error.DetailsFactureBonReceptionNotFound");
        detailsFactureBonReceptionRepo.deleteById(code);
    }

}
