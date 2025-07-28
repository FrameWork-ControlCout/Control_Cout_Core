/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface PlanRepaRepo extends JpaRepository<PlanRepa, Integer> {

    PlanRepa findByCode(Integer code);

    List<PlanRepa> findByCodeFicheTechnique(Integer codeFicheTechnique);

    List<PlanRepa> findByCodeSociete(Integer codeSociete);

    @Query("SELECT p FROM PlanRepa p WHERE p.datePlan >= :startDate AND p.datePlan < :endDate AND p.codeSociete = :codeSociete AND p.traiter= :traiter")
    List<PlanRepa> findAllInDateRangeAndCodeSocieteAndTraiter(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("codeSociete") Integer codeSociete, @Param("traiter") Boolean traiter);

    @Query("SELECT p FROM PlanRepa p WHERE p.datePlan >= :startDate AND p.datePlan < :endDate AND p.codeSociete in (:codeSociete)")
    List<PlanRepa> findAllInDateRangeAndCodeSocieteIn(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("codeSociete") List<Integer> codeSociete);

    @Query("SELECT p FROM PlanRepa p WHERE p.datePlan >= :startDate AND p.codeSociete = :codeSociete")
    List<PlanRepa> findAllDateAndCodeSocieteIn(@Param("startDate") Date startDate, @Param("codeSociete") Integer codeSociete);

}
