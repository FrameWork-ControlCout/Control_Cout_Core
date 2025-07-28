/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Achat.domaine.OrderAchat;
import com.FrameWork.ControlCout.Cout.domaine.DetailsConsoStandard;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsConsoStandardRepo extends JpaRepository<DetailsConsoStandard, Integer> {

    List<DetailsConsoStandard> findByCodeConsoStandardIn(List<Integer> codeConsoStandard);

    DetailsConsoStandard findAllByCodeConsoStandard(Integer codeConsoStandard);

//       @Query("SELECT ff FROM DetailsConsoStandard ff WHERE ff.code in(ff.codeConsoStandard)  ff.dateCreate BETWEEN ?1  AND ?2")
//    Collection<DetailsConsoStandard> findAllByCodeConsoStandardInAndDateCreateBetween(List<Integer> codeConsoStandard,LocalDate dateDebut, LocalDate dateFin);
    List<DetailsConsoStandard> findByCodeConsoStandardInAndCodeArticle(List<Integer> codeConsoStandard, Integer codeArticle);

    List<DetailsConsoStandard> findByCodeConsoStandardInAndCodeArticleIn(List<Integer> codeConsoStandard, List<Integer> codeArticle);

    List<DetailsConsoStandard> findByCodeConsoStandardInAndHaveOA(List<Integer> codeConsoStandard, boolean haveOA);

    List<DetailsConsoStandard> findByCodeConsoStandard(Integer codeConsoStandard);

    List<DetailsConsoStandard> findByCodeSociete(Integer codeSociete);

    @Modifying
    public void deleteByCodeConsoStandard(Integer codeConsoStandard);

    DetailsConsoStandard findByCode(Integer code);

}
