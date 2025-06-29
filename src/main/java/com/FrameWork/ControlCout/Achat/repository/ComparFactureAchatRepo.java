/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.ComparFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.FactureAchat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ComparFactureAchatRepo extends JpaRepository<ComparFactureAchat, Integer> {

    ComparFactureAchat findByCode(Integer code);

    List<ComparFactureAchat> findByCodeFournisseur(Integer codeFournisseur);

    List<ComparFactureAchat> findByCodeFournisseurCompar(Integer codeFournisseurCompar);

    @Query("SELECT ff FROM ComparFactureAchat ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<ComparFactureAchat> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

}
