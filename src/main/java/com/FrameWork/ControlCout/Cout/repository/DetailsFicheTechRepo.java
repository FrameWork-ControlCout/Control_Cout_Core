/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.DetailsFicheTech;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsFicheTechRepo extends JpaRepository<DetailsFicheTech, Integer>{
    List<DetailsFicheTech> findByCodeFicheTechnique (Integer codeFicheTechnique);
    
        List<DetailsFicheTech> findByCodeArticleIn (List<Integer> codeArticle);

    public void deleteByCodeFicheTechnique(Integer codeFicheTechnique);
    
   DetailsFicheTech findByCode (Integer code);
    
}
