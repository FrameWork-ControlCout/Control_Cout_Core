/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureAchat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsFactureAchatRepo extends JpaRepository<DetailsFactureAchat, Integer> {

   DetailsFactureAchat findByCode(Integer code);

     List<DetailsFactureAchat> findByCodeFactureAchat(Integer codeFactureAchat);

    List<DetailsFactureAchat> findByCodeFournisseur(Integer codeFournisseur);

    public void deleteByCodeFactureAchat(Integer codeFactureAchat);

}
