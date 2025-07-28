/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.Fournisseur;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface FournisseurRepo extends JpaRepository<Fournisseur, Integer> {

    Fournisseur findByCode(Integer code);

    List<Fournisseur> findByActif(Boolean actif);

    List<Fournisseur> findByActifAndGros(Boolean actif, Boolean gros);

}
