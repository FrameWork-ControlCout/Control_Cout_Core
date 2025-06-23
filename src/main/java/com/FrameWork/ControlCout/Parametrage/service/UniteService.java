/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.service;

import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.dto.UniteDTO;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Parametrage.repository.UniteRepo;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class UniteService {

    private final UniteRepo uniteRepo;
    private final CompteurService compteurService;

    public UniteService(UniteRepo uniteRepo, CompteurService compteurService) {
        this.uniteRepo = uniteRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<UniteDTO> findAllUnite() {
        return UniteFactory.listUniteToUniteDTOs(uniteRepo.findAll(Sort.by("code").descending()));

    }

    @Transactional(readOnly = true)
    public UniteDTO findOne(Integer code) {
        Unite domaine = uniteRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.UniteNotFound");
        return UniteFactory.uniteToUniteDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<UniteDTO> findAllUniteByActif(Boolean actif) {
        return UniteFactory.listUniteToUniteDTOs(uniteRepo.findByActif(actif));

    }
 

    public UniteDTO save(UniteDTO dto) {
        Unite domaine = UniteFactory.uniteDTOToUnite(dto, new Unite());

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        Compteur CompteurCodeSaisie = compteurService.findOne("Unite");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine = uniteRepo.save(domaine);

        return UniteFactory.uniteToUniteDTO(domaine);
    }

    public Unite update(UniteDTO dto) {
        Unite domaine = uniteRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.UniteNotFound");
        dto.setCode(domaine.getCode());
        UniteFactory.uniteDTOToUnite(dto, domaine);

        return uniteRepo.save(domaine);
    }

    public void deleteUnite(Integer code) {
        Preconditions.checkArgument(uniteRepo.existsById(code), "error.UniteNotFound");
        uniteRepo.deleteById(code);
    }
}
