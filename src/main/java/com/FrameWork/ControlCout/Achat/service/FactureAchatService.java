/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.service;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import com.FrameWork.ControlCout.Achat.dto.DetailsFactureAchatDTO;
import com.FrameWork.ControlCout.Achat.dto.FactureAchatDTO;
import com.FrameWork.ControlCout.Achat.factory.DetailsFactureAchatFactory;
import com.FrameWork.ControlCout.Achat.factory.FactureAchatFactory;
import com.FrameWork.ControlCout.Achat.repository.DetailsFactureAchatRepo;
import com.FrameWork.ControlCout.Achat.repository.FactureAchatRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Banque;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.BanqueFactory;
import com.FrameWork.ControlCout.Parametrage.factory.FournisseurFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.BanqueRepo;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class FactureAchatService {

    private final Logger log = LoggerFactory.getLogger(FactureAchatService.class);

    private final FactureAchatRepo factureAchatRepo;
    private final DetailsFactureAchatRepo detailsFactureAchatRepo;
    private final BanqueRepo banqueRepo;
    private final CompteurService compteurService;

    public FactureAchatService(FactureAchatRepo factureAchatRepo, DetailsFactureAchatRepo detailsFactureAchatRepo, BanqueRepo banqueRepo, CompteurService compteurService) {
        this.factureAchatRepo = factureAchatRepo;
        this.detailsFactureAchatRepo = detailsFactureAchatRepo;
        this.banqueRepo = banqueRepo;
        this.compteurService = compteurService;
    }

    @Transactional(readOnly = true)
    public List<FactureAchatDTO> findAllFactureAchat() {

        List<FactureAchat> domaine = factureAchatRepo.findAll(Sort.by("code").descending());
        Map<Integer, Banque> banqueMap = new HashMap<>();
        List<Banque> allbanques = banqueRepo.findAll();
        for (Banque banque : allbanques) {
            banqueMap.put(banque.getCode(), banque);
        }
        List<FactureAchatDTO> factureAchatDTOs = new ArrayList<>();
        for (FactureAchat factureAchat : domaine) {
            FactureAchatDTO factureAchatDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(factureAchat);
            if (factureAchat.getCodeBanque() != null) {
                Banque banque = banqueMap.get(factureAchat.getCodeBanque());
                if (banque != null) {
                    factureAchatDTO.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(banque));
                }
            }
            factureAchatDTOs.add(factureAchatDTO);
        }
        return factureAchatDTOs;

    }

    @Transactional(readOnly = true)
    public List<FactureAchatDTO> findFactureAchatByEtatFacture(Integer codeEtatFacture) {
        List<FactureAchat> domaine = factureAchatRepo.findByCodeEtatFacture(codeEtatFacture);
        Map<Integer, Banque> banqueMap = new HashMap<>();
        List<Banque> allbanques = banqueRepo.findAll();
        for (Banque banque : allbanques) {
            banqueMap.put(banque.getCode(), banque);
        }
        List<FactureAchatDTO> factureAchatDTOs = new ArrayList<>();
        for (FactureAchat factureAchat : domaine) {
            FactureAchatDTO factureAchatDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(factureAchat);
            if (factureAchat.getCodeBanque() != null) {
                Banque banque = banqueMap.get(factureAchat.getCodeBanque());
                if (banque != null) {
                    factureAchatDTO.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(banque));
                }
            }
            factureAchatDTOs.add(factureAchatDTO);
        }
        return factureAchatDTOs;
    }

    @Transactional(readOnly = true)
    public List<FactureAchatDTO> findFactureAchatByFournisseur(Integer codeFournisseur) {
        List<FactureAchat> domaine = factureAchatRepo.findByCodeFournisseur(codeFournisseur);
        Map<Integer, Banque> banqueMap = new HashMap<>();
        List<Banque> allbanques = banqueRepo.findAll();
        for (Banque banque : allbanques) {
            banqueMap.put(banque.getCode(), banque);
        }
        List<FactureAchatDTO> factureAchatDTOs = new ArrayList<>();
        for (FactureAchat factureAchat : domaine) {
            FactureAchatDTO factureAchatDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(factureAchat);
            if (factureAchat.getCodeBanque() != null) {
                Banque banque = banqueMap.get(factureAchat.getCodeBanque());
                if (banque != null) {
                    factureAchatDTO.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(banque));
                }
            }
            factureAchatDTOs.add(factureAchatDTO);
        }
        return factureAchatDTOs;

    }

    @Transactional(readOnly = true)
    public List<FactureAchatDTO> findFactureAchatByFournisseurAndCodeEtatFacture(Integer codeFournisseur, Integer codeEtatFacture) {
        
 
        List<FactureAchat> domaine = factureAchatRepo.findByCodeFournisseurAndCodeEtatFacture(codeFournisseur, codeEtatFacture); 
        Map<Integer, Banque> banqueMap = new HashMap<>(); 
        List<Banque> allbanques = banqueRepo.findAll(); 
        for (Banque banque : allbanques) {
            banqueMap.put(banque.getCode(), banque); 
        } 
        List<FactureAchatDTO> factureAchatDTOs = new ArrayList<>();
        for (FactureAchat factureAchat : domaine) {
            FactureAchatDTO factureAchatDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(factureAchat);   
            if (factureAchat.getCodeBanque()!= null) {  
                Banque banque = banqueMap.get(factureAchat.getCodeBanque());
                if (banque != null) {
                    factureAchatDTO.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(banque));  
                }
            }
            factureAchatDTOs.add(factureAchatDTO);
        } 
        return factureAchatDTOs; 
        
    }

    @Transactional(readOnly = true)
    public Collection<FactureAchatDTO> findFactureByDateCreateBetween(
            LocalDate dateDebut,
            LocalDate dateFin) {
//        return FactureAchatFactory.CollectionFactureAchatToFactureAchatDTOs(
//                factureAchatRepo.findAllByDateCreateBetween(dateDebut, dateFin)
//        ); 

        Collection<FactureAchat> domaine = factureAchatRepo.findAllByDateCreateBetween(dateDebut, dateFin); 
        Map<Integer, Banque> banqueMap = new HashMap<>(); 
        List<Banque> allbanques = banqueRepo.findAll(); 
        for (Banque banque : allbanques) {
            banqueMap.put(banque.getCode(), banque); 
        } 
        List<FactureAchatDTO> factureAchatDTOs = new ArrayList<>();
        for (FactureAchat factureAchat : domaine) {
            FactureAchatDTO factureAchatDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(factureAchat);   
            if (factureAchat.getCodeBanque()!= null) {  
                Banque banque = banqueMap.get(factureAchat.getCodeBanque());
                if (banque != null) {
                    factureAchatDTO.setBanqueDTO(BanqueFactory.banqueToBanqueDTO(banque));  
                }
            }
            factureAchatDTOs.add(factureAchatDTO);
        } 
        return factureAchatDTOs;
    }

    @Transactional(readOnly = true)
    public FactureAchatDTO findOne(Integer code) {
        FactureAchat domaine = factureAchatRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.FactureAchatNotFound");
        return FactureAchatFactory.factureAchatToFactureAchatDTO(domaine);
        
        
    }

    public FactureAchatDTO save(FactureAchatDTO dto) {

        FactureAchat domaine = FactureAchatFactory.factureAchatDTOToFactureAchat(dto, new FactureAchat());

        if ("ECO".equals(dto.getType())) {
            Compteur CompteurCodeSaisie = compteurService.findOne("FactureAchatEC");
            String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
            domaine.setCodeSaisie(codeSaisieAC);
            compteurService.incrementeSuffixe(CompteurCodeSaisie);
        } else {
            Compteur CompteurCodeSaisie = compteurService.findOne("FactureAchatIMMO");
            String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
            domaine.setCodeSaisie(codeSaisieAC);
            compteurService.incrementeSuffixe(CompteurCodeSaisie);
        }

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = factureAchatRepo.save(domaine);

        //DetailsFactureAchat 
        if (dto.getDetailsFactureAchatsAchatDTOs() != null) {

            List<DetailsFactureAchatDTO> detailsFactureAchatDTOs = dto.getDetailsFactureAchatsAchatDTOs();
            for (DetailsFactureAchatDTO detailsDto : detailsFactureAchatDTOs) {
                DetailsFactureAchat detailsDomaine = DetailsFactureAchatFactory.detailsFactureAchatDTOToDetailsFactureAchat(detailsDto, new DetailsFactureAchat());
                detailsDomaine.setFactureAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
              
                
                detailsDomaine.setMontantHt(detailsDto.getMontantHt());
                detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
                detailsDomaine.setMontantTva(detailsDto.getMontantTva());
                detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setPrixUnitaire(detailsDto.getPrixUnitaire());

                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeFactureAchat(detailsDto.getCodeFactureAchat());
                if (detailsDomaine.getCodeFactureAchat() != null) {
                    detailsDomaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(detailsDto.getCodeFactureAchat()));
                }

                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
                }
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }

                detailsFactureAchatRepo.save(detailsDomaine); // Assuming you have a detailsFactureAchatRepo

            }

        }

        return FactureAchatFactory.factureAchatToFactureAchatDTO(domaine);
    }

    public void deleteFactureAchat(Integer code) {
        Preconditions.checkArgument(factureAchatRepo.existsById(code), "error.FactureAchatNotFound");
        factureAchatRepo.deleteById(code);
    }

    public FactureAchatDTO update(FactureAchatDTO dto) {
        FactureAchat domaine = factureAchatRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine = FactureAchatFactory.factureAchatDTOToFactureAchat(dto, domaine);
        domaine = factureAchatRepo.save(domaine);

        detailsFactureAchatRepo.deleteByCodeFactureAchat(domaine.getCode());

        if (dto.getDetailsFactureAchatsAchatDTOs() != null) {

            List<DetailsFactureAchatDTO> detailsFactureAchatDTOs = dto.getDetailsFactureAchatsAchatDTOs();
            for (DetailsFactureAchatDTO detailsDto : detailsFactureAchatDTOs) {
                DetailsFactureAchat detailsDomaine = DetailsFactureAchatFactory.detailsFactureAchatDTOToDetailsFactureAchat(detailsDto, new DetailsFactureAchat());
                detailsDomaine.setFactureAchat(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUsercreate(Helper.getUserAuthenticated());
                detailsDomaine.setMontantHt(detailsDto.getMontantHt());
                detailsDomaine.setMontantTTC(detailsDto.getMontantTTC());
                detailsDomaine.setMontantTva(detailsDto.getMontantTva());
                detailsDomaine.setQteReceptionner(detailsDto.getQteReceptionner());
                detailsDomaine.setCaracterstique(detailsDto.getCaracterstique());
                detailsDomaine.setPrixUnitaire(detailsDto.getPrixUnitaire());

                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeFactureAchat(detailsDto.getCodeFactureAchat());
                if (detailsDomaine.getCodeFactureAchat() != null) {
                    detailsDomaine.setFactureAchat(FactureAchatFactory.createFactureAchatByCode(detailsDto.getCodeFactureAchat()));
                }

                detailsDomaine.setCodeFournisseur(dto.getCodeFournisseur());
                if (detailsDomaine.getCodeFournisseur() != null) {
                    detailsDomaine.setFournisseur(FournisseurFactory.createFournisseurByCode(dto.getCodeFournisseur()));
                }
                detailsDomaine.setCodeUnite(detailsDto.getCodeUnite());
                if (detailsDomaine.getCodeUnite() != null) {
                    detailsDomaine.setUnite(UniteFactory.createUniteByCode(detailsDto.getCodeUnite()));
                }

                detailsFactureAchatRepo.save(detailsDomaine); // Assuming you have a detailsFactureAchatRepo

            }

        } else {
            throw new IllegalArgumentException("error.DetailsFactureNotFound");
        }

        FactureAchatDTO resultDTO = FactureAchatFactory.factureAchatToFactureAchatDTO(domaine);
        return resultDTO;
    }

}
