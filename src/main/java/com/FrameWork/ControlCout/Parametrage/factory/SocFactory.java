package com.FrameWork.ControlCout.Parametrage.factory;

import com.FrameWork.ControlCout.Parametrage.domaine.Soc;
import com.FrameWork.ControlCout.Parametrage.dto.SocDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SocFactory {

    static String LANGUAGE_SEC;

    @Value("${lang.secondary}")
    public void setLanguage(String db) {
        LANGUAGE_SEC = db;
    }

    public static SocDTO socToSocDTO(Soc domaine, Boolean withoutLogo) {
        SocDTO socDTO = new SocDTO();
        socDTO.setCode(domaine.getCode());
        if (!Boolean.TRUE.equals(withoutLogo)) {
            socDTO.setLogo(domaine.getLogo());
            socDTO.setImgIso(domaine.getImgIso());
        }
        socDTO.setNomSociete(domaine.getNomSociete());
        socDTO.setNomSocieteAr(domaine.getNomSocieteAr());
        return socDTO;
    }

    public static Soc socDTOToSoc(SocDTO socDTO) {
        Soc soc = new Soc();
        soc.setCode(socDTO.getCode());
        soc.setLogo(socDTO.getLogo());
        if (LocaleContextHolder.getLocale().getLanguage().equals(new Locale(LANGUAGE_SEC).getLanguage())) {
            soc.setNomSociete(socDTO.getNomSocieteAr());
            soc.setNomSocieteAr(socDTO.getNomSociete());
        } else {
            soc.setNomSociete(socDTO.getNomSociete());
            soc.setNomSocieteAr(socDTO.getNomSocieteAr());
        }

        return soc;
    }

    public static Collection<SocDTO> socToSocDTOs(Collection<Soc> socs) {
        List<SocDTO> socsDTO = new ArrayList<>();
        socs.forEach(x -> {
            socsDTO.add(socToSocDTO(x, false));
        });
        return socsDTO;
    }
}
