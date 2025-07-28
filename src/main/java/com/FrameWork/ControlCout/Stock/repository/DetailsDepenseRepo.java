/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Stock.repository;

import com.FrameWork.ControlCout.Stock.domaine.DetailsDepense;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsDepenseRepo extends JpaRepository<DetailsDepense, Integer> {

    DetailsDepense findByCode(Integer code);

    List<DetailsDepense> findByCodeDepense(Integer codeDepense);

      public void deleteByCodeDepense(Integer codeDepense);
}
