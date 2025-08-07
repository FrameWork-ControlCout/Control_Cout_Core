/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureGros;
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import com.FrameWork.ControlCout.Achat.domaine.FactureGros;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsBonReceptionDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureGrosDTO;
import com.FrameWork.ControlCout.Achat.dto.DetailsOrderAchatDTO;
import com.FrameWork.ControlCout.Achat.dto.FactureGrosDTO;
import com.FrameWork.ControlCout.Achat.dto.OrderAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureGrosFactory;
import com.FrameWork.ControlCout.Achat.factory.DetailsOrderAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureGrosFactory;
import com.FrameWork.ControlCout.Achat.factory.OrderAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureGrosRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureGrosRepo;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.EtatReceptionFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.ArticleRepo;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
public class FactureGrosService {

    private final Logger log = LoggerFactory.getLogger(FactureGrosService.class);

    private final FactureGrosRepo factureGrosRepo;
    private final CompteurService compteurService;
    private final DetailsFactureGrosRepo detailsFactureGrosRepo;
    private final ArticleRepo articleRepo;

    public FactureGrosService(FactureGrosRepo factureGrosRepo, CompteurService compteurService, DetailsFactureGrosRepo detailsFactureGrosRepo, ArticleRepo articleRepo) {
        this.factureGrosRepo = factureGrosRepo;
        this.compteurService = compteurService;
        this.detailsFactureGrosRepo = detailsFactureGrosRepo;
        this.articleRepo = articleRepo;
    }

    @Transactional(readOnly = true)
    public List<FactureGrosDTO> findAllFactureGros() {
        return FactureGrosFactory.listFactureGrosToFactureGrosDTOs(
                factureGrosRepo.findAll(Sort.by("code").descending())
        );
    }

    @Transactional(readOnly = true)
    public Collection<FactureGrosDTO> findFactureGrosByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
        return FactureGrosFactory.CollectionFactureGrosToFactureGrosDTOs(
                factureGrosRepo.findAllByDateCreateBetween(dateDebut, dateFin)
        );
    }

    @Transactional(readOnly = true)
    public List<FactureGrosDTO> findOrderAchatByCodeFournisseur(Integer codeFournisseur) {
        return FactureGrosFactory.listFactureGrosToFactureGrosDTOs(
                factureGrosRepo.findByCodeFournisseur(codeFournisseur)
        );
    }

    @Transactional(readOnly = true)
    public FactureGrosDTO findOne(Integer code) {
        FactureGros domaine = factureGrosRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureGrosNotFound");
        return FactureGrosFactory.factureGrosToFactureGrosDTO(domaine);

    }

    public FactureGrosDTO save(FactureGrosDTO dto) {

        FactureGros domaine = FactureGrosFactory.factureGrosDTOToFactureGros(dto, new FactureGros());

        Compteur CompteurCodeSaisie = compteurService.findOne("FactureGrosEC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine.setDateCreate(new Date());
        domaine.setUsercreate(Helper.getUserAuthenticated());

        domaine = factureGrosRepo.save(domaine);

        //DetailsFactureGros 
        if (dto.getDetailsFactureGrosesDTOs() != null) {

            List<DetailsFactureGrosDTO> detailsFactureGroses = dto.getDetailsFactureGrosesDTOs();

            Set<Integer> articleCodes = detailsFactureGroses.stream()
                    .map(DetailsFactureGrosDTO::getCodeArticle)
                    .filter(Objects::nonNull) // Ensure we don't process null codes
                    .collect(Collectors.toSet());

            Map<Integer, Article> articleMap = articleRepo.findAllByCodeIn(articleCodes).stream()
                    .collect(Collectors.toMap(Article::getCode, article -> article));

            for (DetailsFactureGrosDTO detailsDto : detailsFactureGroses) {
                DetailsFactureGros detailsDomaine = DetailsFactureGrosFactory.detailsfactureGrosDTOToDetailsFactureGros(detailsDto, new DetailsFactureGros());
                detailsDomaine.setFactureGros(domaine);

                Article articleToUpdate = articleMap.get(detailsDto.getCodeArticle());

                if (articleToUpdate != null) {
                    // *** THE CORE LOGIC: Update the last price on the managed Article entity ***
                    articleToUpdate.setLastPrixGros(detailsDto.getPrixUni());
                    detailsDomaine.setArticle(articleToUpdate);
                }

                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }
                detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
                }

                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                detailsDomaine.setPrixUni(detailsDto.getPrixUni());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());

                detailsFactureGrosRepo.save(detailsDomaine);
            }
//            System.out.println("com.FrameWork.ControlCout.Achat.service.FactureGrosService.save()");
        }
        return FactureGrosFactory.factureGrosToFactureGrosDTOLazy(domaine);
    }

    public FactureGrosDTO update(FactureGrosDTO dto) {
        FactureGros domaine = factureGrosRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.FactureGrosEC");
        domaine = FactureGrosFactory.factureGrosDTOToFactureGros(dto, domaine);
        domaine = factureGrosRepo.save(domaine);
        detailsFactureGrosRepo.deleteByCodeFactureGros(domaine.getCode());

        //DetailsFactureGros 
        if (dto.getDetailsFactureGrosesDTOs() != null) {

            List<DetailsFactureGrosDTO> detailsFactureGroses = dto.getDetailsFactureGrosesDTOs();
            
                Set<Integer> articleCodes = detailsFactureGroses.stream()
                    .map(DetailsFactureGrosDTO::getCodeArticle)
                    .filter(Objects::nonNull) // Ensure we don't process null codes
                    .collect(Collectors.toSet());

            Map<Integer, Article> articleMap = articleRepo.findAllByCodeIn(articleCodes).stream()
                    .collect(Collectors.toMap(Article::getCode, article -> article));
            
            
            for (DetailsFactureGrosDTO detailsDto : detailsFactureGroses) {
                DetailsFactureGros detailsDomaine = DetailsFactureGrosFactory.detailsfactureGrosDTOToDetailsFactureGros(detailsDto, new DetailsFactureGros());
                detailsDomaine.setFactureGros(domaine);
                
                Article articleToUpdate = articleMap.get(detailsDto.getCodeArticle());

                if (articleToUpdate != null) {
                    // *** THE CORE LOGIC: Update the last price on the managed Article entity ***
                    articleToUpdate.setLastPrixGros(detailsDto.getPrixUni());
                    detailsDomaine.setArticle(articleToUpdate);
                }

                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }
                detailsDomaine.setCodeFournisseur(detailsDto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(detailsDto.getCodeFournisseur()));
                }

                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }
                detailsDomaine.setPrixUni(detailsDto.getPrixUni());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());

                detailsFactureGrosRepo.save(detailsDomaine);

            }
        }

        return FactureGrosFactory.factureGrosToFactureGrosDTOLazy(domaine);
    }

    public void deleteFactureGros(Integer code) {
        Preconditions.checkArgument(factureGrosRepo.existsById(code), "error.FactureGrosEC");
        detailsFactureGrosRepo.deleteByCodeFactureGros(code);
        factureGrosRepo.deleteById(code);
    }

}
