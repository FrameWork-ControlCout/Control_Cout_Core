/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.repository;

import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementFournisseur;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface MouvementFournisseurRepo extends JpaRepository<MouvementFournisseur, Integer> {

    List<MouvementFournisseur> findByCodeFournisseur(Integer codeFournisseur);

    List<MouvementFournisseur> findByCodeFournisseurAndTypeMouvement(Integer codeFournisseur, String typeMouvement);

}
