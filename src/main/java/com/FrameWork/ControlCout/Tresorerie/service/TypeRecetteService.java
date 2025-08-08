/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.service;

import com.FrameWork.ControlCout.Tresorerie.domaine.TypeRecette;
import com.FrameWork.ControlCout.Tresorerie.dto.TypeRecetteDTO;
import com.FrameWork.ControlCout.Tresorerie.factory.TypeRecetteFactory;
import com.FrameWork.ControlCout.Tresorerie.repository.TypeRecetteRepo;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TypeRecetteService {
     private final TypeRecetteRepo typeRecetteRepo;

    public TypeRecetteService(TypeRecetteRepo typeRecetteRepo) {
        this.typeRecetteRepo = typeRecetteRepo;
    }

    @Transactional(readOnly = true)
    public List<TypeRecetteDTO> findAllTypeRecette() {
        return TypeRecetteFactory.listTypeRecetteToTypeRecetteDTOs(typeRecetteRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public TypeRecetteDTO findOne(Integer code) {
        TypeRecette domaine = typeRecetteRepo.findByCode(code);
        Preconditions.checkArgument(domaine  != null, "error.TypeRecetteNotFound");
        return TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine);
    }

//
    public TypeRecetteDTO save(TypeRecetteDTO dto) {
        TypeRecette domaine = TypeRecetteFactory.typeRecetteDTOToTypeRecette(dto, new TypeRecette());
               domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = typeRecetteRepo.save(domaine);
        return TypeRecetteFactory.typeRecetteToTypeRecetteDTO(domaine);
    }

    public TypeRecette update(TypeRecetteDTO dto) {
        Preconditions.checkArgument((dto.getCode() != null), "error.TypeRecetteNotFound");
        TypeRecette domaine = typeRecetteRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(true, "error.TypeRecetteNotFound");
        dto.setCode(domaine.getCode());
        TypeRecetteFactory.typeRecetteDTOToTypeRecette(dto, domaine);
        return typeRecetteRepo.save(domaine);
    }

    public void deleteTypeRecette(Integer code) {
        Preconditions.checkArgument(typeRecetteRepo.existsById(code), "error.TypeRecetteNotFound");
        typeRecetteRepo.deleteById(code);
    }
}
