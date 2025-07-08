/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.DetailsCalculeConsommation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsCalculeConsommationRepo extends JpaRepository<DetailsCalculeConsommation, Integer>{
    List<DetailsCalculeConsommation> findByCodeCalculeConsommation (Integer codeCalculeConsommation);
    public void deleteByCodeCalculeConsommation(Integer codeCalculeConsommation);
    
   DetailsCalculeConsommation findByCode (Integer code);
    

}
