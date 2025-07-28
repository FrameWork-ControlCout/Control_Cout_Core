/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Achat.domaine;

import java.io.Serializable;
import jakarta.persistence.*; 
import jakarta.validation.constraints.*;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "clinique")
public class Clinique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code", nullable = false)
    private Integer code;
    @Lob
    @Column(name = "logo")
    private byte[] logo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_clinique", nullable = false, length = 100)
    private String nomClinique;
    @Basic(optional = false)
    @Column(name = "nom_clinique_Ar", nullable = false, length = 100)
    @Size(
            max = 100
    )
    private String nomCliniqueAr;
    @Column(name = "code_site")
    private Integer codeSite;

//    @JoinColumn(name = "code_site", referencedColumnName = "Code", updatable = false, insertable = false)
//    @ManyToOne
//    private Site site;
    @Lob
    @Column(name = "img_iso")
    private byte[] imgIso;
    
    public Clinique() {
    }

    public Clinique(Integer code) {
        this.code = code;
    }

    public Clinique(Integer code, String nomClinique) {
        this.code = code;
        this.nomClinique = nomClinique;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getNomClinique() {
        return nomClinique;
    }

    public void setNomClinique(String nomClinique) {
        this.nomClinique = nomClinique;
    }

    public String getNomCliniqueAr() {
        return nomCliniqueAr;
    }

    public void setNomCliniqueAr(String nomCliniqueAr) {
        this.nomCliniqueAr = nomCliniqueAr;
    }

    public Integer getCodeSite() {
        return codeSite;
    }

    public void setCodeSite(Integer codeSite) {
        this.codeSite = codeSite;
    }

//    public Site getSite() {
//        return site;
//    }
//
//    public void setSite(Site site) {
//        this.site = site;
//    }

    public byte[] getImgIso() {
        return imgIso;
    }

    public void setImgIso(byte[] imgIso) {
        this.imgIso = imgIso;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinique)) {
            return false;
        }
        Clinique other = (Clinique) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.parametrage.domain.Clinique[ code=" + code + " ]";
    }

}
