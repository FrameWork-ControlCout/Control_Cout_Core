/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.TypeRepa;
import com.FrameWork.ControlCout.Parametrage.dto.TypeRepaDTO;
import com.FrameWork.ControlCout.Parametrage.factory.TypeRepaFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.TypeRepaRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class TypeRepaService {

    private final TypeRepaRepo typeRepaRepo;
    private final CompteurService compteurService;

    public TypeRepaService(TypeRepaRepo typeRepaRepo, CompteurService compteurService) {
        this.typeRepaRepo = typeRepaRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<TypeRepaDTO> findAllTypeRepa() {
        return TypeRepaFactory.listTypeRepaToTypeRepaDTOs(typeRepaRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public TypeRepaDTO findOne(Integer code) {
        TypeRepa domaine = typeRepaRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.TypeRepaNotFound");
        return TypeRepaFactory.typeRepaToTypeRepaDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<TypeRepaDTO> findAllTypeRepaByActif(Boolean actif) {
        return TypeRepaFactory.listTypeRepaToTypeRepaDTOs(typeRepaRepo.findByActif(actif));

    }
  
 
    public TypeRepaDTO save(TypeRepaDTO dto) {
        TypeRepa domaine = TypeRepaFactory.typeRepaDTOToTypeRepa(dto, new TypeRepa());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("TypeRepa");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = typeRepaRepo.save(domaine);
    

        return TypeRepaFactory.typeRepaToTypeRepaDTO(domaine);
    }

    public TypeRepa update(TypeRepaDTO dto) {
        TypeRepa domaine = typeRepaRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.TypeRepaNotFound");
        dto.setCode(domaine.getCode());
        TypeRepaFactory.typeRepaDTOToTypeRepa(dto, domaine);

        return typeRepaRepo.save(domaine);
    }

    public void deleteTypeRepa(Integer code) {
        Preconditions.checkArgument(typeRepaRepo.existsById(code), "error.TypeRepaNotFound");
        typeRepaRepo.deleteById(code);
    }
}
