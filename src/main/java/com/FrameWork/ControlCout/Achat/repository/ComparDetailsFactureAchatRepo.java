/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.ComparDetailsFactureAchat;
import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureAchat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ComparDetailsFactureAchatRepo extends JpaRepository<ComparDetailsFactureAchat, Integer> {

   ComparDetailsFactureAchat findByCode(Integer code);

     List<ComparDetailsFactureAchat> findByCodeFactureAchat(Integer codeFactureAchat); 
     
     List<ComparDetailsFactureAchat> findByCodeComparFactureAchat(Integer codeComparFactureAchat);

    List<ComparDetailsFactureAchat> findByCodeFournisseur(Integer codeFournisseur);

    public void deleteByCodeComparFactureAchat(Integer codeComparFactureAchat);

}
