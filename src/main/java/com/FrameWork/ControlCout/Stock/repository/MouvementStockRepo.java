/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.repository;

import com.FrameWork.ControlCout.Stock.domaine.MouvementStock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouvementStockRepo extends JpaRepository<MouvementStock, Integer> {

    List<MouvementStock> findByCodeOrigine(Integer codeOrigine);

    List<MouvementStock> findByCodeOrigineAndTypeMouvement(Integer codeOrigine, String typeMouvement);
   List<MouvementStock> findByCodeOrigineAndTypeMouvementAndCodeDepotSource(Integer codeOrigine, String typeMouvement,Integer codeDepotSource); 
 


}
