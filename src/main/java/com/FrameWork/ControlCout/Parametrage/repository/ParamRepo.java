/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.repository;
  
import com.FrameWork.ControlCout.Parametrage.domaine.param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface ParamRepo extends JpaRepository<param, Integer>{
    
//    List<param> findParamByCodeParamIn(Collection<String> codeParam);  
     param findParamByCodeParam(String codeParam);

    
}
