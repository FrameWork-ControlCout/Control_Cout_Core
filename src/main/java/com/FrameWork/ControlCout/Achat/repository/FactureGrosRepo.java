/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.FactureGros;  
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
public interface FactureGrosRepo extends JpaRepository<FactureGros, Integer> {

    @Query("SELECT ff FROM FactureGros ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<FactureGros> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);
 
    
        List<FactureGros> findByCodeFournisseur(Integer codeFournisseur);

       FactureGros findByCode(Integer code);

       
           @Query("SELECT CASE WHEN COUNT(br) > 0 THEN TRUE ELSE FALSE END FROM FactureGros br WHERE CONCAT(',', br.codeOrderAchat, ',') LIKE CONCAT('%,', :orderCode, ',%')")
    boolean existsByCodeOrderAchatContaining(@Param("orderCode") String orderCode);

    
}
