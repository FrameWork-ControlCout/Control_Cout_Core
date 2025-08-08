/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.repository;
  
import com.FrameWork.ControlCout.Tresorerie.domaine.SoldeCaisse;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface SoldeCaisseRepo extends JpaRepository<SoldeCaisse, Integer> {
    
    
    SoldeCaisse findByCode(Integer code);

    List<SoldeCaisse> findByCodeDeviseIn(Collection<Integer> codeDevise);

    List<SoldeCaisse> findByCodeDeviseAndCodeCaisse(Integer codeDevise, Integer codeCaisse);

    SoldeCaisse findByCodeCaisse(Integer codeCaisse);

    void deleteByCodeCaisse(Integer codeCaisse);

}
