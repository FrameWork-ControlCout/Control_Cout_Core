/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandardPerDay;
import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsConsoStandardPerDayRepo extends JpaRepository<DetailsConsoStandardPerDay, Integer>{
    
    List<DetailsConsoStandardPerDay> findByCodeDetailsConsoConsommation( Integer codeDetailsConsoConsommation);   
    
    List<DetailsConsoStandardPerDay> findByCodeConsoStandardPerDay( Integer codeConsoStandardPerDay);

    
    
    @Query("SELECT p FROM DetailsConsoStandardPerDay p WHERE p.datePlan >= :startDate AND p.codeDetailsConsoConsommation = :codeDetailsConsoConsommation")
    List<DetailsConsoStandardPerDay> findAllDateAndCodeDetailsConsoConsommationIn(@Param("startDate") Date startDate, @Param("codeDetailsConsoConsommation") Integer codeDetailsConsoConsommation);
    
       List<DetailsConsoStandardPerDay> findByCodeSocieteAndDatePlanGreaterThanEqual(Integer codeSociete, Date startDate);
 
    @Modifying
    void deleteByCodeConsoStandardPerDay(Integer codeConsoStandardPerDay);
}
