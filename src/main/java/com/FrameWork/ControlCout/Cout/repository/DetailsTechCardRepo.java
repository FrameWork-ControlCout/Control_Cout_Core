/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.repository;

import com.FrameWork.ControlCout.Cout.domaine.DetailsTechCard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsTechCardRepo extends JpaRepository<DetailsTechCard, Integer>{
    List<DetailsTechCard> findByCodeTechCard (Integer codeTechCard);
    public void deleteByCodeTechCard(Integer codeTechCard);
    
   DetailsTechCard findByCode (Integer code);
    
}
