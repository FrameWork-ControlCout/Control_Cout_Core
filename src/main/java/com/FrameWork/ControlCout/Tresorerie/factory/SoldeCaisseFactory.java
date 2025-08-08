/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;
 
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.SoldeCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.SoldeCaisseDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class SoldeCaisseFactory {

    public static SoldeCaisse createSoldeCaisseByCode(int code) {
        SoldeCaisse domaine = new SoldeCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static SoldeCaisse soldeCaisseDTOToSoldeCaisse(SoldeCaisse domaine, SoldeCaisseDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCredit(dto.getCredit());
            domaine.setDebit(dto.getDebit());
            domaine.setDateUpdated(dto.getDateUpdated());
            domaine.setUserUpdated(dto.getUserUpdated());

            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }
            return domaine;
        } else {
            return null;
        }
    }

    public static SoldeCaisseDTO soldeCaisseToSoldeCaisseDTO(SoldeCaisse domaine) {

        if (domaine != null) {
            SoldeCaisseDTO dto = new SoldeCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setDebit(domaine.getDebit());
            dto.setCredit(domaine.getCredit());
            dto.setDateUpdated(domaine.getDateUpdated());
            dto.setUserUpdated(domaine.getUserUpdated());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());
            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            return dto;
        } else {
            return null;
        }
    }
    
    
       
    public static List<SoldeCaisseDTO> listSoldeCaisseToSoldeCaisseDTOs(List<SoldeCaisse> soldeCaisses) {
        List<SoldeCaisseDTO> list = new ArrayList<>();
        for (SoldeCaisse soldeCaisse : soldeCaisses) {
            list.add(soldeCaisseToSoldeCaisseDTO(soldeCaisse));
        }
        return list;
    }

    public static Collection<SoldeCaisseDTO> CollectionsoldeCaissesTosoldeCaissesDTOsCollection(Collection<SoldeCaisse> soldeCaisses) {
        List<SoldeCaisseDTO> dtos = new ArrayList<>();
        soldeCaisses.forEach(x -> {
            dtos.add(soldeCaisseToSoldeCaisseDTO(x));
        });
        return dtos;

    }
    
    public static SoldeCaisse SoldeCaisseDTOToSoldeCaisseUpdated(SoldeCaisseDTO Dto, SoldeCaisse domaine) {
        if (Dto != null) {
            domaine.setCode(Dto.getCode());
            domaine.setDebit(Dto.getDebit());   
            domaine.setCredit(Dto.getCredit()); 
            return domaine;
        } else {
            return null;
        }
    }
    
    public static SoldeCaisse soldeCaisseDTOToSoldeCaisseForUpdate(SoldeCaisse domaine) {
        SoldeCaisseDTO dto = new SoldeCaisseDTO();
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCredit(dto.getCredit());
            domaine.setDebit(dto.getDebit());
            domaine.setDateUpdated(dto.getDateUpdated());
            domaine.setUserUpdated(dto.getUserUpdated());

            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }
            return domaine;
        } else {
            return null;
        }
    }
    
    
}
