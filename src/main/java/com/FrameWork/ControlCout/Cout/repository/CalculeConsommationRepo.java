/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.CalculeConsommation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CalculeConsommationRepo extends JpaRepository<CalculeConsommation, Integer> {

    CalculeConsommation findByCode(Integer code);

    List<CalculeConsommation> findByActif(Boolean actif);

//    @Query("SELECT p FROM CalculeConsommation p WHERE p.dateDebut >= :startDate AND p.dateFin < :endDate")
//    List<CalculeConsommation> findAllInDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
