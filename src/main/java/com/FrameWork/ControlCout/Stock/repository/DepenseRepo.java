/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.repository;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Stock.domaine.Depense;
import java.time.LocalDate;
import java.util.Collection;
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
public interface DepenseRepo extends JpaRepository<Depense, Integer> {

    @Query("SELECT ff FROM Depense ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<Depense> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

    List<Depense> findByCodeConsoStandard (Integer codeConsoStandard);
    
    @Query("SELECT a FROM Depense a JOIN FETCH a.depotSource JOIN FETCH a.depotDestination  JOIN FETCH a.detailsDepenses b  JOIN FETCH  b.article JOIN FETCH b.unite   WHERE a.code = :code")
    List<Depense> findAllDepenseByCode(@Param("code") Integer code);

    Depense findByCode(Integer code);

}
