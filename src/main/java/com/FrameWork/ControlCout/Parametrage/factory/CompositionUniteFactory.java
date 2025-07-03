/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.CompositionUnite;
import com.FrameWork.ControlCout.Parametrage.dto.CompositionUniteDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class CompositionUniteFactory {

    public static CompositionUnite createCompositionUniteByCode(int code) {
        CompositionUnite domaine = new CompositionUnite();
        domaine.setCode(code);
        return domaine;
    }

    public static CompositionUnite compositionUniteDTOToCompositionUnite(CompositionUniteDTO dto, CompositionUnite domaine) {
        if (dto != null) {
            domaine.setCode(dto.getCode());

            domaine.setNmbreUnite(dto.getNmbreUnite());

            domaine.setCodeUnitePrinc(dto.getCodeUnitePrinc());
            if (domaine.getCodeUnitePrinc() != null) {
                domaine.setUnitePrinc(UniteFactory.createUniteByCode(dto.getCodeUnitePrinc()));
            }

            domaine.setCodeUniteSec(dto.getCodeUniteSec());
            if (domaine.getCodeUniteSec() != null) {
                domaine.setUniteSec(UniteFactory.createUniteByCode(dto.getCodeUniteSec()));
            }
            return domaine;
        } else {
            return null;
        }
    }

    public static CompositionUniteDTO compositionUniteToCompositionUniteDTO(CompositionUnite domaine) {

        if (domaine != null) {
            CompositionUniteDTO dto = new CompositionUniteDTO();
            dto.setCode(domaine.getCode());

            dto.setNmbreUnite(domaine.getNmbreUnite());

            dto.setCodeUnitePrinc(domaine.getCodeUnitePrinc());
            dto.setUniteSecDTO(UniteFactory.uniteToUniteDTO(domaine.getUnitePrinc()));

            dto.setCodeUniteSec(domaine.getCodeUniteSec());
            dto.setUniteSecDTO(UniteFactory.uniteToUniteDTO(domaine.getUniteSec()));

            return dto;
        } else {
            return null;
        }
    }

    public static List<CompositionUniteDTO> listCompositionUniteToCompositionUniteDTOs(List<CompositionUnite> compositionUnites) {
        List<CompositionUniteDTO> list = new ArrayList<>();
        for (CompositionUnite compositionUnite : compositionUnites) {
            list.add(compositionUniteToCompositionUniteDTO(compositionUnite));
        }
        return list;
    }
}
