/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FrameWork.ControlCout.Authentification.factory;

import com.FrameWork.ControlCout.Authentification.domaine.User;
import com.FrameWork.ControlCout.Authentification.dto.AccessUserDTO;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrator
 */
@Component
public class AccessUserFactory {

    public static User accessUserDTOToAccessUser(AccessUserDTO dTO, User domaine) {
//        AccessUser domaine = new AccessUser();
        domaine.setId(dTO.getId());
        domaine.setUserName(dTO.getUserName());
        domaine.setFullName(dTO.getFullName());
        domaine.setEmail(dTO.getEmail());

        domaine.setSignature(dTO.getSignature());
        domaine.setImageProfil(dTO.getImageProfil());

        domaine.setPassword(dTO.getPassword());
        domaine.setPasswordDecry(dTO.getPasswordDecry());

        return domaine;
    }

    public static User accessUserDTOToAccessUserx(AccessUserDTO Dto, User domaine) {
        if (Dto != null) {

            domaine.setId(Dto.getId());
            domaine.setUserName(Dto.getUserName());
            domaine.setEmail(Dto.getEmail());

            domaine.setFullName(Dto.getFullName());
            domaine.setPassword(Dto.getPassword());
            domaine.setPasswordDecry(Dto.getPasswordDecry());

            if (Dto.getSig() != null) {

                String[] strings = Dto.getSig().split(",");
                String extension;
                switch (strings[0]) {//check image's extension
                    case "data:image/jpeg;base64":
                        extension = "jpeg";
                        break;
                    case "data:image/png;base64":
                        extension = "png";
                        break;
                    case "data:application/pdf;base64":
                        extension = "pdf";
                        break;
                    case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
                        extension = "docx";
                        break;
                    default://should write cases for more images types
                        extension = "jpg";
                        break;
                }
                //convert base64 string to binary data
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);

                domaine.setSignature(data);
            } else {
                domaine.setSignature(null);
            }

            // Profil image
            if (Dto.getImgProfil() != null) {

                String[] strings = Dto.getImgProfil().split(",");
                String extension;
                switch (strings[0]) {//check image's extension
                    case "data:image/jpeg;base64":
                        extension = "jpeg";
                        break;
                    case "data:image/png;base64":
                        extension = "png";
                        break;
                    case "data:application/pdf;base64":
                        extension = "pdf";
                        break;
                    case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
                        extension = "docx";
                        break;
                    default://should write cases for more images types
                        extension = "jpg";
                        break;
                }
                //convert base64 string to binary data
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);

                domaine.setImageProfil(data);
            } else {
                domaine.setImageProfil(null);
            }

            return domaine;
        } else {
            return null;
        }
    }

    public static AccessUserDTO accessUserToAccessUserDTOFull(User domaine) {
        if (domaine != null) {
            AccessUserDTO dTO = new AccessUserDTO();
            dTO.setId(domaine.getId());
            dTO.setPassword(domaine.getPassword());
            dTO.setPasswordDecry(domaine.getPasswordDecry());

            dTO.setFullName(domaine.getFullName());
            dTO.setEmail(domaine.getEmail());

            dTO.setCodeMedecin(domaine.getCodeMedecin());
            dTO.setPermissionDMI(domaine.getPermissionDMI());

            dTO.setUserName(domaine.getUserName());
            dTO.setSignature(domaine.getSignature());
            dTO.setImageProfil(domaine.getImageProfil());

            return dTO;
        } else {
            return null;
        }
    }

    public static AccessUserDTO accessUserToAccessUserDTOWithOutPassword(User domaine) {
        AccessUserDTO dTO = new AccessUserDTO();
        dTO.setUserName(domaine.getUserName());
//        if (!Boolean.TRUE.equals(withoutLogo)) {
        dTO.setSignature(domaine.getSignature());
        dTO.setImageProfil(domaine.getImageProfil());

//        }
        dTO.setId(domaine.getId());
        dTO.setFullName(domaine.getFullName());

        return dTO;
    }

    public static AccessUserDTO accessUserToAccessUserDTO(User domaine, Boolean withoutLogo) {
        AccessUserDTO dTO = new AccessUserDTO();
        dTO.setUserName(domaine.getUserName());
//        if (!Boolean.TRUE.equals(withoutLogo)) {
        dTO.setSignature(domaine.getSignature());
        dTO.setImageProfil(domaine.getImageProfil());

//        }
        dTO.setCodeMedecin(domaine.getCodeMedecin());
        dTO.setPermissionDMI(domaine.getPermissionDMI());

        dTO.setId(domaine.getId());
        dTO.setFullName(domaine.getFullName());
        dTO.setPassword(domaine.getPassword());
        dTO.setPasswordDecry(domaine.getPasswordDecry());

        return dTO;
    }
    
    public static AccessUserDTO accessUserToAccessUserDTOImage(User domaine, Boolean withoutLogo) {
        AccessUserDTO dTO = new AccessUserDTO();
        dTO.setUserName(domaine.getUserName());
 
        dTO.setImageProfil(domaine.getImageProfil());
 

        return dTO;
    }

    public static List<AccessUserDTO> societeToSocieteDTOs(List<User> societes) {
        List<AccessUserDTO> dTOs = new ArrayList<>();
        societes.forEach(x -> {
            dTOs.add(accessUserToAccessUserDTO(x, false));
        });
        return dTOs;
    }

    public static List<AccessUserDTO> societeToSocieteDTOsWithOutPassword(List<User> societes) {
        List<AccessUserDTO> dTOs = new ArrayList<>();
        societes.forEach(x -> {
            dTOs.add(accessUserToAccessUserDTOWithOutPassword(x));
        });
        return dTOs;
    }

    public static AccessUserDTO accessUserToAccessUserDTOx(User domaine) {

        if (domaine != null) {
            AccessUserDTO dTO = new AccessUserDTO();
            dTO.setUserName(domaine.getUserName());
            dTO.setSignature(domaine.getSignature());
            dTO.setImageProfil(domaine.getImageProfil());

            dTO.setId(domaine.getId());
            dTO.setFullName(domaine.getFullName());
            dTO.setPassword(domaine.getPassword());
            dTO.setPasswordDecry(domaine.getPasswordDecry());
            dTO.setCodeMedecin(domaine.getCodeMedecin());
            dTO.setPermissionDMI(domaine.getPermissionDMI());

            return dTO;
        } else {
            return null;
        }
    }
}
