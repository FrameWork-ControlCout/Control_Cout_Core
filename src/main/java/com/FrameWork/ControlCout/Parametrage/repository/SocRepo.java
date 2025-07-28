package com.FrameWork.ControlCout.Parametrage.repository;
 
import com.FrameWork.ControlCout.Parametrage.domaine.Soc; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
@Repository
public interface SocRepo extends JpaRepository<Soc, Integer> {

 
    Soc findFirstBy();
    
        Soc findByCode(Integer code);
 
}
