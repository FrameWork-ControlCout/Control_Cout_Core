/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.service;

import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import com.FrameWork.ControlCout.Cout.dto.DetailsConsoStandardDTO;
import com.FrameWork.ControlCout.Cout.factory.DetailsConsoStandardFactory;
import com.FrameWork.ControlCout.Cout.factory.ConsoStandardFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ArticleFactory;
import com.FrameWork.ControlCout.Parametrage.factory.UniteFactory;
import com.FrameWork.ControlCout.web.Util.Helper;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.FrameWork.ControlCout.Cout.repository.DetailsConsoStandardRepo;
import com.FrameWork.ControlCout.Cout.repository.ConsoStandardRepo;
import com.FrameWork.ControlCout.Parametrage.factory.SocieteFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.stream.Collectors;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author Administrator
 */
@Service
@Transactional
public class DetailsConsoStandardService {

    private final ConsoStandardRepo consoStandardRepo;
    private final DetailsConsoStandardRepo detailsConsoStandardRepo;

    public DetailsConsoStandardService(ConsoStandardRepo consoStandardRepo, DetailsConsoStandardRepo detailsConsoStandardRepo) {
        this.consoStandardRepo = consoStandardRepo;
        this.detailsConsoStandardRepo = detailsConsoStandardRepo;
    }

    @Transactional(readOnly = true)
    public List<DetailsConsoStandardDTO> findAllDetailsConsoStandard() {
        return DetailsConsoStandardFactory.listDetailsConsoStandardToDetailsConsoStandardDTOs(detailsConsoStandardRepo.findAll());

    }

    @Transactional(readOnly = true)
    public DetailsConsoStandardDTO findOne(Integer code) {
        DetailsConsoStandard domaine = detailsConsoStandardRepo.findByCode(code);
        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardNotFound");
        return DetailsConsoStandardFactory.DetailsConsoStandardToDetailsConsoStandardDTONew(domaine);
    }

    @Transactional(readOnly = true)
    public List<DetailsConsoStandardDTO> findByCodeConsoStandard(List<Integer> codeFacture) {
        List<DetailsConsoStandard> domaine = detailsConsoStandardRepo.findByCodeConsoStandardIn(codeFacture);
        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardNotFound");
        return DetailsConsoStandardFactory.listDetailsConsoStandardToDetailsConsoStandardDTOs(domaine);
    }
//     @Transactional(readOnly = true)
//    public List<DetailsConsoStandardDTO> findOneByCodeConsoStandard(Integer codeConsoStandar) {
//        DetailsConsoStandard domaine = detailsConsoStandardRepo.findAllByCodeConsoStandard(codeConsoStandar);
//        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardNotFound");
//        return DetailsConsoStandardFactory.listDetailsConsoStandardToDetailsConsoStandardDTOs(domaine);
//    }


//    @Transactional(readOnly = true)
//    public List<DetailsConsoStandardDTO> findByConsoStandardAndRecalculate(Integer codeConsoStandard, LocalDate userStartDate, LocalDate userEndDate) {
//
//        // 1. Fetch the parent ConsoStandard to get its full date range and person count
//        ConsoStandard parentConso = consoStandardRepo.findById(codeConsoStandard)
//                .orElseThrow(() -> new IllegalArgumentException("ConsoStandard not found with code: " + codeConsoStandard));
//
//        // 2. Fetch all original details for this ConsoStandard
//        List<DetailsConsoStandard> detailsList = detailsConsoStandardRepo.findByCodeConsoStandardIn(Collections.singletonList(codeConsoStandard));
//        if (detailsList.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 3. If no date range is provided by the user, return the DTOs with the original consTotal
//        if (userStartDate == null || userEndDate == null) {
//            return DetailsConsoStandardFactory.listDetailsConsoStandardToDetailsConsoStandardDTOs(detailsList);
//        }
//
//        // --- RECALCULATION LOGIC ---
//        // 4. Determine the number of days for the calculation
//        LocalDate consoStartDate = parentConso.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate consoEndDate = parentConso.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        // Find the intersection of the two date ranges
//        LocalDate effectiveStartDate = userStartDate.isBefore(consoStartDate) ? consoStartDate : userStartDate;
//        LocalDate effectiveEndDate = userEndDate.isAfter(consoEndDate) ? consoEndDate : userEndDate;
//
//        long numberOfDays = 0;
//        if (!effectiveStartDate.isAfter(effectiveEndDate)) {
//            numberOfDays = ChronoUnit.DAYS.between(effectiveStartDate, effectiveEndDate) + 1;
//        }
//
//        final long finalNumberOfDays = numberOfDays;
//
//        // 5. Create new DTOs with recalculated consTotal
//        return detailsList.stream().map(detail -> {
//            // Convert entity to DTO first
//            DetailsConsoStandardDTO dto = DetailsConsoStandardFactory.DetailsConsoStandardToDetailsConsoStandardDTONew(detail);
//
//            // Recalculate the total consumption for the new period
//            BigDecimal consUni = detail.getPlanRepa().getFicheTechnique().getDetailsFicheTechniques().iterator().next().getConsUni()!= null ? detail.getConsUni() : BigDecimal.ZERO;
//            BigDecimal nbrePerson = new BigDecimal(detail.getNbrePerson());
//            BigDecimal days = new BigDecimal(finalNumberOfDays);
//
//            // New Total = (Unit Consumption) * (Number of People) * (Number of Days)
//            BigDecimal recalculatedConsTotal = consUni.multiply(nbrePerson).multiply(days);
//
//            // Override the consTotal in the DTO
//            dto.setConsTotal(recalculatedConsTotal);
//
//            return dto;
//        }).collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public List<DetailsConsoStandardDTO> findByCodeConsoStandardAndHaveOA(List<Integer> codeFacture, boolean haveOA) {
        List<DetailsConsoStandard> domaine = detailsConsoStandardRepo.findByCodeConsoStandardInAndHaveOA(codeFacture, haveOA);
        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardNotFound");
        return DetailsConsoStandardFactory.listDetailsConsoStandardToDetailsConsoStandardDTOs(domaine);
    }

//
    public DetailsConsoStandardDTO save(DetailsConsoStandardDTO dto) {
        DetailsConsoStandard domaine = DetailsConsoStandardFactory.detailsConsoStandardDTOToDetailsConsoStandard(dto, new DetailsConsoStandard());
        domaine.setDateCreate(new Date());  // Set in domaine
        domaine.setUserCreate(Helper.getUserAuthenticated());
        domaine = detailsConsoStandardRepo.save(domaine);
        return DetailsConsoStandardFactory.DetailsConsoStandardToDetailsConsoStandardDTONew(domaine);
    }

    public List<DetailsConsoStandardDTO> saveList(List<DetailsConsoStandardDTO> dto) {
        List<DetailsConsoStandardDTO> detailsConsoStandardDTOs = dto;

        for (DetailsConsoStandardDTO detailsDto : detailsConsoStandardDTOs) {
            DetailsConsoStandard detailsDomaine = DetailsConsoStandardFactory.detailsConsoStandardDTOToDetailsConsoStandard(detailsDto, new DetailsConsoStandard()); // Assuming you have this factory method

            detailsDomaine.setDateCreate(new Date());
            detailsDomaine.setUserCreate(Helper.getUserAuthenticated());

            detailsDomaine.setNbrePerson(detailsDto.getNbrePerson());

//            detailsDomaine.setConsUni(detailsDto.getConsUni());
//            detailsDomaine.setConsTotal(detailsDto.getConsTotal());

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
            detailsDomaine.setCodeSociete(detailsDto.getCodeSociete());
            if (detailsDomaine.getCodeSociete() != null) {
                detailsDomaine.setSociete(SocieteFactory.createSocieteByCode(detailsDto.getCodeSociete()));
            }
            detailsConsoStandardRepo.save(detailsDomaine);
        }
        
        

        return detailsConsoStandardDTOs;
    }

    public DetailsConsoStandardDTO update(DetailsConsoStandardDTO dto) {
        DetailsConsoStandard domaine = detailsConsoStandardRepo.findByCode(dto.getCode());
        Preconditions.checkArgument(domaine != null, "error.DetailsConsoStandardNotFound");
        domaine = DetailsConsoStandardFactory.detailsConsoStandardDTOToDetailsConsoStandard(dto, domaine);
        domaine = detailsConsoStandardRepo.save(domaine);
        DetailsConsoStandardDTO resultDTO = DetailsConsoStandardFactory.DetailsConsoStandardToDetailsConsoStandardDTONew(domaine);
        return resultDTO;
    }

    public void deleteDetailsConsoStandard(Integer code) {
        Preconditions.checkArgument(detailsConsoStandardRepo.existsById(code), "error.DetailsConsoStandardNotFound");
        detailsConsoStandardRepo.deleteById(code);
    }

}
