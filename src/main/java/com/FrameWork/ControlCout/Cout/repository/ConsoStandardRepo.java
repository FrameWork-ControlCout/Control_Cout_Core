/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Cout.domaine.ConsoStandard;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ConsoStandardRepo extends JpaRepository<ConsoStandard, Integer> {

    ConsoStandard findByCode(Integer code);
        ConsoStandard findByCodeSociete(Integer codeSociete);
                List<ConsoStandard> findAllByCodeSociete(Integer codeSociete);



    List<ConsoStandard> findByActif(Boolean actif);
    
      


    @EntityGraph(attributePaths = {"detailsConsoStandards", "detailsConsoStandards.societe", "detailsConsoStandards.article"})
    ConsoStandard findWithDetailsByCode(Integer code);

// findAllBonReceptionByCodeIn
    @Query("SELECT a FROM ConsoStandard a JOIN FETCH a.societe JOIN FETCH a.detailsConsoStandardPerDays b  JOIN FETCH  b.article JOIN FETCH b.uniteSecondaire   WHERE a.code in (:code)")
    List<ConsoStandard> findAllConsoStandardByCodeIn(@Param("code") List<Integer> code);

    @Query("SELECT cs FROM ConsoStandard cs JOIN FETCH cs.societe s JOIN FETCH s.depot d "
            + "WHERE cs.actif = :actif AND d.code = :codeDepot")
    List<ConsoStandard> findByActifAndCodeDepotV2(@Param("actif") Boolean actif, @Param("codeDepot") Integer codeDepot);

    @Query("SELECT cs FROM ConsoStandard cs JOIN cs.detailsConsoStandards dcs "
            + "WHERE cs.actif = true AND cs.dateFin >= :currentDate AND dcs.codeSociete = :societeCode")
    List<ConsoStandard> findActivePlansBySocieteAndEndDateAfter(@Param("societeCode") Integer societeCode, @Param("currentDate") Date currentDate);

    List<ConsoStandard> findByCodeSocieteAndDateFinGreaterThanEqual(Integer codeSociete, Date dateFin);

     List<ConsoStandard> findByCodeSocieteAndActifIsTrueAndDateFinGreaterThanEqual(Integer codeSociete, Date aDate);
    
     
        @Query("SELECT cs FROM ConsoStandard cs WHERE cs.codeSociete = :societeCode AND cs.actif = true AND :planDate BETWEEN cs.dateDebut AND cs.dateFin")
    List<ConsoStandard> findActiveBySocieteAndDate(@Param("societeCode") Integer societeCode, @Param("planDate") Date planDate);
}
