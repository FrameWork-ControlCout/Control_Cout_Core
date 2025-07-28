/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Parametrage.domaine.Societe;
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
public interface SocieteRepo extends JpaRepository<Societe, Integer> {

    Societe findByCode(Integer code);

    List<Societe> findByActif(Boolean actif);

    List<Societe> findByActifAndCode(Boolean actif, Integer code);
//        List<Societe> findByActifAndCodeIn(Boolean actif, List<Integer> code);

//    @Query("SELECT p FROM Societe p WHERE p.actif = :actif AND p.codeSociete in (:codeSociete)")
//    List<Societe> findByActifAndCodeSocieteIn(@Param("actif") Boolean actif, @Param("codeSociete") List<Integer> codeSociete);

    List<Societe> findByActifAndCodeDepot(Boolean actif, Integer codeDepot);

}
