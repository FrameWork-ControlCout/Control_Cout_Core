/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    Article findByCode(Integer code);

    List<Article> findByActif(Boolean actif);

    List<Article> findByActifAndCodeFamille(Boolean actif, Integer codeFamille);

    List<Article> findByCodeFamille(Integer codeFamille);

    List<Article> findByTypeAndActif(String type, Boolean actif);

    List<Article> findByType(String type);

}
