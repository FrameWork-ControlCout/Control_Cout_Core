/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.TraceSociete;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface TraceSocieteRepo extends JpaRepository<TraceSociete, Integer> {

    List<TraceSociete> findByCodeSociete(Integer codeSociete, Sort sort);

    Optional<TraceSociete> findTopByCodeSocieteOrderByDateCreateDesc(Integer codeSociete);

    TraceSociete findTopByCodeSocieteAndDateUpdateLessThanEqualOrderByDateUpdateDesc(Integer codeSociete, Date date);

    @Modifying
    void deleteByCodeSociete(Integer codeSociete);

}
