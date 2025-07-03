/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.ComparDetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.ComparFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import com.FrameWork.ControlCout.Achat.dto.ComparDetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.dto.ComparFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.ComparDetailsFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.ComparFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.ComparDetailsFactureAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.ComparFactureAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureAchatRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import com.FrameWork.ControlCout.Parametrage.domaine.Unite;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.ArticleRepo;
import com.FrameWork.ControlCout.Parametrage.repository.FournisseurRepo;
import com.FrameWork.ControlCout.Parametrage.repository.UniteRepo;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ComparFactureAchatService {

    private final Logger log = LoggerFactory.getLogger(ComparFactureAchatService.class);

    private final ComparFactureAchatRepo comparFactureAchatRepo;
    private final ComparDetailsFactureAchatRepo comparDetailsFactureAchatRepo;

    private final CompteurService compteurService;
    
     @Autowired private ArticleRepo articleRepo;
    @Autowired private FactureAchatRepo factureAchatRepo;
    @Autowired private FournisseurRepo fournisseurRepo;
    @Autowired private UniteRepo uniteRepo;

    public ComparFactureAchatService(ComparFactureAchatRepo comparFactureAchatRepo, ComparDetailsFactureAchatRepo comparDetailsFactureAchatRepo, CompteurService compteurService) {
        this.comparFactureAchatRepo = comparFactureAchatRepo;
        this.comparDetailsFactureAchatRepo = comparDetailsFactureAchatRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<ComparFactureAchatDTO> findAllComparFactureAchat() {

        List<ComparFactureAchat> domaine = comparFactureAchatRepo.findAll(Sort.by("code").descending());
        return ComparFactureAchatFactory.listComparFactureAchatToComparFactureAchatDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public List<ComparFactureAchatDTO> findComparFactureAchatByFournisseurCompar(Integer codeFournisseurCompar) {
        List<ComparFactureAchat> domaine = comparFactureAchatRepo.findByCodeFournisseurCompar(codeFournisseurCompar);
        return ComparFactureAchatFactory.listComparFactureAchatToComparFactureAchatDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public Collection<ComparFactureAchatDTO> findComparFactureByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {

        Collection<ComparFactureAchat> domaine = comparFactureAchatRepo.findAllByDateCreateBetween(dateDebut, dateFin);

        return ComparFactureAchatFactory.CollectionComparFactureAchatToComparFactureAchatDTOs(domaine);
    }

    @Transactional(readOnly = true)
    public ComparFactureAchatDTO findOne(Integer code) {
        ComparFactureAchat domaine = comparFactureAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureAchatNotFound");
        return ComparFactureAchatFactory.ComparfactureAchatToComparFactureAchatDTO(domaine);

    }

    public ComparFactureAchatDTO save(ComparFactureAchatDTO dto) {

        ComparFactureAchat domaine = ComparFactureAchatFactory.ComparfactureAchatDTOToComparFactureAchat(dto, new ComparFactureAchat());

        Compteur CompteurCodeSaisie = compteurService.findOne("ComparFactureAchatEC");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = comparFactureAchatRepo.save(domaine);

        if (dto.getDetailsComparFactureAchatsAchatDTOs() != null) {

            List<ComparDetailsFactureAchatDTO> detailsFactureAchatDTOs = dto.getDetailsComparFactureAchatsAchatDTOs();
            for (ComparDetailsFactureAchatDTO detailsDto : detailsFactureAchatDTOs) {
                ComparDetailsFactureAchat detailsDomaine = ComparDetailsFactureAchatFactory.detailsFactureAchatDTOToComparDetailsFactureAchat(detailsDto, new ComparDetailsFactureAchat());
                detailsDomaine.setComparFactureAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());

                detailsDomaine.setMontantTTCGros(detailsDto.getMontantTTCGros());
                detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
                detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setPrixUnitaire(detailsDto.getPrixUnitaire());
                detailsDomaine.setPrixUnitaireGros(detailsDto.getPrixUnitaireGros());

                detailsDomaine.setDiffPrixUni(detailsDto.getDiffPrixUni());

                detailsDomaine.setDiffPrixTotal(detailsDto.getDiffPrixTotal());

//                detailsDomaine.setCodeComparFactureAchat(detailsDto.getCodeComparFactureAchat());
//                if (detailsDomaine.getCodeComparFactureAchat() != null) {
//                    detailsDomaine.setComparFactureAchat(ComparFactureAchatFactory.createComparFactureAchatByCode(detailsDto.getCodeComparFactureAchat()));
//                }
//
//             
//                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
//                if (detailsDomaine.getCodeArticle() != null) {
//                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
//                }
//   System.out.println("detailsDto.getCodeArticle()    " + detailsDto.getCodeArticle());
//                detailsDomaine.setCodeFactureAchat(detailsDto.getCodeFactureAchat());
//                if (detailsDomaine.getCodeFactureAchat() != null) {
//                    detailsDomaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(detailsDto.getCodeFactureAchat()));
//                }
//
//                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
//                if (detailsDomaine.getCodeFournisseur() != null) {
//                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
//                }
//                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
//                if (detailsDomaine.getCodeUnite() != null) {
//                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
//                }


  if (detailsDto.getCodeArticle() != null) {
                Article article = articleRepo.findById(detailsDto.getCodeArticle())
                        .orElseThrow(() -> new RuntimeException("Article not found with code: " + detailsDto.getCodeArticle()));
                detailsDomaine.setArticle(article);
            }

            // Set the FactureAchat relationship
            if (detailsDto.getCodeFactureAchat() != null) {
                FactureAchat factureAchat = factureAchatRepo.findById(detailsDto.getCodeFactureAchat())
                        .orElseThrow(() -> new RuntimeException("FactureAchat not found with code: " + detailsDto.getCodeFactureAchat()));
                detailsDomaine.setFactureAchat(factureAchat);
            }
            
            // Set the Fournisseur relationship for the detail
            // Note: Your JSON has `codeFournisseur` on the detail level, but your old code used the header's `codeFournisseur`. 
            // I'll assume the detail's `codeFournisseur` is correct.
            if (detailsDto.getCodeFournisseur() != null) {
                 Fournisseur fournisseur = fournisseurRepo.findById(detailsDto.getCodeFournisseur())
                        .orElseThrow(() -> new RuntimeException("Fournisseur not found with code: " + detailsDto.getCodeFournisseur()));
                detailsDomaine.setFournisseur(fournisseur);
            }

            // Set the Unite relationship
            if (detailsDto.getCodeUnite() != null) {
                Unite unite = uniteRepo.findById(detailsDto.getCodeUnite())
                        .orElseThrow(() -> new RuntimeException("Unite not found with code: " + detailsDto.getCodeUnite()));
                detailsDomaine.setUnite(unite);
            }

                comparDetailsFactureAchatRepo.save(detailsDomaine); // Assuming you have a detailsFactureAchatRepo

            }

        }

        return ComparFactureAchatFactory.ComparfactureAchatToComparFactureAchatDTO(domaine);
    }

    public void deleteComparFactureAchat(Integer code) {
        Preconditions.checkArgument(comparFactureAchatRepo.existsById(code), "error.FactureAchatNotFound");
        comparFactureAchatRepo.deleteById(code);
    }

    public ComparFactureAchatDTO update(ComparFactureAchatDTO dto) {
        ComparFactureAchat domaine = comparFactureAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = ComparFactureAchatFactory.ComparfactureAchatDTOToComparFactureAchat(dto, domaine);
        domaine = comparFactureAchatRepo.save(domaine);

        comparDetailsFactureAchatRepo.deleteByCodeComparFactureAchat(domaine.getCode());

        if (dto.getDetailsComparFactureAchatsAchatDTOs() != null) {

            List<ComparDetailsFactureAchatDTO> comparDetailsFactureAchatDTOs = dto.getDetailsComparFactureAchatsAchatDTOs();
            for (ComparDetailsFactureAchatDTO detailsDto : comparDetailsFactureAchatDTOs) {
                ComparDetailsFactureAchat detailsDomaine = ComparDetailsFactureAchatFactory.detailsFactureAchatDTOToComparDetailsFactureAchat(detailsDto, new ComparDetailsFactureAchat());
                detailsDomaine.setComparFactureAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());

                detailsDomaine.setMontantTTCGros(detailsDto.getMontantTTCGros());
                detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
                detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setPrixUnitaire(detailsDto.getPrixUnitaire());
                detailsDomaine.setPrixUnitaireGros(detailsDto.getPrixUnitaireGros());

                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeFactureAchat(detailsDto.getCodeFactureAchat());
                if (detailsDomaine.getCodeFactureAchat() != null) {
                    detailsDomaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(detailsDto.getCodeFactureAchat()));
                }

                detailsDomaine.setCodeComparFactureAchat(detailsDto.getCodeComparFactureAchat());
                if (detailsDomaine.getCodeComparFactureAchat() != null) {
                    detailsDomaine.setComparFactureAchat(ComparFactureAchatFactory.createComparFactureAchatByCode(detailsDto.getCodeComparFactureAchat()));
                }

                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
                }

                detailsDomaine.setCodeFournisseurCompar(dto.getCodeFournisseurCompar());
                if (detailsDomaine.getCodeFournisseurCompar() != null) {
                    detailsDomaine.setFournisseurCompar(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseurCompar()));
                }

                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }

                comparDetailsFactureAchatRepo.save(detailsDomaine); // Assuming you have a detailsFactureAchatRepo

            }

        } else {
            throw new IllegalArgumentException("error.DetailscomparFactureNotFound");
        }

        ComparFactureAchatDTO resultDTO = ComparFactureAchatFactory.ComparfactureAchatToComparFactureAchatDTO(domaine);
        return resultDTO;
    }

}
