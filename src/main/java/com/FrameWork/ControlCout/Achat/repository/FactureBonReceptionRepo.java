/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;
 
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.FactureBonReception;
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
public interface FactureBonReceptionRepo extends JpaRepository<FactureBonReception, Integer> {

    @Query("SELECT ff FROM FactureBonReception ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<FactureBonReception> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

    FactureBonReception findByCode(Integer code);
//        List<FactureBonReception> findAllByCode(Integer code);

    @Query("SELECT a FROM FactureBonReception a JOIN FETCH a.fournisseur JOIN FETCH a.detailsFactureBonReceptions b  JOIN FETCH  b.article JOIN FETCH b.unite   WHERE a.code = :code")
    List<FactureBonReception> findAllByCode(@Param("code") Integer code);
        
    List<FactureBonReception> findByCodeFournisseur(Integer codeFournisseur);
    
  
     
//    List<FactureBonReception> findByCodeOrderAchat(String codeOrderAchat);
    
       @Query("SELECT CASE WHEN COUNT(br) > 0 THEN TRUE ELSE FALSE END FROM FactureBonReception br WHERE CONCAT(',', br.codeBonReception, ',') LIKE CONCAT('%,', :receptionCode, ',%')")
    boolean existsByCodeBonReceptionContaining(@Param("receptionCode") String receptionCode);
 

}
