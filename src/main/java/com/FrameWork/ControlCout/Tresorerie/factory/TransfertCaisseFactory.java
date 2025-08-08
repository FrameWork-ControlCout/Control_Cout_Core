/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Tresorerie.factory;
 
import com.FrameWork.ControlCout.Parametrage.factory.DeviseFactory;
import com.FrameWork.ControlCout.Parametrage.factory.ModeReglementFactory;
import com.FrameWork.ControlCout.Tresorerie.domaine.TransfertCaisse;
import com.FrameWork.ControlCout.Tresorerie.dto.TransfertCaisseDTO;
import com.FrameWork.ControlCout.web.Util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List; 
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class TransfertCaisseFactory {
//
//    public static TransfertCaisse createTransfertCaisseByCode(int code) {
//        TransfertCaisse domaine = new TransfertCaisse();
//        domaine.setCode(code);
//        return domaine;
//    }
//
//    public static TransfertCaisse transfertCaisseDTOToTransfertCaisse(TransfertCaisseDTO dto, TransfertCaisse domaine) {
//        if (dto != null) {
//            domaine.setCode(dto.getCode());
//
//            domaine.setCodeSaisie(dto.getCodeSaisie());
//            domaine.setMontant(dto.getMontant());
//            domaine.setMontantEnDevise(dto.getMontantEnDevise());
//
//            domaine.setDateCreate(dto.getDateCreate());
//            domaine.setUserCreate(dto.getUserCreate());
//            domaine.setTauxChange(dto.getTauxChange());
//            domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
//
//            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseEntreeRequired");
//            domaine.setCodeCaisse(dto.getCodeCaisse());
//            if (domaine.getCodeCaisse() != null) {
//                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse())); 
//            }
//              Preconditions.checkBusinessLogique(dto.getCodeCaisseTr()!= null, "error.CaisseSortieRequired");
//            domaine.setCodeCaisseTr(dto.getCodeCaisseTr());
//            if (domaine.getCodeCaisseTr() != null) {
//                domaine.setCaisseTr(CaisseFactory.createCaisseByCode(dto.getCodeCaisseTr())); 
//            }
//
//         
//
//            domaine.setCodeDevise(dto.getCodeDevise());
//            if (domaine.getCodeDevise() != null) {
//                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));
//
//            }
//              domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
//            if (domaine.getCodeEtatApprouver() != null) {
//                domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
//
//            }
//
//            domaine.setCodeModeReglement(dto.getCodeModeReglement());
//            if (domaine.getCodeModeReglement() != null) {
//                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));
//
//            }
//
//            return domaine;
//        } else {
//            return null;
//        }
//    }
//
//    public static TransfertCaisseDTO transfertCaisseToTransfertCaisseDTO(TransfertCaisse domaine) {
//
//        if (domaine != null) {
//            TransfertCaisseDTO dto = new TransfertCaisseDTO();
//            dto.setCode(domaine.getCode());
//
//            dto.setCodeSaisie(domaine.getCodeSaisie());
//            dto.setDateCreate(domaine.getDateCreate());
//            dto.setUserCreate(domaine.getUserCreate());
//            dto.setTauxChange(domaine.getTauxChange());      
//            dto.setObservation(domaine.getObservation());    
//            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());    
//            
//            dto.setMontant(domaine.getMontant());  
//            dto.setMontantEnDevise(domaine.getMontantEnDevise());
//
//
//
//
//            
//
//            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
//            dto.setCodeCaisse(domaine.getCodeCaisse());
//
//            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
//            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());
//
//            dto.setCaisseTrDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisseTr()));
//            dto.setCodeCaisseTr(domaine.getCodeCaisseTr());
//
//            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
//            dto.setCodeModeReglement(domaine.getCodeModeReglement());
//
//            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
//            dto.setCodeDevise(domaine.getCodeDevise());
//
//            return dto;
//        } else {
//            return null;
//        }
//    }
//
//    public static List<TransfertCaisseDTO> listTransfertCaisseToTransfertCaisseDTOs(List<TransfertCaisse> transfertCaisses) {
//        List<TransfertCaisseDTO> list = new ArrayList<>();
//        for (TransfertCaisse transfertCaisse : transfertCaisses) {
//            list.add(transfertCaisseToTransfertCaisseDTO(transfertCaisse));
//        }
//        return list;
//    }
//
//    public static Collection<TransfertCaisseDTO> CollectiontransfertCaissesTotransfertCaissesDTOsCollection(Collection<TransfertCaisse> transfertCaisses) {
//        List<TransfertCaisseDTO> dTOs = new ArrayList<>();
//        transfertCaisses.forEach(x -> {
//            dTOs.add(transfertCaisseToTransfertCaisseDTO(x));
//        });
//        return dTOs;
//
//    }

    public static TransfertCaisse transfertCaisseDTOToTransfertCaisse(TransfertCaisse domaine, TransfertCaisseDTO dto) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setCodeSaisie(dto.getCodeSaisie());
            domaine.setMontant(dto.getMontant());
            domaine.setMontantEnDevise(dto.getMontantEnDevise());
 
            domaine.setTauxChange(dto.getTauxChange());
            domaine.setCodeUserApprouver(dto.getCodeUserApprouver());

            Preconditions.checkBusinessLogique(dto.getCodeCaisse() != null, "error.CaisseEntreeRequired");
            domaine.setCodeCaisse(dto.getCodeCaisse());
            if (domaine.getCodeCaisse() != null) {
                domaine.setCaisse(CaisseFactory.createCaisseByCode(dto.getCodeCaisse()));
            }
            Preconditions.checkBusinessLogique(dto.getCodeCaisseTr() != null, "error.CaisseSortieRequired");
            domaine.setCodeCaisseTr(dto.getCodeCaisseTr());
            if (domaine.getCodeCaisseTr() != null) {
                domaine.setCaisseTr(CaisseFactory.createCaisseByCode(dto.getCodeCaisseTr()));
            }

            Preconditions.checkBusinessLogique(dto.getCodeDevise() != null, "error.DeviseRequired");
            domaine.setCodeDevise(dto.getCodeDevise());
            if (domaine.getCodeDevise() != null) {
                domaine.setDevise(DeviseFactory.createDeviseByCode(dto.getCodeDevise()));

            }
            domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
            if (domaine.getCodeEtatApprouver() != null) {
                domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));

            }
            Preconditions.checkBusinessLogique(dto.getCodeModeReglement() != null, "error.ModeReglementRequired");
            domaine.setCodeModeReglement(dto.getCodeModeReglement());
            if (domaine.getCodeModeReglement() != null) {
                domaine.setModeReglement(ModeReglementFactory.createModeReglementByCode(dto.getCodeModeReglement()));

            }

            return domaine;
        } else {
            return null;
        }
    }

    public static TransfertCaisseDTO transfertCaisseToTransfertCaisseDTO(TransfertCaisse domaine) {

        if (domaine != null) {
            TransfertCaisseDTO dto = new TransfertCaisseDTO();

            dto.setCode(domaine.getCode());
            dto.setCodeSaisie(domaine.getCodeSaisie());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());
            dto.setTauxChange(domaine.getTauxChange());
            dto.setObservation(domaine.getObservation());
            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
            dto.setMontant(domaine.getMontant());
            dto.setMontantEnDevise(domaine.getMontantEnDevise());
            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
            dto.setCodeCaisse(domaine.getCodeCaisse());

            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());

            dto.setCaisseTrDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisseTr()));
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

//    
//    public static TransfertCaisseDTO transfertCaisseToTransfertCaisseDTO(TransfertCaisse domaine) {
//
//        if (domaine != null) {
//            TransfertCaisseDTO dto = new TransfertCaisseDTO();
//            dto.setCode(domaine.getCode());
//            dto.setObservation(domaine.getObservation());
//            dto.setCodeSaisie(domaine.getCodeSaisie());
//            dto.setDateCreate(domaine.getDateCreate());
//            dto.setUserCreate(domaine.getUserCreate());
//            dto.setMontant(domaine.getMontant());
//            dto.setMontantEnDevise(domaine.getMontantEnDevise());       
//            dto.setTauxChange(domaine.getTauxChange());
//            dto.setCodeUserApprouver(domaine.getCodeUserApprouver());
//
//            
//
//            dto.setCaisseDTO(CaisseFactory.caisseToCaisseDTO(domaine.getCaisse()));
//            dto.setCodeCaisse(domaine.getCodeCaisse());
//
//            dto.setModeReglementDTO(ModeReglementFactory.modeReglementToModeReglementDTO(domaine.getModeReglement()));
//            dto.setCodeModeReglement(domaine.getCodeModeReglement());
//
//            dto.setDeviseDTO(DeviseFactory.deviseToDeviseDTO(domaine.getDevise()));
//            dto.setCodeDevise(domaine.getCodeDevise());
//
//            dto.setEtatApprouverDTO(EtatApprouverFactory.etatApprouverToEtatApprouverDTO(domaine.getEtatApprouver()));
//            dto.setCodeEtatApprouver(domaine.getCodeEtatApprouver());
//
//             
//
//            return dto;
//        } else {
//            return null;
//        }
//    }
    public static List<TransfertCaisseDTO> listTransfertCaisseToTransfertCaisseDTOs(List<TransfertCaisse> transfertCaisses) {
        List<TransfertCaisseDTO> list = new ArrayList<>();
        for (TransfertCaisse transfertCaisse : transfertCaisses) {
            list.add(transfertCaisseToTransfertCaisseDTO(transfertCaisse));
        }
        return list;
    }

    public static Collection<TransfertCaisseDTO> CollectiontransfertCaissesTotransfertCaissesDTOsCollection(Collection<TransfertCaisse> transfertCaisses) {
        List<TransfertCaisseDTO> dtos = new ArrayList<>();
        transfertCaisses.forEach(x -> {
            dtos.add(transfertCaisseToTransfertCaisseDTO(x));
        });
        return dtos;

    }

    public static TransfertCaisse ApprouveTransfertCaisseDTOToTransfertCaisse(TransfertCaisse domaine, TransfertCaisseDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(dto.getCodeUserApprouver());
        domaine.setDateApprouve(new Date());

        return domaine;
    }

    public static TransfertCaisse CancelTransfertCaisseDTOToTransfertCaisse(TransfertCaisse domaine, TransfertCaisseDTO dto) {
        domaine.setCode(dto.getCode());
        domaine.setCodeSaisie(dto.getCodeSaisie());

        domaine.setCodeEtatApprouver(dto.getCodeEtatApprouver());
        if (domaine.getCodeEtatApprouver() != null) {
            domaine.setEtatApprouver(EtatApprouverFactory.createEtatApprouverByCode(dto.getCodeEtatApprouver()));
        }
        domaine.setCodeUserApprouver(null);
        domaine.setDateApprouve(null);

        return domaine;
    }
}
