/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;

import com.FrameWork.ControlCout.Parametrage.domaine.Banque;
import com.FrameWork.ControlCout.Parametrage.domaine.CompositionUnite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface CompositionUniteRepo extends JpaRepository<CompositionUnite, Integer>{
      CompositionUnite findByCode(Integer code);
      
       CompositionUnite  findByCodeUnitePrinc (Integer codeUnitePrinc);
      
       
    public void deleteByCodeUnitePrinc(Integer codeUnitePrinc);
}
