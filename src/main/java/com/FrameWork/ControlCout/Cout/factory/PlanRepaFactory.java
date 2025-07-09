/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Cout.factory;

import com.FrameWork.ControlCout.Cout.domaine.PlanRepa;
import com.FrameWork.ControlCout.Cout.dto.PlanRepaDTO;
import com.FrameWork.ControlCout.Parametrage.factory.TypeRepaFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class PlanRepaFactory {

    public static PlanRepa createPlanRepaByCode(int code) {
        PlanRepa domaine = new PlanRepa();
        domaine.setCode(code);
        return domaine;
    }

    public static PlanRepa planRepaDTOToPlanRepa(PlanRepaDTO dto, PlanRepa domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());
            domaine.setDatePlan(dto.getDatePlan());

            domaine.setCodeFicheTechnique(dto.getCodeFicheTechnique());
            if (domaine.getCodeFicheTechnique() != null) {
                domaine.setFicheTechnique(FicheTechFactory.createFicheTechniqueByCode(dto.getCodeFicheTechnique()));
            }

            domaine.setCodeTypeRepa(dto.getCodeTypeRepa());
            if (domaine.getCodeTypeRepa() != null) {
                domaine.setTypeRepa(TypeRepaFactory.createTypeRepaByCode(dto.getCodeTypeRepa()));
            }
            return domaine;
        } else {
            return null;
        }
    }

    public static PlanRepaDTO planRepaToPlanRepaDTO(PlanRepa domaine) {

        if (domaine != null) {
            PlanRepaDTO dto = new PlanRepaDTO();
            dto.setCode(domaine.getCode()); 
            dto.setDatePlan(domaine.getDatePlan());
            dto.setDateCreate(domaine.getDateCreate());
            dto.setUserCreate(domaine.getUserCreate());

            dto.setCodeFicheTechnique(domaine.getCodeFicheTechnique());
            dto.setFicheTechniqueDTO(FicheTechFactory.ficheTechniqueToFicheTechniqueDTO(domaine.getFicheTechnique()));

            dto.setCodeTypeRepa(domaine.getCodeTypeRepa());
            dto.setTypeRepaDTO(TypeRepaFactory.typeRepaToTypeRepaDTO(domaine.getTypeRepa()));
            

            return dto;
        } else {
            return null;
        }
    }

    public static List<PlanRepaDTO> listPlanRepaToPlanRepaDTOs(List<PlanRepa> planRepas) {
        List<PlanRepaDTO> list = new ArrayList<>();
        for (PlanRepa planRepa : planRepas) {
            list.add(planRepaToPlanRepaDTO(planRepa));
        }
        return list;
    }

    public static Collection<PlanRepaDTO> CollectionPlanRepaToPlanRepaDTOs(Collection<PlanRepa> planRepas) {
        Collection<PlanRepaDTO> collection = new ArrayList<>();
        for (PlanRepa planRepa : planRepas) {
            collection.add(planRepaToPlanRepaDTO(planRepa));
        }
        return collection;
    }
}
