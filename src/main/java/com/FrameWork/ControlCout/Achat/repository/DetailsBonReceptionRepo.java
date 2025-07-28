/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;
 
import com.FrameWork.ControlCout.Achat.domaine.DetailsBonReception;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsBonReceptionRepo extends JpaRepository<DetailsBonReception, Integer> {

    DetailsBonReception findByCode(Integer code);

    List<DetailsBonReception> findByCodeBonReception(Integer codeBonReception);  
//    List<DetailsBonReception> findByCodeBonReceptionAndSatisfait(Integer codeBonReception,Boolean satisfait);


    List<DetailsBonReception> findByCodeFournisseur(Integer codeFournisseur);
  List<DetailsBonReception> findByCodeDetailsOrderAchat(Integer codeDetailsOrderAchat);
    
    public void deleteByCodeBonReception(Integer codeBonReception);
}
