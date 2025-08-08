/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.repository;

import com.FrameWork.ControlCout.Tresorerie.domaine.AvanceFournisseur;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface AvanceFournisseurRepo extends JpaRepository<AvanceFournisseur, Integer> {

    List<AvanceFournisseur> findAllByOrderByCodeSaisieDesc();

    AvanceFournisseur findByCode(Integer code);

    List<AvanceFournisseur> findByCodeCaisseIn(Collection<Integer> codeCaisse);  
    
    List<AvanceFournisseur> findByCodeFournisseur(Integer codeFournisseur);


    List<AvanceFournisseur> findByCodeDeviseIn(Collection<Integer> codeDevise);
}
