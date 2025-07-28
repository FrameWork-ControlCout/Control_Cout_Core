/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.OrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsOrderAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.OrderAchatRepo;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
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
public class DetailsOrderAchatService {

    private final OrderAchatRepo orderAchatRepo;
    private final DetailsOrderAchatRepo detailsOrderAchatRepo;

    public DetailsOrderAchatService(OrderAchatRepo orderAchatRepo, DetailsOrderAchatRepo detailsOrderAchatRepo) {
        this.orderAchatRepo = orderAchatRepo;
        this.detailsOrderAchatRepo = detailsOrderAchatRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsOrderAchatDTO> findAllDetailsOrderAchat() {
        return DetailsOrderAchatFactory.listOrderAchatToDetailsOrderAchatDTOs(detailsOrderAchatRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsOrderAchatDTO findOne(Integer code) {
        DetailsOrderAchat domaine = detailsOrderAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsOrderAchatNotFound");
        return DetailsOrderAchatFactory.detailsorderAchatToDetailsOrderAchatDTO(domaine);
    }
 

    
    
    @Transactional(readOnly = true)
    public List<DetailsOrderAchatDTO> findByCodeOrderAchat(Integer codeFacture) {
        List<DetailsOrderAchat> domaine = detailsOrderAchatRepo.findByCodeOrderAchat(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsOrderAchatNotFound");
        return DetailsOrderAchatFactory.listOrderAchatToDetailsOrderAchatDTOs(domaine);
    }
        @Transactional(readOnly = true)
    public List<DetailsOrderAchatDTO> findByCodeOrderAchatIn(List<Integer> codeOrderAchat) {
        List<DetailsOrderAchat> domaine = detailsOrderAchatRepo.findByCodeOrderAchatIn(codeOrderAchat);
        Preconditions.checkArgument(domaine != null, "error.DetailsOrderAchatNotFound");
        return DetailsOrderAchatFactory.listOrderAchatToDetailsOrderAchatDTOs(domaine);
    }
    
    
 
      @Transactional(readOnly = true)
    public List<DetailsOrderAchatDTO> findByCodeOrderAchatAndSatisfait(Integer codeFacture,Boolean satisfait) {
        List<DetailsOrderAchat> domaine = detailsOrderAchatRepo.findByCodeOrderAchatAndSatisfait(codeFacture,satisfait);
        Preconditions.checkArgument(domaine != null, "error.DetailsOrderAchatNotFound");
        return DetailsOrderAchatFactory.listOrderAchatToDetailsOrderAchatDTOs(domaine);
    }

//
    public DetailsOrderAchatDTO save(DetailsOrderAchatDTO dto) {
        DetailsOrderAchat domaine = DetailsOrderAchatFactory.detailsorderAchatDTOTodetailsOrderAchat(dto, new DetailsOrderAchat());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUsercreate(Helper.getUserAuthenticated());
        domaine = detailsOrderAchatRepo.save(domaine);
        return DetailsOrderAchatFactory.detailsorderAchatToDetailsOrderAchatDTO(domaine);
    }

    public List<DetailsOrderAchatDTO> saveList(List<DetailsOrderAchatDTO> dto) {
        List<DetailsOrderAchatDTO> detailsOrderAchatDTOs = dto;

        for (DetailsOrderAchatDTO detailsDto : detailsOrderAchatDTOs) {
            DetailsOrderAchat detailsDomaine = DetailsOrderAchatFactory.detailsorderAchatDTOTodetailsOrderAchat(detailsDto, new DetailsOrderAchat()); // Assuming you have this factory method

         
            detailsDomaine.setDateCreate(new Date());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
            detailsDomaine.setQteBesoin(detailsDto.getQteBesoin());
            detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());  
            detailsDomaine.setSatisfait(detailsDto.isSatisfait()); 


            detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
            if (detailsDomaine.getCodeArticle() != null) {
                detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
            }

            detailsDomaine.setCodeOrderAchat(detailsDto.getCodeOrderAchat());
            if (detailsDomaine.getCodeOrderAchat() != null) {
                detailsDomaine.setOrderAchat(OrderAchatFactory.createOrderAchatByCode(detailsDto.getCodeOrderAchat()));
            }

            detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
            if (detailsDomaine.getCodeFournisseur() != null) {
                detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
            }
            detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
            if (detailsDomaine.getCodeUnite() != null) {
                detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
            }

            detailsOrderAchatRepo.save(detailsDomaine);  

        }

        return detailsOrderAchatDTOs;
    }

    public DetailsOrderAchatDTO update(DetailsOrderAchatDTO dto) {
        DetailsOrderAchat domaine = detailsOrderAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = DetailsOrderAchatFactory.detailsorderAchatDTOTodetailsOrderAchat(dto, domaine);
        domaine = detailsOrderAchatRepo.save(domaine);
        DetailsOrderAchatDTO resultDTO = DetailsOrderAchatFactory.detailsorderAchatToDetailsOrderAchatDTO(domaine);
        return resultDTO;
    }

    public void deleteDetailsOrderAchat(Integer code) {
        Preconditions.checkArgument(detailsOrderAchatRepo.existsById(code), "error.DetailsOrderAchatNotFound");
        detailsOrderAchatRepo.deleteById(code);
    }

}
