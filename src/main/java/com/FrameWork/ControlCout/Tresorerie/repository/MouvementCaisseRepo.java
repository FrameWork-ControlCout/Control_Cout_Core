/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.repository;
 
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse; 
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
public interface MouvementCaisseRepo extends JpaRepository<MouvementCaisse, Integer> {

    MouvementCaisse findByCode(Integer code);

    List<MouvementCaisse> findAllByOrderByDateCreateDesc();

    MouvementCaisse findMouvementCaisseByCodeSaisie(String codeSaisie);

    List<MouvementCaisse> findMouvementCaisseByCodeCaisse(Integer codeCaisse);

    boolean existsByCodeCaisse(Integer codeCaisse);

    @Modifying
    @Query("delete from MouvementCaisse det where det.codeSaisie=?1 ")
    public void deleteByCodeSaisie(String codeSaisie);

}
