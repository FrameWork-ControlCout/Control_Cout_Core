/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Parametrage.domaine.Article;
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
public interface BonReceptionRepo extends JpaRepository<BonReception, Integer> {

    @Query("SELECT ff FROM BonReception ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<BonReception> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

    @Query("SELECT a FROM BonReception a JOIN FETCH a.fournisseur JOIN FETCH a.detailsBonReceptions b  JOIN FETCH  b.article JOIN FETCH b.unite   WHERE a.code = :code")
    List<BonReception> findAllBonReceptionByCode(@Param("code") Integer code);

    BonReception findByCode(Integer code);

    List<BonReception> findByCodeIn(List<Integer> code);

    List<BonReception> findByHaveFBR(Boolean haveFBR);

    List<BonReception> findByHaveFBRAndCodeFournisseur(Boolean haveFBR, Integer codeFournisseur);

    List<BonReception> findByCodeFournisseur(Integer codeFournisseur);

    List<BonReception> findByCodeOrderAchat(String codeOrderAchat);

    @Query("SELECT CASE WHEN COUNT(br) > 0 THEN TRUE ELSE FALSE END FROM BonReception br WHERE CONCAT(',', br.codeOrderAchat, ',') LIKE CONCAT('%,', :orderCode, ',%')")
    boolean existsByCodeOrderAchatContaining(@Param("orderCode") String orderCode);

}
