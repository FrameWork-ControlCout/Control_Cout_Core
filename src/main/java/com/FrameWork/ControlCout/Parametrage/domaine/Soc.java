/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FrameWork.ControlCout.Parametrage.domaine;
 
import jakarta.persistence.*; 
import jakarta.validation.constraints.*; 

/**
 *
 * @author Admin
 */
 
@Entity
@Table(name = "Soc", schema = "param") 
public class Soc  {
 
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
    @Column(name = "nom_societe", nullable = false, length = 100)
    private String nomSociete;
    @Basic(optional = false)
    @Column(name = "nom_societe_ar", nullable = false, length = 100,columnDefinition = ("nvarchar(100)"))
    @Size(max = 100)
    private String nomSocieteAr;
   
 
    @Lob
    @Column(name = "img_iso")
    private byte[] imgIso;
    
    public Soc() {
    }

    public Soc(Integer code) {
        this.code = code;
    }

    public Soc(Integer code, String nomSociete) {
        this.code = code;
        this.nomSociete = nomSociete;
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

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getNomSocieteAr() {
        return nomSocieteAr;
    }

    public void setNomSocieteAr(String nomSocieteAr) {
        this.nomSocieteAr = nomSocieteAr;
    }

  

 
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
        if (!(object instanceof Soc)) {
            return false;
        }
        Soc other = (Soc) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Soc{" + "code=" + code + '}';
    }

   

}
