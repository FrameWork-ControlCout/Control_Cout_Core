/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

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
public interface FactureAchatRepo extends JpaRepository<FactureAchat, Integer> {

    FactureAchat findByCode(Integer code);

    List<FactureAchat> findByCodeEtatFacture(Integer codeEtatFacture);

    List<FactureAchat> findByCodeFournisseur(Integer codeFournisseur);
    
        List<FactureAchat> findByCodeFournisseurAndCodeEtatFacture(Integer codeFournisseur,Integer codeEtatFacture);


    @Query("SELECT ff FROM FactureAchat ff WHERE   ff.dateCreate BETWEEN ?1  AND ?2")
    Collection<FactureAchat> findAllByDateCreateBetween(LocalDate dateDebut, LocalDate dateFin);

}
