/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;
 
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.MouvementCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.MouvementCaisseDTO;
import com.FrameWork.ControlCout.Tresorerie.repository.CaisseRepo;
import com.FrameWork.ControlCout.Tresorerie.repository.MouvementCaisseRepo;
import com.FrameWork.ControlCout.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class MouvementCaisseFactory {

    private final MouvementCaisseRepo mouvementCaisseRepo;

       private final CaisseRepo caisseRepo;

    public MouvementCaisseFactory(MouvementCaisseRepo mouvementCaisseRepo, CaisseRepo caisseRepo) {
        this.mouvementCaisseRepo = mouvementCaisseRepo;
        this.caisseRepo = caisseRepo;
    }
       
  

    
    

   
    

    public static MouvementCaisse createMouvementCaisseByCode(int code) {
        MouvementCaisse domaine = new MouvementCaisse();
        domaine.setCode(code);
        return domaine;
    }

    public static MouvementCaisse mouvementCaisseDTOToMouvementCaisse(MouvementCaisseDTO dto, MouvementCaisse domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setDebit(dto.getDebit());
            domaine.setCredit(dto.getCredit());

            domaine.setDateCreate(dto.getDateCreate());
            domaine.setUserCreate(dto.getUserCreate());
            domaine.setMntDevise(dto.getMntDevise());
            domaine.setCodeTier(dto.getCodeTier());

            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));

            }

            domaine.setCodeCaisseTr(dto.getCodeCaisseTr());

            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }

            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static MouvementCaisseDTO mouvementCaisseToMouvementCaisseDTO(MouvementCaisse domaine) {

        if (domaine != null) {
            MouvementCaisseDTO dto = new MouvementCaisseDTO();
            dto.setCode(domaine.getCode());
            dto.setCredit(domaine.getCredit());
            dto.setDebit(domaine.getDebit());
            dto.setMntDevise(domaine.getMntDevise());

            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

//            dto.setCaisseDTOTr(CaisseFactory.caisseToCaisseDTO(domaine.getCaisseTr()));
            dto.setCodeCaisseTr(domaine.getCodeCaisseTr());
//      Caisse caisse = new Caisse();
//        caisse = caisseRepo.getReferenceById(domaine.get(0).getCodeCaisseTr());
       
//                        dto.setDesignationCaisseTr(CaisseFactory.createCaisseByCode(domaine.getCodeCaisseTr()).getDesignationAr());

            dto.setCodeCaisseTr(domaine.getCodeCaisseTr());

            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
            dto.setCodeModeReglement(domaine.getCodeModeReglement());

            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
            dto.setCodeDevise(domaine.getCodeDevise());

            return dto;
        } else {
            return null;
        }
    }

    public static List<MouvementCaisseDTO> listMouvementCaisseToMouvementCaisseDTOs(List<MouvementCaisse> mouvementCaisses) {
        List<MouvementCaisseDTO> list = new ArrayList<>();
        for (MouvementCaisse mouvementCaisse : mouvementCaisses) {
            list.add(mouvementCaisseToMouvementCaisseDTO(mouvementCaisse));
        }
        return list;
    }

    public static Collection<MouvementCaisseDTO> CollectionmouvementCaissesTomouvementCaissesDTOsCollection(Collection<MouvementCaisse> mouvementCaisses) {
        List<MouvementCaisseDTO> dTOs = new ArrayList<>();
        mouvementCaisses.forEach(x -> {
            dTOs.add(mouvementCaisseToMouvementCaisseDTO(x));
        });
        return dTOs;

    }

//    public static List<MouvementCaisse> listMouvementCaisseToMouvementCaisseDTOsGrouped(List<SoldeCaisseDTO> mouvementCaisses) {
//        List<SoldeCaisseDTO> list = new ArrayList<>();
//        for (SoldeCaisseDTO mouvementCaisse : mouvementCaisses) {
//            list.add(mouvementCaisseToMouvementCaisseDTOGrouped(mouvementCaisse));
//        }
//        return list;
//    }
}
