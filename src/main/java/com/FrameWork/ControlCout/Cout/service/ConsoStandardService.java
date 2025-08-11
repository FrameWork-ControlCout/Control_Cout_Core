/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandardPerDay;
import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import com.FrameWork.ControlCout.Cout.domaine.FicheTech;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.ConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardPerDayDTO;
import com.FrameWork.ControlCout.Cout.dto.DispensationRequestDTO;
import com.FrameWork.ControlCout.Cout.dto.DispenseDetailDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.factory.DetailsConsoStandardPerDayFactory;
import com.FrameWork.ControlCout.Cout.repository.PlanRepaRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Compteur;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.Parametrage.service.CompteurService;
import com.FrameWork.ControlCout.web.Util.Helper;

import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Cout.repository.DetailsConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.repository.ConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.repository.DetailsConsoStandardPerDayRepo;
import com.FrameWork.ControlCout.Cout.repository.DetailsFicheTechRepo;
import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import com.FrameWork.ControlCout.Parametrage.repository.DepotRepo;
import com.FrameWork.ControlCout.Parametrage.repository.SocieteRepo;
import com.FrameWork.ControlCout.Parametrage.repository.TraceSocieteRepo;
import com.FrameWork.ControlCout.Stock.domaine.Depense;
import com.FrameWork.ControlCout.Stock.domaine.MouvementStock;
import com.FrameWork.ControlCout.Stock.repository.DepenseRepo;
import com.FrameWork.ControlCout.Stock.service.MouvementStockService;
import com.FrameWork.ControlCout.constants.AchatConstants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class ConsoStandardService {

    private final Logger log = LoggerFactory.getLogger(ConsoStandardService.class);

    private final ConsoStandardRepo consoStandardRepo;
    private final DetailsConsoStandardRepo detailsConsoStandardRepo;
    private final DetailsConsoStandardPerDayRepo detailsConsoStandardPerDayRepo;
    private final CompteurService compteurService;
    private final PlanRepaRepo planRepaRepo;
    private final MouvementStockService mouvementStockService;
    private final DepotRepo depotRepo;
    private final DepenseRepo depenseRepo;
    private final TraceSocieteRepo traceSocieteRepo;
    private final SocieteRepo societeRepo;
    private final DetailsFicheTechRepo detailsFicheTechRepo;

    public ConsoStandardService(ConsoStandardRepo consoStandardRepo, DetailsConsoStandardRepo detailsConsoStandardRepo, DetailsConsoStandardPerDayRepo detailsConsoStandardPerDayRepo, CompteurService compteurService, PlanRepaRepo planRepaRepo, MouvementStockService mouvementStockService, DepotRepo depotRepo, DepenseRepo depenseRepo, TraceSocieteRepo traceSocieteRepo, SocieteRepo societeRepo, DetailsFicheTechRepo detailsFicheTechRepo) {
        this.consoStandardRepo = consoStandardRepo;
        this.detailsConsoStandardRepo = detailsConsoStandardRepo;
        this.detailsConsoStandardPerDayRepo = detailsConsoStandardPerDayRepo;
        this.compteurService = compteurService;
        this.planRepaRepo = planRepaRepo;
        this.mouvementStockService = mouvementStockService;
        this.depotRepo = depotRepo;
        this.depenseRepo = depenseRepo;
        this.traceSocieteRepo = traceSocieteRepo;
        this.societeRepo = societeRepo;
        this.detailsFicheTechRepo = detailsFicheTechRepo;
    }

    @Transactional(readOnly = true)
    public List<ConsoStandardDTO> findAllConsoStandard() {

        List<ConsoStandard> domaine = consoStandardRepo.findAll(Sort.by("code").descending());

        return ConsoStandardFactory.listConsoStandardToConsoStandardDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public List<ConsoStandardDTO> findAllConsoStandardByActif(Boolean actif) {

        List<ConsoStandard> domaine = consoStandardRepo.findByActif(actif);

        return ConsoStandardFactory.listConsoStandardToConsoStandardDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public List<ConsoStandardDTO> findAllConsoStandardByActifAndCodeDepot(Boolean actif, Integer codeDepot) {

        List<ConsoStandard> domaine = consoStandardRepo.findByActifAndCodeDepotV2(actif, codeDepot);

        return ConsoStandardFactory.listConsoStandardToConsoStandardDTOs(domaine);

    }

    @Transactional(readOnly = true)
    public ConsoStandardDTO findAllConsoStandardBySociete(Integer codeSociete) {

        ConsoStandard domaine = consoStandardRepo.findByCodeSociete(codeSociete);

        return ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine);

    }

    @Transactional(readOnly = true)
    public ConsoStandardDTO findOne(Integer code) {
        ConsoStandard domaine = consoStandardRepo.findByCode(code);
        Preconditions.checkArgument(domaine.getCode() != null, "error.ConsoStandardNotFound");
        return ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine);

    }

    public ConsoStandardDTO save(ConsoStandardDTO dto) {

        ConsoStandard domaine = ConsoStandardFactory.consoStandardDTOToConsoStandard(dto, new ConsoStandard());

        Compteur CompteurCodeSaisie = compteurService.findOne("CodeSaisieConsoStandard");
        String codeSaisieAC = CompteurCodeSaisie.getPrefixe() + CompteurCodeSaisie.getSuffixe();
        domaine.setCodeSaisie(codeSaisieAC);
        compteurService.incrementeSuffixe(CompteurCodeSaisie);

        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());

        domaine = consoStandardRepo.save(domaine);

        Societe sc = societeRepo.findByCode(domaine.getCodeSociete());
        Integer nbrePers = sc.getNbrePerson();
        //DetailsConsoStandard 
        if (dto.getDetailsConsoStandardsDTOs() != null) {

            List<DetailsConsoStandardDTO> detailsConsoStandardDTOs = dto.getDetailsConsoStandardsDTOs();

            for (DetailsConsoStandardDTO detailsDto : detailsConsoStandardDTOs) {
                DetailsConsoStandard detailsDomaine = DetailsConsoStandardFactory.detailsConsoStandardDTOToDetailsConsoStandard(detailsDto, new DetailsConsoStandard());

                detailsDomaine.setConsoStandard(domaine);
                detailsDomaine.setDateCreate(new Date());
                detailsDomaine.setUserCreate(Helper.getUserAuthenticated());
                detailsDomaine.setSatisfait(detailsDto.isSatisfait());
                detailsDomaine.setHaveOA(detailsDto.isHaveOA());

                detailsDomaine.setNbrePerson(detailsDto.getNbrePerson());
                detailsDomaine.setCodeArticle(detailsDto.getCodeArticle());
                if (detailsDomaine.getCodeArticle() != null) {
                    detailsDomaine.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                detailsDomaine.setCodeConsoStandard(detailsDto.getCodeConsoStandard());
                if (detailsDomaine.getCodeConsoStandard() != null) {
                    detailsDomaine.setConsoStandard(ConsoStandardFactory.createConsoStandardByCode(detailsDto.getCodeConsoStandard()));
                }

                detailsDomaine.setCodeUniteConso(detailsDto.getCodeUniteConso());
                if (detailsDomaine.getCodeUniteConso() != null) {
                    detailsDomaine.setUniteConso(UniteFactory.createUniteByCode(detailsDto.getCodeUniteConso()));
                }

                if (detailsDto.getQteDispensee() != null) {
                    detailsDomaine.setQteDispensee(detailsDto.getQteDispensee());
                }

                detailsDomaine.setCodeSociete(detailsDto.getCodeSociete());
                if (detailsDomaine.getCodeSociete() != null) {
                    detailsDomaine.setSociete(SocieteFactory.createSocieteByCode(detailsDto.getCodeSociete()));
                }
                detailsConsoStandardRepo.save(detailsDomaine); // Assuming you have a detailsConsoStandardRepo

            }

            Set<Integer> allSocieteCodes = detailsConsoStandardDTOs.stream()
                    .map(DetailsConsoStandardDTO::getCodeSociete)
                    .collect(Collectors.toSet());

            // 2. Loop through each unique society code and update its PlanRepa records.
            for (Integer codeSoc : allSocieteCodes) {
                List<PlanRepa> planRepasToUpdate = planRepaRepo.findByCodeSociete(codeSoc);
                for (PlanRepa pldto : planRepasToUpdate) {
                    pldto.setTraiter(Boolean.TRUE);
                    planRepaRepo.save(pldto); // Or saveAll at the end for better performance
                }
            }

        } else {
            throw new IllegalArgumentException("error.DetailsFactureNotFound");
        }

        DetailsConsoStandard det = detailsConsoStandardRepo.findAllByCodeConsoStandard(domaine.getCode());

        System.out.println("com.FrameWork.ControlCout.Cout.service.ConsoStandardService.save()" + det.getCode());
        /// detailsPerDay
        if (dto.getDetailsConsoStandardPerDayDTOs() != null) {

            List<DetailsConsoStandardPerDayDTO> detailsConsoStandardPerDayDTOs = dto.getDetailsConsoStandardPerDayDTOs();

            for (DetailsConsoStandardPerDayDTO detailsDto : detailsConsoStandardPerDayDTOs) {
                DetailsConsoStandardPerDay consoStandardPerDay = DetailsConsoStandardPerDayFactory.detailsConsoStandardDPerDayTOToDetailsConsoStandardPerDay(detailsDto, new DetailsConsoStandardPerDay());

                consoStandardPerDay.setConsoStandard(domaine);
//                detailsDomaine.setDetailsConsoStandard(det);

                consoStandardPerDay.setCodeDetailsConsoConsommation(det.getCode());
                if (consoStandardPerDay.getCodeDetailsConsoConsommation() != null) {
                    consoStandardPerDay.setDetailsConsoStandard(det);
                }

                consoStandardPerDay.setCodeArticle(detailsDto.getCodeArticle());
                if (consoStandardPerDay.getCodeArticle() != null) {
                    consoStandardPerDay.setArticle(ArticleFactory.createArticleByCode(detailsDto.getCodeArticle()));
                }

                consoStandardPerDay.setCodeUniteSecondaire(detailsDto.getCodeUniteSecondaire());
                if (consoStandardPerDay.getCodeUniteSecondaire() != null) {
                    consoStandardPerDay.setUniteSecondaire(UniteFactory.createUniteByCode(detailsDto.getCodeUniteSecondaire()));
                }

                consoStandardPerDay.setCodeConsoStandardPerDay(detailsDto.getCodeConsoStandard());
                if (consoStandardPerDay.getCodeConsoStandardPerDay() != null) {
                    consoStandardPerDay.setConsoStandard(ConsoStandardFactory.createConsoStandardByCode(detailsDto.getCodeConsoStandard()));
                }

                consoStandardPerDay.setCodeSociete(detailsDto.getCodeSociete());
                if (consoStandardPerDay.getCodeSociete() != null) {
                    consoStandardPerDay.setSociete(SocieteFactory.createSocieteByCode(detailsDto.getCodeSociete()));
                }

                consoStandardPerDay.setDatePlan(detailsDto.getDatePlan());
                consoStandardPerDay.setConsUni(detailsDto.getConsUni());
                consoStandardPerDay.setConsTotal(detailsDto.getConsTotal());
                consoStandardPerDay.setNbrePreson(nbrePers);
                consoStandardPerDay.setSatisfait(det.isSatisfait());

                detailsConsoStandardPerDayRepo.save(consoStandardPerDay); // Assuming you have a detailsConsoStandardRepo

            }

        } else {
            throw new IllegalArgumentException("error.DetailsConsoPerDayNotFound");
        }

        return ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine);
    }

    private void creerConsoStandardPourJour(Integer codeSociete, Date date, Integer nbrePerson, List<PlanRepa> plans) {
        log.info("Création de la consommation pour société {}, date {}, avec {} personnes.", codeSociete, date, nbrePerson);

        if (plans == null || plans.isEmpty()) {
            log.warn("Aucun plan de repas, création de consommation annulée.");
            return;
        }

        // --- 1. TROUVER L'EN-TÊTE 'ConsoStandard' ---
        // On cherche une ConsoStandard existante qui couvre cette date.
        ConsoStandard masterConso = consoStandardRepo.findActiveBySocieteAndDate(codeSociete, date)
                .stream().findFirst().orElse(null);

        if (masterConso == null) {
            log.error("ERREUR CRITIQUE: Aucune ConsoStandard maîtresse trouvée pour société {} à la date {}. Le calcul ne peut pas continuer.", codeSociete, date);
            // Selon vos règles métier, vous pourriez en créer une ici, mais il est plus sûr de lever une exception.
            throw new IllegalStateException("Aucune ConsoStandard active pour la société " + codeSociete + " à la date " + date);
        }

        // --- 2. AGRÉGER LES BESOINS EN ARTICLES POUR LA JOURNÉE ---
        Map<Integer, BigDecimal> besoinsParArticle = new HashMap<>();
        for (PlanRepa plan : plans) {
            FicheTech repa = plan.getFicheTech();
            if (repa == null) {
                continue;
            }

            List<DetailsFicheTech> detailsDuRepa = detailsFicheTechRepo.findByCodeFicheTechnique(repa.getCode());
            for (DetailsFicheTech detailRepa : detailsDuRepa) {
                BigDecimal qtePourCeRepa = detailRepa.getConsUni().multiply(new BigDecimal(nbrePerson));
                besoinsParArticle.merge(detailRepa.getCodeArticle(), qtePourCeRepa, BigDecimal::add);
            }
        }
//        log.info("Besoins agrégés pour le jour : {}", besoinsParArticle);

        // --- 3. CRÉER LES DÉTAILS JOURNALIERS ET METTRE À JOUR LES TOTAUX ---
        for (Map.Entry<Integer, BigDecimal> besoin : besoinsParArticle.entrySet()) {
            Integer codeArticle = besoin.getKey();
            BigDecimal qteTotaleJour = besoin.getValue();
            BigDecimal qteUnitaire = (nbrePerson > 0)
                    ? qteTotaleJour.divide(new BigDecimal(nbrePerson), 4, BigDecimal.ROUND_HALF_UP)
                    : BigDecimal.ZERO;

            // 3a. Créer le détail journalier (DetailsConsoStandardPerDay)
            DetailsConsoStandardPerDay dailyDetail = new DetailsConsoStandardPerDay();
            dailyDetail.setConsoStandard(masterConso);
            dailyDetail.setCodeDetailsConsoConsommation(masterConso.getDetailsConsoStandards().iterator().next().getCode());

            dailyDetail.setCodeArticle(codeArticle);
            dailyDetail.setArticle(ArticleFactory.createArticleByCode(codeArticle));
            dailyDetail.setDatePlan(date);
            dailyDetail.setConsTotal(qteTotaleJour);
            dailyDetail.setConsUni(qteUnitaire);
            dailyDetail.setNbrePreson(nbrePerson);
            dailyDetail.setCodeSociete(codeSociete);
            dailyDetail.setSociete(SocieteFactory.createSocieteByCode(codeSociete));
                   

            // ... autres liens comme dans votre code 'save'

            detailsConsoStandardPerDayRepo.save(dailyDetail);

            // 3b. Mettre à jour le total dans 'DetailsConsoStandard' (le total de la période)
            DetailsConsoStandard totalDetail = detailsConsoStandardRepo
                    .findByCodeConsoStandardAndCodeArticle(masterConso.getCode(), codeArticle)
                    .orElse(null);

            if (totalDetail == null) {
                // Le total pour cet article n'existe pas encore, on le crée.
                totalDetail = new DetailsConsoStandard();
                totalDetail.setConsoStandard(masterConso);
                totalDetail.setCodeArticle(codeArticle);
                totalDetail.setArticle(ArticleFactory.createArticleByCode(codeArticle));
                totalDetail.setNbrePerson(nbrePerson); // Le nbre de personnes du jour
                totalDetail.setCodeSociete(codeSociete);
                totalDetail.setSociete(SocieteFactory.createSocieteByCode(codeSociete));
                totalDetail.setDateCreate(new Date());
                totalDetail.setUserCreate(Helper.getUserAuthenticated());
                totalDetail.setQteDispensee(qteTotaleJour); // La quantité est celle de ce jour
            } else {
                // Le total existe déjà, on AJOUTE la quantité de ce jour.
                BigDecimal qteExistante = totalDetail.getQteDispensee() != null ? totalDetail.getQteDispensee() : BigDecimal.ZERO;
                totalDetail.setQteDispensee(qteExistante.add(qteTotaleJour));
            }
            detailsConsoStandardRepo.save(totalDetail);
        }

        log.info("Création de la consommation terminée pour le {}.", date);
    }

    public void deleteConsoStandard(Integer code) {
        Preconditions.checkArgument(consoStandardRepo.existsById(code), "error.ConsoStandardNotFound");
        List<DetailsConsoStandard> detailsConsoStandards = detailsConsoStandardRepo.findByCodeConsoStandard(code);

        List<Depense> dp = depenseRepo.findByCodeConsoStandard(code);
        Preconditions.checkArgument(dp.isEmpty(), "error.ConsoStandardHaveDepense");

        if (detailsConsoStandards != null && !detailsConsoStandards.isEmpty()) {
            Set<Integer> allSocieteCodes = detailsConsoStandards.stream()
                    .map(DetailsConsoStandard::getCodeSociete)
                    .collect(Collectors.toSet());
            for (Integer codeSoc : allSocieteCodes) {
                List<PlanRepa> planRepasToReset = planRepaRepo.findByCodeSociete(codeSoc);
                for (PlanRepa pldto : planRepasToReset) {
                    pldto.setTraiter(Boolean.FALSE);
                    planRepaRepo.save(pldto);
                }
            }
        }
        detailsConsoStandardPerDayRepo.deleteByCodeConsoStandardPerDay(code);
        detailsConsoStandardRepo.deleteByCodeConsoStandard(code);
        consoStandardRepo.deleteById(code);
    }

    public ConsoStandardDTO update(ConsoStandardDTO dto) {
        ConsoStandard domaine = consoStandardRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.AlimentationCaisseNotFound");
        domaine.setActif(dto.isActif());
        domaine = consoStandardRepo.save(domaine);
        return ConsoStandardFactory.consoStandardToConsoStandardDTO(domaine);
    }

    @Transactional
    public ConsoStandardDTO dispense(DispensationRequestDTO dto) {
        log.info("Starting dispensation process for ConsoStandard code: {}", dto.getCodeConsoStandard());

        // 1. Basic Validations
        Preconditions.checkArgument(dto.getCodeConsoStandard() != null, "error.ConsoStandardCodeRequired");
        Preconditions.checkArgument(dto.getCodeDepotSource() != null, "error.DepotSourceRequired");
        Preconditions.checkArgument(dto.getCodeDepotDestination() != null, "error.DepotDestinationRequired");
        Preconditions.checkArgument(dto.getDetails() != null && !dto.getDetails().isEmpty(), "error.DispensationDetailsRequired");

        // +++ FETCH DEPOT ENTITIES +++
        Depot depotSource = depotRepo.findById(dto.getCodeDepotSource()).orElseThrow(() -> new IllegalArgumentException("Source Depot not found with code: " + dto.getCodeDepotSource()));
        Depot depotDestination = depotRepo.findById(dto.getCodeDepotDestination()).orElseThrow(() -> new IllegalArgumentException("Destination Depot not found with code: " + dto.getCodeDepotDestination()));

        ConsoStandard consoStandard = consoStandardRepo.findById(dto.getCodeConsoStandard())
                .orElseThrow(() -> new IllegalArgumentException("ConsoStandard not found with code: " + dto.getCodeConsoStandard()));

        for (DispenseDetailDTO detailDto : dto.getDetails()) {
            if (detailDto.getQteADispenser() == null || detailDto.getQteADispenser().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            DetailsConsoStandard detailToUpdate = consoStandard.getDetailsConsoStandards().stream()
                    .filter(d -> d.getCode().equals(detailDto.getCodeDetailsConsoStandard()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("DetailsConsoStandard line not found with code: " + detailDto.getCodeDetailsConsoStandard()));

//            BigDecimal qteRequise = detailToUpdate.getConsTotal();
            BigDecimal qteDejaDispensee = detailToUpdate.getQteDispensee() != null ? detailToUpdate.getQteDispensee() : BigDecimal.ZERO;
//            BigDecimal qteRestante = qteRequise.subtract(qteDejaDispensee);

//            if (detailDto.getQteADispenser().compareTo(qteRestante) > 0) {
//                throw new IllegalStateException("Cannot dispense quantity (" + detailDto.getQteADispenser() + ") greater than remaining quantity (" + qteRestante + ") for article: " + detailToUpdate.getArticle().getCodeSaisie());
//            }
// 
            detailToUpdate.setQteDispensee(qteDejaDispensee.add(detailDto.getQteADispenser()));
            detailToUpdate.setSatisfait(detailDto.getIsSatisfait());

            MouvementStock movement = new MouvementStock();
            movement.setArticle(detailToUpdate.getArticle());
            movement.setQuantite(detailDto.getQteADispenser());
            movement.setDepot(depotSource);
            movement.setDepotDestination(depotDestination);
            movement.setTypeMouvement(AchatConstants.TYPE_MVT_DISPENSATION.toString());
            movement.setCodeOrigine(consoStandard.getCode());

            mouvementStockService.createMouvement(movement);
        }

        consoStandardRepo.save(consoStandard);

        log.info("Dispensation process completed successfully for ConsoStandard code: {}", dto.getCodeConsoStandard());
        return ConsoStandardFactory.consoStandardToConsoStandardDTO(consoStandard);
    }

    @Transactional(readOnly = true)
    public List<ConsoStandard> findOneByCodeEdition(List<Integer> code) {
        List<ConsoStandard> consoStandards = consoStandardRepo.findAllConsoStandardByCodeIn(code);
        return consoStandards;
    }

    public void UpdatedNbrePerson(Integer codeSociete, Integer newNbrePerson) {
        // 1. Attempt to find the existing record.
        ConsoStandard cs = consoStandardRepo.findByCodeSociete(codeSociete);

        // 2. The CRITICAL check: Is the object itself null?
        if (cs != null) {
            // 3. If it's NOT null, it means a record was found. We can safely update it.
            log.info("Found existing ConsoStandard for societe code {}. Updating nbrePerson to {}.", codeSociete, newNbrePerson);
            cs.setNbrePerson(newNbrePerson);

            // 4. Save the updated entity.
            consoStandardRepo.save(cs);
        } else {
            // 5. If it IS null, it means no record was found. Log this and do nothing.
            //    This matches the behavior seen in your other log message: "No action taken."
            log.warn("No ConsoStandard found for societe code {}. No update will be performed.", codeSociete);
        }
    }

    public void recalculateFutureDailyConsumption(Integer codeSociete, Integer newNbrePerson, Date changeDate) {
        log.info("Starting recalculation for Societe code: {} with new NbrePerson: {} from date: {}",
                codeSociete, newNbrePerson, changeDate);

        // 1. Find all future daily consumption records for the given society.
        List<DetailsConsoStandardPerDay> futureRecords = detailsConsoStandardPerDayRepo
                .findByCodeSocieteAndDatePlanGreaterThanEqual(codeSociete, changeDate);

        if (futureRecords.isEmpty()) {
            log.info("No future daily consumption records found for Societe code: {}. No action taken.", codeSociete);
            return;
        }

        log.info("Found {} future daily records to recalculate.", futureRecords.size());

        // 2. Iterate through each record and update the calculation.
        for (DetailsConsoStandardPerDay dailyDetail : futureRecords) {

            // Update the number of people on the record itself
            dailyDetail.setNbrePreson(newNbrePerson);

            // Recalculate 'consTotal'
            BigDecimal consUni = dailyDetail.getConsUni();
            // We need the conversion rate from the Article
            BigDecimal conversionRate = dailyDetail.getArticle().getConversionRate();

            // Safety check to prevent division by zero or null pointer exceptions
            if (consUni != null && conversionRate != null && conversionRate.compareTo(BigDecimal.ZERO) > 0) {

                BigDecimal nbrePersonDecimal = new BigDecimal(newNbrePerson);

                // Formula: (Unit Consumption * Number of People) / Conversion Rate
                BigDecimal newConsTotal = consUni.multiply(nbrePersonDecimal)
                        .divide(conversionRate, 3, RoundingMode.HALF_UP);

                // Use your existing rounding helper method to ensure consistency
                dailyDetail.setConsTotal(roundUpToHalf(newConsTotal));

            } else {
                // If data is invalid, log a warning and set total to zero
                log.warn("Could not recalculate for DetailsConsoStandardPerDay code: {} due to invalid consUni or conversionRate.", dailyDetail.getCode());
                dailyDetail.setConsTotal(BigDecimal.ZERO);
            }
        }

        // 3. Save all the updated records in a single batch operation.
        detailsConsoStandardPerDayRepo.saveAll(futureRecords);
        log.info("Successfully recalculated and saved {} records.", futureRecords.size());
    }

    // Your existing roundUpToHalf method (ensure it's present)
    private BigDecimal roundUpToHalf(BigDecimal num) {
        if (num == null) {
            return BigDecimal.ZERO;
        }
        return num.multiply(new BigDecimal("2.0"))
                .setScale(0, RoundingMode.CEILING)
                .divide(new BigDecimal("2.0"));
    }

    public void recalculerPourDateSpecifique(Integer codeSociete, Date date) {
        log.info("Déclenchement du recalcul pour la société {} à la date {}", codeSociete, date);

        // --- 1. ANNULER LES CALCULS PRÉCÉDENTS POUR CETTE DATE ---
        List<DetailsConsoStandardPerDay> oldDailyDetails = detailsConsoStandardPerDayRepo.findByCodeSocieteAndDatePlanGreaterThanEqual(codeSociete, date);

        if (!oldDailyDetails.isEmpty()) {
            log.info("Annulation de {} anciens enregistrements de consommation pour le {}.", oldDailyDetails.size(), date);
            ConsoStandard masterConso = oldDailyDetails.get(0).getConsoStandard();

            for (DetailsConsoStandardPerDay oldDetail : oldDailyDetails) {
                // Soustraire l'ancienne quantité du total maître
                DetailsConsoStandard totalDetail = detailsConsoStandardRepo
                        .findByCodeConsoStandardAndCodeArticle(masterConso.getCode(), oldDetail.getCodeArticle())
                        .orElse(null);

                if (totalDetail != null && totalDetail.getQteDispensee() != null) {
                    BigDecimal nouvelleQte = totalDetail.getQteDispensee().subtract(oldDetail.getConsTotal());
                    totalDetail.setQteDispensee(nouvelleQte);
                    detailsConsoStandardRepo.save(totalDetail);
                }
            }

            // Supprimer les anciens détails journaliers
            detailsConsoStandardPerDayRepo.deleteAllInBatch(oldDailyDetails);
        }

        // --- 2. RÉCUPÉRER LES NOUVELLES DONNÉES D'ENTRÉE ---
        Integer nbrePersonPourCeJour = trouverNbrePersonPourDate(codeSociete, date);
        List<PlanRepa> plansDuJour = planRepaRepo.findByCodeSocieteAndDatePlanAndTraiterIsFalse(codeSociete, date);

        if (plansDuJour.isEmpty()) {
            log.info("Aucun PlanRepa trouvé pour la société {} à la date {}. Le recalcul est terminé (rien à calculer).", codeSociete, date);
            // Mettre à jour le statut 'traiter' à false si nécessaire pour les anciens plans supprimés
            return;
        }

        // --- 3. LANCER LE NOUVEAU CALCUL ---
        creerConsoStandardPourJour(codeSociete, date, nbrePersonPourCeJour, plansDuJour);

        // --- 4. MARQUER LES PLANS COMME TRAITÉS ---
        plansDuJour.forEach(plan -> plan.setTraiter(true));
        planRepaRepo.saveAll(plansDuJour);
    }

    /**
     * Trouve le nombre de personnes d'une société à une date donnée en se
     * basant sur l'historique.
     */
    private Integer trouverNbrePersonPourDate(Integer codeSociete, Date date) {
        // Cherche la dernière trace de changement AVANT ou À la date donnée.
        TraceSociete derniereTrace = traceSocieteRepo.findTopByCodeSocieteAndDateUpdateLessThanEqualOrderByDateUpdateDesc(codeSociete, date);

        if (derniereTrace != null) {
            return derniereTrace.getNbrePersonNew();
        } else {
            // Si aucune trace, on prend la valeur actuelle de la société (cas initial)
            Societe societe = societeRepo.findByCode(codeSociete);
            return (societe != null) ? societe.getNbrePerson() : 0;
        }
    }

}
