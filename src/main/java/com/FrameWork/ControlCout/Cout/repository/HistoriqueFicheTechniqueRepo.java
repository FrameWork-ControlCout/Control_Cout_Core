/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.HistoriqueFicheTechnique; 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface HistoriqueFicheTechniqueRepo extends JpaRepository<HistoriqueFicheTechnique, Integer>{
     List<HistoriqueFicheTechnique> findByCodeFicheTechnique(Integer codeFicheTechnique);
          List<HistoriqueFicheTechnique> findByCodeArticle(Integer codeArticle);
          
                    List<HistoriqueFicheTechnique> findByCodeArticleAndCodeFicheTechnique(Integer codeArticle,Integer codeFicheTechnique);


}
