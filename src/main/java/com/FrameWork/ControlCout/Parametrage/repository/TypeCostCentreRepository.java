/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.TypeCostCentre;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrateur
 */
@Repository
public interface TypeCostCentreRepository extends JpaRepository<TypeCostCentre, Integer> {

    List<TypeCostCentre> findByActifIn(Collection<Boolean> actif);

    List<TypeCostCentre> findByCodeIn(Integer[] codes);

    List<TypeCostCentre> findByActif(Boolean actif);
}
