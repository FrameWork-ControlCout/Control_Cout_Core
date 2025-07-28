/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.Depot;
import com.FrameWork.ControlCout.Stock.domaine.EtatStock;
import com.FrameWork.ControlCout.Stock.domaine.EtatStockPK;
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
public interface EtatStockRepo extends JpaRepository<EtatStock, EtatStockPK> {

 
//    @Query("SELECT es FROM EtatStock es JOIN FETCH  es.id det JOIN FETCH det.depot JOIN FETCH det.article WHERE det.depot = :depot")
//    List<EtatStock> findAllByDepot(@Param("depot") Depot depot);

}
