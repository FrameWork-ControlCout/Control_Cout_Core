 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsBonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.BonReceptionFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsBonReceptionRepo;
import com.FrameWork.ControlCout.Achat.repository.BonReceptionRepo;
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
public class DetailsBonReceptionService {

    private final DetailsBonReceptionRepo detailsBonReceptionRepo;

    public DetailsBonReceptionService(DetailsBonReceptionRepo detailsBonReceptionRepo) {
        this.detailsBonReceptionRepo = detailsBonReceptionRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsBonReceptionDTO> findAllDetailsBonReception() {
        return DetailsBonReceptionFactory.listBonReceptionToDetailsBonReceptionDTOs(detailsBonReceptionRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsBonReceptionDTO findOne(Integer code) {
        DetailsBonReception domaine = detailsBonReceptionRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsBonReceptionNotFound");
        return DetailsBonReceptionFactory.detailsbonReceptionToDetailsBonReceptionDTO(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsBonReceptionDTO> findByCodeBonReception(Integer codeFacture) {
        List<DetailsBonReception> domaine = detailsBonReceptionRepo.findByCodeBonReception(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsBonReceptionNotFound");
        return DetailsBonReceptionFactory.listBonReceptionToDetailsBonReceptionDTOs(domaine);
    }

    public DetailsBonReceptionDTO save(DetailsBonReceptionDTO dto) {
        DetailsBonReception domaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(dto, new DetailsBonReception());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsBonReceptionRepo.save(domaine);
        return DetailsBonReceptionFactory.detailsbonReceptionToDetailsBonReceptionDTO(domaine);
    }

    public List<DetailsBonReceptionDTO> saveList(List<DetailsBonReceptionDTO> dto) {
        List<DetailsBonReceptionDTO> detailsBonReceptionDTOs = dto;

        for (DetailsBonReceptionDTO detailsDto : detailsBonReceptionDTOs) {
            DetailsBonReception detailsDomaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(detailsDto, new DetailsBonReception()); // Assuming you have this factory method
            detailsDomaine.setDateCreate(new Date());
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
            detailsDomaine.setQteBesoin(detailsDto.getQteBesoin());
            detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
            detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
            if (detailsDomaine.getCodeArticle() != null) {
                detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
            } 
            detailsDomaine.setCodeDetailsOrderAchat(detailsDto.getCodeDetailsOrderAchat());
            if (detailsDomaine.getCodeDetailsOrderAchat() != null) {
                detailsDomaine.setDetailsOrderAchat(DetailsOrderAchatFactory.createOrderAchatByCode(detailsDto.getCodeDetailsOrderAchat()));
            } 
            detailsDomaine.setCodeBonReception(detailsDto.getCodeBonReception());
            if (detailsDomaine.getCodeBonReception() != null) {
                detailsDomaine.setBonReception(BonReceptionFactory.createBonReceptionByCode(detailsDto.getCodeBonReception()));
            }

            detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
            if (detailsDomaine.getCodeFournisseur() != null) {
                detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
            }
            detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
            if (detailsDomaine.getCodeUnite() != null) {
                detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
            } 
            detailsBonReceptionRepo.save(detailsDomaine); 
        }

        return detailsBonReceptionDTOs;
    }

    public DetailsBonReceptionDTO update(DetailsBonReceptionDTO dto) {
        DetailsBonReception domaine = detailsBonReceptionRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsBonReceptionFactory.detailsbonReceptionDTOTodetailsBonReception(dto, domaine);
        domaine = detailsBonReceptionRepo.save(domaine);
        DetailsBonReceptionDTO resultDTO = DetailsBonReceptionFactory.detailsbonReceptionToDetailsBonReceptionDTO(domaine);
        return resultDTO;
    }

    public void deleteDetailsBonReception(Integer code) {
        Preconditions.checkArgument(detailsBonReceptionRepo.existsById(code), "error.DetailsBonReceptionNotFound");
        detailsBonReceptionRepo.deleteById(code);
    }

}
