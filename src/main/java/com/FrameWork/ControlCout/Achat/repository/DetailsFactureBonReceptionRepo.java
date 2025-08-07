/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;

import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureBonReception;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsFactureBonReceptionRepo extends JpaRepository<DetailsFactureBonReception, Integer> {

    DetailsFactureBonReception findByCode(Integer code);

    List<DetailsFactureBonReception> findByCodeFactureBonReception(Integer codeFactureBonReception);

    List<DetailsFactureBonReception> findByCodeFournisseur(Integer codeFournisseur);

//    List<DetailsFactureBonReception> findByCodeDetailsOrderAchat(Integer codeDetailsOrderAchat);

    public void deleteByCodeFactureBonReception(Integer codeFactureBonReception);
}
