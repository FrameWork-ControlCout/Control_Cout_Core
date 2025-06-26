/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Devise;
import com.FrameWork.ControlCout.Parametrage.dto.DeviseDTO;
import com.FrameWork.ControlCout.Parametrage.dto.FournisseurDTO;
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.repository.DeviseRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DeviseService {

    private final DeviseRepo deviseRepo;

    public DeviseService(DeviseRepo deviseRepo) {
        this.deviseRepo = deviseRepo;
    }

    @Transactional(readOnly = true)
    public List<DeviseDTO> findAllDevise() {
        return DeviseFactory.listDeviseToDeviseDTOs(deviseRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public DeviseDTO findOne(Integer code) {
        Devise domaine = deviseRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DeviseNotFound");
        return DeviseFactory.deviseToDeviseDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<DeviseDTO> findAllDeviseByActif(Boolean actif) {
         Sort sort = Sort.by(Sort.Direction.DESC, "code");
        return DeviseFactory.listDeviseToDeviseDTOs(deviseRepo.findByActif(actif,sort) );

    }

    @Transactional(readOnly = true)
    public List<DeviseDTO> findByHasTaux(boolean hasTaux) {
        List<Devise> domaine = deviseRepo.findByHasTaux(hasTaux);
        if (domaine.isEmpty()) {

        } else {
            Preconditions.checkArgument(domaine.iterator().next().getCode() != null, "error.DeviseNotFound");

        }
        return DeviseFactory.listDeviseToDeviseDTOs(domaine);
    }

//
    public DeviseDTO save(DeviseDTO dto) {
        Devise domaine = DeviseFactory.deviseDTOToDevise(dto, new Devise());
        domaine.setHasTaux(false);
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = deviseRepo.save(domaine);

        return DeviseFactory.deviseToDeviseDTO(domaine);
    }

    public Devise update(DeviseDTO dto) {
        Devise domaine = deviseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DeviseNotFound");
        dto.setCode(domaine.getCode());
        DeviseFactory.deviseDTOToDevise(dto, domaine);

        return deviseRepo.save(domaine);
    }

    public Devise updateHasTaux(DeviseDTO dto) {
        Devise domaine = deviseRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DeviseNotFound");
        dto.setCode(domaine.getCode());
        DeviseFactory.deviseDTOToDeviseHasTaux(dto, domaine);
        return deviseRepo.save(domaine);
    }

    public void deleteDevise(Integer code) {
        Preconditions.checkArgument(deviseRepo.existsById(code), "error.DeviseNotFound");
        deviseRepo.deleteById(code);
    }
}
