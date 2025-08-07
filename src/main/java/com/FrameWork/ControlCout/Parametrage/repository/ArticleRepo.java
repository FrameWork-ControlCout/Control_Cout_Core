/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    Article findByCode(Integer code);
    
        List<Article> findArticleByCodeIn(List<Integer> code);


    List<Article> findAllByCodeIn(Set<Integer> codes);

    List<Article> findByActif(Boolean actif);

    List<Article> findByActifAndCodeFamille(Boolean actif, Integer codeFamille);

    List<Article> findByCodeFamille(Integer codeFamille);

    List<Article> findByTypeAndActif(String type, Boolean actif);

    List<Article> findByType(String type);

    
    @Query("SELECT a FROM Article a JOIN FETCH a.unite WHERE a.type = :type AND a.actif = :actif")
List<Article> findAllArticleByTypeAndActif(@Param("type") String type, @Param("actif") Boolean actif);

}
