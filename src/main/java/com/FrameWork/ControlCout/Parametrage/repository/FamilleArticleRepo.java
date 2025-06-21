/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.FamilleArticle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface FamilleArticleRepo extends JpaRepository<FamilleArticle, Integer>{
        FamilleArticle findByCode(Integer code);
        
    List<FamilleArticle> findByActif(Boolean actif);
    
        List<FamilleArticle> findByActifAndType(Boolean actif,String type);

}
