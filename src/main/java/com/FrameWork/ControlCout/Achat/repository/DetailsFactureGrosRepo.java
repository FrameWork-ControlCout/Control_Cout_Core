/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FrameWork.ControlCout.Achat.repository;
  
import com.FrameWork.ControlCout.Achat.domaine.DetailsFactureGros;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface DetailsFactureGrosRepo extends JpaRepository<DetailsFactureGros, Integer> {

    DetailsFactureGros findByCode(Integer code);

    List<DetailsFactureGros> findByCodeFactureGros(Integer codeFactureGros);   
    List<DetailsFactureGros> findByCodeFournisseur(Integer codeFournisseur); 
    
    public void deleteByCodeFactureGros(Integer codeFacturegros);
}
