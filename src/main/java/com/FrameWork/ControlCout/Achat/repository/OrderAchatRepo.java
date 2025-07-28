/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;
 
import com.FrameWork.ControlCout.Achat.domaine.BonReception;
import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
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
public interface OrderAchatRepo extends JpaRepository<OrderAchat, Integer> {

    @Query("SELECT ff FROM OrderAchat ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<OrderAchat> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

    OrderAchat findByCode(Integer code);

    List<OrderAchat> findByCodeIn(List<Integer> code);

    List<OrderAchat> findByCodeEtatFacture(Integer codeEtatFacture);

    List<OrderAchat> findByCodeFournisseur(Integer codeFournisseur);

    List<OrderAchat> findByCodeFournisseurAndCodeEtatFacture(Integer codeFournisseur, Integer codeEtatFacture);
    
     @Query("SELECT a FROM OrderAchat a JOIN FETCH a.fournisseur JOIN FETCH a.detailsOrderAchats b  JOIN FETCH  b.article JOIN FETCH b.unite   WHERE a.code = :code")
    List<OrderAchat> findAllOrderAchatByCode(@Param("code") Integer code);


}
