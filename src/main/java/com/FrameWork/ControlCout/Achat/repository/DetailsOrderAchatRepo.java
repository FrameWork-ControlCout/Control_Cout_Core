/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;
 
import com.FrameWork.ControlCout.Achat.domaine.DetailsOrderAchat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsOrderAchatRepo extends JpaRepository<DetailsOrderAchat, Integer> {

    DetailsOrderAchat findByCode(Integer code);

    List<DetailsOrderAchat> findByCodeOrderAchat(Integer codeOrderAchat);  
        List<DetailsOrderAchat> findByCodeOrderAchatIn(List<Integer> codeOrderAchat);  

    List<DetailsOrderAchat> findByCodeOrderAchatAndSatisfait(Integer codeOrderAchat,Boolean satisfait);
    
        List<DetailsOrderAchat> findByCodeOrderAchatInAndCodeArticle(List<Integer> codeOrderAchat,Integer codeArticle); 
 
   List<DetailsOrderAchat> findByCodeOrderAchatInAndCodeArticleIn(List<Integer> codesOrderAchat, List<Integer> codesArticle);

    List<DetailsOrderAchat> findByCodeFournisseur(Integer codeFournisseur);

    public void deleteByCodeOrderAchat(Integer codeOrderAchat);
}
